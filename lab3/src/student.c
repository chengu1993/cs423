/**
 * General structure of a student.
 *
 */

#include <pthread.h>
#include <stdio.h>
#include <time.h>
#include <errno.h>
#include <string.h>
#include <stdlib.h>
#include "ta.h"

void *student_loop(void *param)
{
	int number = *((int *)param);
	int sleep_time, help_time = 0;

        // set the seed
	srandom((unsigned)time(NULL));

        while(help_time < 5) {
            // programming for a while
	    sleep_time = (int)((random() % MAX_SLEEP_TIME) + 1);
	    programming(sleep_time);
            // seek help
            pthread_mutex_lock (&chair_lock);
            if(chair_idx == NUM_OF_SEATS) {
                // release the lock
                pthread_mutex_unlock (&chair_lock);       
                // the students will now quit and show up later
                printf("Student %d will try again later\n", number);
            } else {
                chairs[chair_idx++] = number;
                // release the lock
                pthread_mutex_unlock (&chair_lock);       
                printf("Student %d takes a seat waiting students = %d\n", number, chair_idx);
                if(sem_post(&student_sem) != 0)
                    printf("%s\n", strerror(errno));
                if(sem_wait(&ta_sem) != 0)
                    printf("%s\n", strerror(errno));
                help_time++;
            }
        }
	return NULL;
}

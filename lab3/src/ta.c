/**
 * General structure of the teaching assistant.
 *
 */

#include <pthread.h>
#include <stdio.h>
#include <time.h>
#include <errno.h>
#include <string.h>
#include <stdlib.h>
#include "ta.h"

void *ta_loop(void *param)
{
    int sleep_time, help_time = 0; 
    // set the seed
    srandom((unsigned)time(NULL));

    while(help_time < NUM_OF_HELP_PER_STUDENT * NUM_OF_STUDENTS) {
        pthread_mutex_lock (&chair_lock);
        if(chair_idx > 0) {
            chair_idx--;
            // release the lock
            pthread_mutex_unlock(&chair_lock);
            sleep_time = (int) ((random() % MAX_SLEEP_TIME) + 1); 
            printf("Helping student for %d seconds waiting students = %d\n", sleep_time, chair_idx); 
            help_student(sleep_time);
            help_time++;
            // wake up waiting students and by this time this stduent is already helped 
            if(sem_post(&ta_sem) != 0)
                printf("%s\n", strerror(errno));
 
        } else {
            // release the lock
            pthread_mutex_unlock(&chair_lock);
            // take a nap and wait for students to wake him/her up
            if(sem_wait(&student_sem) != 0) 
                printf("%s\n", strerror(errno));
        }
    }    
}

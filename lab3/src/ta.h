/**
 * Header file for sleeping TA
 */

#include <pthread.h>
#include <semaphore.h>

// the maximum time (in seconds) to sleep
#define MAX_SLEEP_TIME	5

// number of maximum waiting students
#define MAX_WAITING_STUDENTS	3

// number of help each student needs
#define NUM_OF_HELP_PER_STUDENT 5

// number of potential students
#define NUM_OF_STUDENTS		5

// number of available seats
#define NUM_OF_SEATS	3

pthread_mutex_t chair_lock;

sem_t ta_sem, student_sem;



// the numeric id of each student
int student_id[NUM_OF_STUDENTS];

// chairs
// for chair index -1 represents ta is available,
// 0-3 presents ta is not available, 
// and there are 0-3 students waiting
int chair_idx;
int chairs[NUM_OF_SEATS];

// student function prototype
void *student_loop(void *param);

// ta function prototype
void *ta_loop(void *param);

// simulate programming
void programming(int sleeptime);

// simulate helping a student
void help_student(int sleeptime);

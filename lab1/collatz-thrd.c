#include<stdio.h>
#include<stdlib.h>
#include<unistd.h>
#include<pthread.h>
    
// define linked list node
typedef struct Node {
    long val;
    struct Node *next;
} Node;

/* Initialize linked list */
Node *p;

// child thread to run
void *runner(void *param) {
    long n = atol(param);
    if(n < 0) {
        fprintf(stderr, "The parameter should be positive\n");
    }
    while(n != 1) {
        p->val = n;
        p->next = (Node*) malloc(sizeof(Node));
        p = p->next;
        if((n & 1) == 1){
            n = n * 3 + 1;
        } else {
            n /= 2;
        }
    }
    p->val = 1;
    pthread_exit(0);
}


int main(int argc, char* argv[]) {
    // initialize head of linked list
    Node *head, *temp;
    head = p = (Node*) malloc(sizeof(Node));

    
    pthread_t tid;
    pthread_attr_t attr;

    if(argc != 2) {
        fprintf(stderr, "usage: collatz-thrd <integer value>\n");
        return -1;
    }
    /* get the default attributes*/
    pthread_attr_init(&attr);

    /* create the thread*/
    pthread_create(&tid, &attr, runner, argv[1]);

    /* now wait for the thread to exit */
    pthread_join(tid, NULL);

    /* Print out linked list */
    p = head;
    while(p->next != NULL){
        printf("%ld,", p->val);
        temp = p->next;
        free(p);
        p = temp;
    }
    printf("1\n");

    return 0;
}




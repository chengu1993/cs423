#include<stdio.h>
#include<unistd.h>
#include<stdlib.h>
#include<sys/types.h>

int main(int argc, char *argv[]){
    pid_t pid = fork();
    if(pid < 0) {
        fprintf(stderr, "Fork Failed\n");
        return 1;
    } else if(pid == 0) { //child process
        // convert param to long
        if(argc != 2) {
            fprintf(stderr, "usage: collatz-thrd <integer value>\n");
            return -1;
        }
        long n = atol(argv[1]);
        while(n != 1){
            printf("%ld,", n);
            if((n & 1) == 1){
                n = n * 3 + 1;
            } else {
                n /= 2;
            }
        }
        printf("1\n");
    } else { // parent process
        // wait for the child process
        wait(NULL);
    }
    return 0;
}

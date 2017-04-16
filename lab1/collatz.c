#include<stdio.h>
#include<stdlib.h>
#include<unistd.h>

int main(int argc, char* argv[]){
    pid_t pid = fork();
    if(pid < 0) {
        fprintf(stderr, "Fork Failed\n");
    } else if(pid == 0) { // child process
        // execute another program
        execlp("./lab1", "lab1", argv[1], NULL);
    } else { // parent process
        // wait for child process
        wait(NULL);
    }
    return 0;
}



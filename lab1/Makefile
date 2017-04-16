# Build the multi-fork program
CC = gcc
CFLAGS = -Wall

all: lab1 collatz collatz-thrd
lab1: lab1.o
collatz: collatz.o 
collatz-thrd: collatz-thrd.o
	$(CC) -o $@ $(CFLAGS) $^

.c.o:
	$(CC) $(CFLAGS) -c $<

clean:
	rm -f lab1 collatz collatz-thrd *.o

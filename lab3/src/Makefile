# Makefile for sleeping TA
CC=gcc
CFLAGS=-lpthread

all: sleeping_ta

sleeping_ta: main.o student.o ta.o help_student.o programming.o
	$(CC) $(CFLAGS) -o sleeping_ta main.o student.o ta.o help_student.o programming.o

main.o: main.c ta.h
	$(CC) $(CFLAGS) -c main.c

student.o: student.c ta.h
	$(CC) $(CFLAGS) -c student.c

ta.o: ta.c ta.h
	$(CC) $(CFLAGS) -lpthread -c ta.c

help_student.o: help_student.c ta.h
	$(CC) $(CFLAGS) -c help_student.c

programming.o: programming.c ta.h
	$(CC) $(CFLAGS) -lpthread -c programming.c

clean: 
	rm -f sleeping_ta *.o

#include <stdio.h>

int main()
{
	fork();
	

	fork();

	fork();
	printf("%d\n",getpid());

	return 0;
}


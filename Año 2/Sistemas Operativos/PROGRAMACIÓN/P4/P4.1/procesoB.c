#include <stdio.h>
#include <sys/types.h>
#include <unistd.h>
#include <sys/stat.h>
#include <fcntl.h>
#include <stdlib.h>
#include <signal.h>

void nada(){}

int main()
{
    signal(1, nada);
    signal(4, nada);

    pause();

    printf("Segundo mensaje\n");
    kill(getppid(), 2);

    pause();

    printf("Quinto mensaje\n");
    kill(getppid(), 5);
}
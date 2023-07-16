#include <stdio.h>
#include <sys/types.h>
#include <unistd.h>
#include <sys/stat.h>
#include <fcntl.h>
#include <stdlib.h>
#include <signal.h>

int seguir = 1;

void nada(){}

void fin()
{
    seguir = 0;
}

int main()
{
    signal(2, nada);
    signal(SIGALRM, fin);

    pause();

    printf("Tercer mensaje\n");

    alarm(3);

    do
    {
        printf("Esperando Alarma ....\n");
        sleep(1);
    } while (seguir);

    printf("Cuarto mensaje\n");

    kill(getppid(), 4);
}
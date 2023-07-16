#include <stdio.h>
#include <sys/types.h>
#include <unistd.h>
#include <sys/stat.h>
#include <fcntl.h>
#include <stdlib.h>
#include <signal.h>

int pidB, pidC;

void nada(){}

int main()
{
    pidB = fork();

    if (pidB == 0)
    {
        execl("procesoB", "procesoB", NULL);
    }
    else
    {
        pidC = fork();

        if (pidC == 0)
        {
            execl("procesoC", "procesoC", NULL);
        }
        else
        {
            signal(2, nada);
            signal(4, nada);
            signal(5, nada);

            sleep(1);

            printf("Primer mensaje\n");

            kill(pidB, 1);

            pause();

            kill(pidC, 2);

            pause();

            kill(pidB, 4);

            pause();

            printf("Último mensaje\n");
        }
    }

    return 0;
}
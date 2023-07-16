#include <stdio.h>
#include <unistd.h>
#include <stdlib.h>
#include <sys/types.h>
#include <sys/stat.h>
#include <fcntl.h>
#include <signal.h>
#include <sys/wait.h>

int seguir = 1;

void Fin()
{
    seguir = 0;
}

void FinDescarga() {}

int main()
{
    signal(10, FinDescarga);
    signal(12, Fin);

    int ppid = getppid();

    srand(getpid());

    int espera;

    printf("Camion %d creado...\n", getpid());

    while (seguir)
    {
        espera = (rand() % 6) + 5;

        printf("CAMION: esperando %d segundos\n", espera);
        sleep(espera);

        if (seguir)
        {
            kill(ppid, 10);

            pause();
        }
    }

    return 0;
}
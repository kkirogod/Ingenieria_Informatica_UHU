#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <signal.h>
#include <sys/types.h>
#include <sys/wait.h>
#include <sys/ipc.h>
#include <sys/msg.h>

#include "comun.h"

int llega10 = 0;

void f10() // => TODO OK
{
    llega10 = 1;
}

void f12() // => FIN EJECUCUÓN
{
    printf("El servidor no ha podido arrancar...\n");
    exit(-1);
}

int main()
{
    signal(10, f10);
    signal(12, f12);

    int maxEstudiantes = 10;

    srand(getpid());

    // Arrancando el servidor gráfico

    int pidServidor = fork();

    if (pidServidor == 0)
    {
        execl("servidor_ncurses", "servidor_ncurses", NULL);
        perror("ERROR: execl\n");
        exit(-1);
    }
    else if (pidServidor == -1)
    {
        perror("ERROR: fork\n");
        exit(-1);
    }

    // Comprobamos si ha arrancado correctamente

    if (!llega10) // Por si llega antes del pause()
        pause();

    llega10 = 0;

    // crear estudiantes de teoria

    int pidAteoria, valor;

    for (int i = 0; i < maxEstudiantes; i++)
    {
        valor=rand()%2; //creamos estudiantes de ATODO y ATEORIA aleatoriamente

        pidAteoria = fork();

        if (pidAteoria == 0)
        {
            if(valor==0)
                execl("ateoria", "ateoria", NULL);
            else
                execl("atodo", "atodo", NULL);

            perror("ERROR: execl\n");
            exit(-1);
        }
        else if (pidAteoria == -1)
        {
            perror("ERROR: fork\n");
            exit(-1);
        }

        //espero entre 2" y 5"

        sleep(rand() % 4 + 2);
    }

    //esperamos a los estudiantes

    for (int i = 0; i < maxEstudiantes; i++)
    {
        wait(NULL);
    }

    sleep(5);

    // Avisar al servidor gráfico que hemos terminado

    kill(pidServidor, 12);

    // Esperar a ncurses (servidor gráfico)

    wait(NULL);

    // Reseteo de la terminal

    system("reset");

    return 0;
}
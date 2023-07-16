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
    int retorno, hijo, pidB;

    printf("Hola soy el proceso A!\n");

    pidB = fork();

    if (pidB == 0)
    {
        execl("procesoB", "procesoB", NULL);
    }
    else
    {
        signal(1, nada);

        srand(time(NULL));

        pause();

        int num = (rand() % 4) + 2;

        printf("El numero generado en A es: %d\n", num);

        sleep(num);

        kill(pidB, 2);

        hijo = wait(&retorno);

        if (WIFEXITED(retorno))
        {
            switch(WEXITSTATUS(retorno))
            {
                case 0: printf("Juego abortado\n");
                        break;

                case 1: printf("Gano Yo\n");
                        break;

                case 2: printf("Gana C\n");
                        break;

                default: printf("Ha ocurrido algún problema\n");
            }
        }
        else
        {
            printf("Ha terminado de forma extraña \n");
        }
    }

    return 0;
}
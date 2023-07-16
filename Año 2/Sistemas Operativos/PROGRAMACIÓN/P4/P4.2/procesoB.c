#include <stdio.h>
#include <sys/types.h>
#include <unistd.h>
#include <sys/stat.h>
#include <fcntl.h>
#include <stdlib.h>
#include <signal.h>

int valor;

void alarma()
{
    valor = 0;
}

void procesoA()
{
    valor = 1;
}

void procesoC()
{
    valor = 2;
}

int main()
{
    int pidC, pidA = getppid();

    printf("Hola soy el proceso B!\n");

    pidC = fork();

    if (pidC == 0)
    {
        execl("procesoC", "procesoC", NULL);
    }
    else
    {
        srand(time(NULL));

        signal(SIGALRM, alarma);
        signal(2, procesoA);
        signal(3, procesoC);

        sleep(1);

        kill(pidA, 1);
        kill(pidC, 1);

        int num = (rand() % 4) + 3;

        printf("El numero generado en B es: %d\n", num);

        alarm(num);

        pause();

        exit(valor);
    }
}
#include <stdio.h>
#include <sys/types.h>
#include <unistd.h>
#include <sys/stat.h>
#include <fcntl.h>
#include <time.h>
#include <stdlib.h>

void procesoB()
{
    close(1);
    int descriptor = open("InfoB", O_WRONLY | O_CREAT | O_TRUNC);

    printf("Hola, soy el proceso B\n");

    int num = (rand() % 10) + 1;

    int pidC;

    pidC = fork();

    if (pidC == 0)
    {
        char numRand[2];
        sprintf(numRand, "%d", num);

        close(descriptor);

        execl("./procesoC", "procesoC", numRand, NULL);
        // perror("Error al ejecutar el proceso C\n");
    }
    else
    {
        printf("Soy el proceso B, mi PID es %d, el del proceso A es %d y el del proceso C es %d\n", getpid(), getppid(), pidC);
        sleep(1);
        printf("El proceso B acaba aquí\n");    
    }
}

int main()
{
    int pidB, pidC;

    printf("Soy el proceso A, mi PID es %d\n", getpid());

    pidB = fork();

    srand(time(NULL));

    if (pidB == 0)
        procesoB();
    else
    {
        printf("Mi PID es %d y el del proceso B es %d\n", getpid(), pidB);
        sleep(2);
        printf("El proceso A acaba aquí\n");
    }

    return 0;
}

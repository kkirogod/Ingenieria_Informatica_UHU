#include <stdio.h>
#include <unistd.h>
#include <stdlib.h>
#include <sys/wait.h>
#include <sys/types.h>
#include <sys/stat.h>
#include <fcntl.h>

int main()
{
    int pidB, tubo[2], fd, dato;

    pipe(tubo);

    // CREA AL PROCESO B

    pidB = fork();

    if (pidB == 0)
    {
        close(2); // liberamos la posicion de la salida de errores

        dup(tubo[1]); // el hijo sólo podrá escribir en la tubería

        execl("pB", "pB", NULL);
        perror("no se puede ejecutar pB");
        exit(-1);
    }
    else if (pidB == -1)
        printf("Imposible hacer el fork\n");

    // close(tubo[0]); // sólo podremos escribir de la tubería

    mkfifo("FifoAC", 0660);

    fd = open("FifoAC", O_RDONLY);

    int sumaB=0, sumaC=0;

    for (int i = 0; i < 5; i++)
    {
        read(fd, &dato, sizeof(dato)); // leemos de la fifo
        printf("El dato que manda C es %d\n", dato);
        sumaC = sumaC + dato;

        read(tubo[0], &dato, sizeof(dato)); // leemos de la pipe
        printf("El dato que manda B es %d\n", dato);
        sumaB = sumaB + dato;
    }

    close(fd);
    unlink("FifoAC");

    float mediaC = sumaC / 5.0;
    float mediaB = sumaB / 5.0;

    printf("La media de C es %.1f\n", mediaC);
    printf("La media de B es %.1f\n", mediaB);

    if (mediaB >= mediaC)
        kill(pidB, 10);
    else
        kill(pidB, 12);

    wait(NULL);

    printf("PROCESO A: TERMINADO\n");

    return 0;
}

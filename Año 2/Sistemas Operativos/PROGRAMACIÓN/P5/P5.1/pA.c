#include <stdio.h>
#include <unistd.h>
#include <stdlib.h>
#include <wait.h>
#include <sys/types.h>
#include <sys/stat.h>
#include <fcntl.h>

int main()
{
    int pidb, pidc, retorno, tubo[2];

    mkfifo("FifoBC", 0660);

    pipe(tubo);

    // CREA LOS PROCESOS B Y C

    pidb = fork();

    if (pidb == 0)
    {
        close(2); // liberamos la posicion de la salida de errores

        dup(tubo[0]); // el hijo sólo podrá leer de la tubería

        execl("pB", "pB", NULL);
        perror("no se puede ejecutar pB");
        exit(-1);
    }
    else if (pidb == -1)
        printf("Imposible hacer el fork\n");

    pidc = fork();

    if (pidc == 0)
    {
        execl("pC", "pC", NULL);
        perror("no se puede ejecutar pC");
        exit(-1);
    }
    else if (pidc == -1)
        printf("Imposible hacer el fork\n");

    // close(tubo[0]); // sólo podremos escribir de la tubería

    write(tubo[1], &pidc, sizeof(pidc));

    sleep(1);

    printf("Primer mensaje\n");

    kill(pidb, 10);

    wait(NULL);
    wait(NULL);

    printf("Último mensaje\n");

    unlink("FifoBC");

    return 0;
}
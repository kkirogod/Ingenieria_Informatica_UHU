#include <stdio.h>
#include <unistd.h>
#include <stdlib.h>
#include <wait.h>
#include <sys/types.h>
#include <sys/stat.h>
#include <fcntl.h>

int seguir = 1;

void fin()
{
    seguir = 0;
}

void nada() {}

int main()
{
    signal(SIGALRM, fin);
    signal(10, nada); // señal que manda pB

    int fd;
    int pidB;

    fd = open("FifoBC", O_RDONLY);

    read(fd, &pidB, sizeof(pidB));

    pause(); // espera a la señal de pB

    printf("Tercer mensaje\n");

    alarm(3);

    while (seguir)
    {
        printf("Esperando Alarma ....\n");
        sleep(1);

    }

    printf("Cuarto mensaje\n");

    kill(pidB, 12); // manda señal a pB

    // AQUÍ PUEDE HABER MAS WRITES, ANTES DEL CLOSE

    close(fd);

    return 0;
}
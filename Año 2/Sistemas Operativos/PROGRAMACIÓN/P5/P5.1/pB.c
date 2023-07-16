#include <stdio.h>
#include <unistd.h>
#include <stdlib.h>
#include <wait.h>
#include <sys/types.h>
#include <sys/stat.h>
#include <fcntl.h>

void nada() {}

int main()
{
    signal(12, nada); // señal que manda pC
    signal(10, nada); // señal que manda pA

    int pidB = getpid(), pidC, fd;

    read(2, &pidC, sizeof(pidC)); // leemos de la tubería

    fd = open("FifoBC", O_WRONLY);

    write(fd, &pidB, sizeof(pidB));

    pause();
    
    printf("Segundo mensaje\n");

    kill(pidC, 10);

    pause();

    printf("Quinto mensaje\n");

    // AQUÍ PUEDE HABER MAS READS, ANTES DEL CLOSE

    close(fd);

    return 0;
}
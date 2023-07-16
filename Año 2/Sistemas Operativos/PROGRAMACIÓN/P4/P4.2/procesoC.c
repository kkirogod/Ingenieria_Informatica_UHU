#include <stdio.h>
#include <sys/types.h>
#include <unistd.h>
#include <sys/stat.h>
#include <fcntl.h>
#include <stdlib.h>
#include <signal.h>

void nada() {}

int main()
{
    int pidB = getppid();

    srand(time(NULL));

    signal(1, nada);

    pause();

    int num = (rand() % 4) + 2;

    printf("El numero generado en C es: %d\n", num);

    sleep(num);

    kill(pidB, 3);
}
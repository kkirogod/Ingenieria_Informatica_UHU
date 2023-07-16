#include <stdio.h>
#include <sys/types.h>
#include <sys/stat.h>
#include <fcntl.h>
#include <unistd.h>
#include <stdlib.h>
#include <sys/wait.h>
#include <signal.h>
#include <time.h>

int llega10 = 0;

void R10()
{
    llega10 = 1;
}

int llega12 = 0;

void R12()
{
    llega12 = 1;
}

int main()
{
    signal(10, R10);
    signal(12, R12);

    int pidB = getpid(), pidC, fd, num;

    srand(time(NULL));

    pidC = fork();

    if (pidC == 0)
    {
        execl("pC", "pC", NULL);
        perror("no se puede ejecutar pC");
        exit(-1);
    }
    else if (pidC == -1)
        printf("Imposible hacer el fork\n");

    for (int i = 0; i < 5; i++)
    {
        num = (rand() % 5) + 1;
        write(2, &num, sizeof(num));
    }

    if (!llega10 && !llega12) // porque puede ser que la señal de A llegue antes
        pause();
    
    if (llega10)
    {
        printf("PROCESO B: GANA\n");
        kill(pidC, 12);
    }
    else
    {
        printf("PROCESO B: PIERDE\n");
        kill(pidC, 10);
    }

    wait(NULL);

    printf("PROCESO B: TERMINADO\n");

    return 0;
}
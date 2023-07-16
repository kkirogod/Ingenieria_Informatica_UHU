#include <stdio.h>
#include <unistd.h>
#include <stdlib.h>
#include <wait.h>
#include <sys/types.h>
#include <sys/stat.h>
#include <fcntl.h>
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

    srand(time(NULL));

    int fd = open("FifoAC", O_WRONLY);

    for (int i = 0; i < 5; i++)
    {
        int num = rand() % 4 + 1;
        write(fd, &num, sizeof(num));
    }

    close(fd);

    if (!llega10 && !llega12)
        pause();
    
    if (llega10)
        printf("PROCESO C: GANA\n");
    else
        printf("PROCESO C: PIERDE\n");

    printf("PROCESO C: TERMINADO\n");

    return 0;
}
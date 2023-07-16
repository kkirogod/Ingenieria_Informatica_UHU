#include <stdio.h>
#include <sys/types.h>
#include <unistd.h>
#include <sys/stat.h>
#include <fcntl.h>
#include <stdlib.h>
int main()
{
    int pidB, pidC;

    printf("Soy el proceso A, mi PID es %d\n", getpid());

    pidB = fork();

    if (pidB == 0)
    {
        printf("Soy el proceso B, mi PID es %d, y el del proceso A es %d\n", getpid(), getppid());

        int pidCopy = fork();

        if (pidCopy == 0)
        {
            for (int i = 0; i < 3; i++)
            {
                printf("Soy el proceso B Copia, mi PID es %d, y el de mi padre es %d\n", getpid(), getppid());

                sleep(1);
            }
        }
        else
        {
            for (int i = 0; i < 3; i++)
            {
                printf("Soy el proceso B, mi PID es %d, y el de mi copia es %d\n", getpid(), pidCopy);

                sleep(1);
            }
        }
    }
    else
    {
        pidC = fork();

        if (pidC == 0)
        {
            close(1);
            open("infoc", O_WRONLY | O_CREAT | O_TRUNC);

            printf("Soy el proceso C, mi PID es %d, y el del proceso A es %d\n", getpid(), getppid());
        }
        else
        {
            printf("El PID del proceso B es %d\n", pidB);
            printf("El PID del proceso C es %d\n", pidC);
            sleep(4);
            printf("El proceso A acaba aquí\n");
        }
    }

    return 0;
}
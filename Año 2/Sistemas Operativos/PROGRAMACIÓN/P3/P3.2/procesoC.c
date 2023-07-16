#include <stdio.h>
#include <sys/types.h>
#include <unistd.h>
#include <sys/stat.h>
#include <fcntl.h>
#include <time.h>
#include <stdlib.h>

int main(int argc, char *argv[])
{
    if(argc > 1)
    {
        printf("Soy el proceso C, mi PID es %d, el del proceso B es %d y el numero aleatorio es %d\n", getpid(), getppid(), argv[1]);
    }

    return 0;
}
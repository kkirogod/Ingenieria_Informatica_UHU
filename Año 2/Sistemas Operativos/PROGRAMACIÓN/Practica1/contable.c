#include <stdio.h>
#include <unistd.h>
#include <stdlib.h>
#include <sys/types.h>
#include <sys/stat.h>
#include <fcntl.h>
#include <signal.h>
#include <sys/wait.h>
#include <time.h>

int seguir = 1, carga = 0, descarga = 0;

void Fin()
{
    seguir = 0;
}

void AnotaCarga()
{
    carga = 1;
}

void AnotaDescarga()
{
    descarga = 1;
}

int main()
{
    signal(10, AnotaCarga);
    signal(12, AnotaDescarga);
    signal(18, Fin);

    time_t segundos = time(NULL);

    while (seguir)
    {
        if(carga)
        {
            printf("CARGA realizada en el segundo %ld\n", time(NULL) - segundos);
            carga = 0;
        }

        if(descarga)
        {
            printf("DESCARGA realizada en el segundo %ld\n", time(NULL) - segundos);
            descarga = 0;
        }
    }

    return 0;
}
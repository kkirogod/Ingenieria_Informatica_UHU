#include <stdio.h>
#include <unistd.h>
#include <stdlib.h>
#include <sys/types.h>
#include <sys/stat.h>
#include <fcntl.h>
#include <signal.h>
#include <sys/wait.h>
#include <pthread.h>

int cargaDescarga = 0, silo = 50, seguir = 1, camion = 0;

void *hilo(int *pidContable) // HILO DE LA CARRETILLA
{
    // int *espera = malloc(sizeof(int));

    while (seguir)
    {
        int espera = (rand() % 5) + 4;

        printf("CARRETILLA: esperando %d segundos\n", espera);
        sleep(espera);

        if (seguir)
        {
            while (cargaDescarga)
            {
            }

            cargaDescarga = 1;

            if (silo >= 5)
            {
                silo = silo - 5;
                printf("DESCARGA realizada (-5kg) --> %dkg\n", silo);
            }
            else
            {
                printf("DESCARGA realizada (-%dkg) --> 0kg\n", silo);
                silo = 0;
            }

            kill(*pidContable, 12);

            cargaDescarga = 0;
        }
    }
}

void camionCarga()
{
    camion = 1;
}

void fin()
{
    seguir = 0;
}

int main(int argc, char *argv[])
{
    signal(10, camionCarga);
    signal(SIGALRM, fin);

    srand(getpid());

    if (argc == 1)
    {
        printf("ERROR: Hace falta pasar un tiempo como parámetro\n");
        exit(-1);
    }

    int pidCamion, pidContable;

    // Lanza el proceso camion
    pidCamion = fork();
    if (pidCamion == 0)
    {
        execl("camion", "camion", NULL);
        perror("Error de execl");
        exit(-1);
    }
    else if (pidCamion == -1)
        perror("Error de fork para camion");

    // Lanza el proceso contable
    pidContable = fork();
    if (pidContable == 0)
    {
        close(1);
        open("fbitacora", O_WRONLY | O_CREAT | O_TRUNC, 0666);

        execl("contable", "contable", NULL);
        perror("Error de execl");
        exit(-1);
    }
    else if (pidContable == -1)
        perror("Error de fork para contable");

    // Creamos el hilo Carretilla
    pthread_t carretilla;
    int *ret;
    pthread_create(&carretilla, NULL, (void *)&hilo, &pidContable);

    alarm(atoi(argv[1]));

    int numCargas = 0;

    printf("Nivel inicial --> %dkg\n", silo);

    while (seguir)
    {
        if (camion)
        {
            while (cargaDescarga)
            {
            }

            cargaDescarga = 1;

            silo = silo + 10;

            printf("CARGA realizada (+10kg) --> %dkg\n", silo);

            numCargas++;

            kill(pidContable, 10);
            kill(pidCamion, 10);

            cargaDescarga = 0;
            camion = 0;
        }
    }

    // Esperamos a que acabe el hilo y matamos los procesos hijos
    pthread_join(carretilla, NULL);
    kill(pidCamion, 12);
    kill(pidContable, 18);

    printf("Cargas realizadas --> %d\n", numCargas);
    printf("Nivel final del silo --> %d\n", silo);

    return 0;
}
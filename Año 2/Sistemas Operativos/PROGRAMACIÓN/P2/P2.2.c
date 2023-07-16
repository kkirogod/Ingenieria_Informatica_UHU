#include <stdio.h>
#include <pthread.h>
#include <unistd.h>
#include <time.h>
#include <stdlib.h>

void *hilo(int *id)
{
    int *totalWait = (int *)malloc(sizeof(int));
    *totalWait = 0;

    for (int i = 0; i < 5; i++)
    {
        int wait = (rand() % 3) + 1;

        printf("Indetificador: %i\n", *id);
        printf("Iteración: %i\n", i + 1);
        printf("Tiempo de espera: %i\n\n", wait);

        sleep(wait);

        *totalWait += wait;
    }
    pthread_exit(totalWait);
}

int main()
{
    pthread_t h1, h2;
    int id1 = 1, id2 = 2, tw = 0, *t1, *t2;

    srand(time(NULL));

    pthread_create(&h1, NULL, (void *)&hilo, &id1);
    pthread_create(&h2, NULL, (void *)&hilo, &id2);

    pthread_join(h1, (void *)&t1);
    pthread_join(h2, (void *)&t2);

    if (*t1 > *t2)
    {
        printf("El ganador es el hilo %i con tiempo total de %i segundos!\n", id1, *t1);
        printf("Tiempo hilo %i: %i\n", id2, *t2);
    }
    else
    {
        if (*t1 == *t2)
            printf("Ha habido un empate, los tiempos han sido de %i segundos!\n", *t1);
        else
        {
            printf("El ganador es el hilo %i con tiempo total de %i segundos!\n", id2, *t2);
            printf("Tiempo hilo %i: %i\n", id1, *t1);
        }
    }

    return 0;
}
#include <stdio.h>
#include <unistd.h>
#include <pthread.h>

int var=1;

void *hilo1(void)
{
    while(var!=0)
    {
        scanf("%i", &var);
    }
}

int main()
{
    pthread_t h1;
    int contador;

    printf("Valor inicial del contador: \n");
	scanf("%i", &contador);

    pthread_create(&h1, NULL, (void *)&hilo1, NULL);

    while(var!=0)
    {
        printf("Valor del contador: %i\n", contador);
        sleep(1);
        contador=contador+var;
    }

    return 0;
}
#include <stdio.h>
#include <pthread.h>
#include <unistd.h>

void *hilo1(void)
{
    printf("Hola soy el hilo 1\n");
    sleep(5);
    printf("Hola soy el hilo 1\n");
}
void *hilo2(char *c)
{
    printf("Hola soy el hilo 2, y el caracter vale %c \n", *c);
}
void *hilo3(float *ff)
{
    printf("Hola soy el hilo 3, y el numero vale %f \n", *ff);
    *ff = *ff + 10;
    pthread_exit(ff);
}
int main()
{
    pthread_t h1, h2, h3;
    char ch = 'b';
    float param, *retorno;
    param = 10;
    printf("Hola soy la main y voy a lanzar el hilo 1 \n");
    pthread_create(&h1, NULL, (void *)&hilo1, NULL);
    pthread_create(&h2, NULL, (void *)&hilo2, &ch);
    pthread_create(&h3, NULL, (void *)&hilo3, &param);
    pthread_detach(h1);
    printf("Adios, me voy\n");
    pthread_join(h2, NULL);
    pthread_join(h3, (void *)&retorno);
    printf("Ahora si, y el hilo 3 retorna %f\n", *retorno);

    return 0;
}
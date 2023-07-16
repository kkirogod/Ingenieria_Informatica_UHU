#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <sys/msg.h>
#include <errno.h>
#include <sys/types.h>
#include <unistd.h>

struct mensaje
{
    long tipo;
    int num;
};

int main()
{
    key_t clave;
    int idCola, tiempo;
    struct mensaje msg;

    srand(getpid());

    // CREA LA COLA DE MENSAJES

    clave = ftok("./makefile", 33);
    if (clave == (key_t)-1)
    {
        printf("Error al obtener la clave para la cola mensajes\n");
        exit(-1);
    }

    idCola = msgget(clave, 0600 | IPC_CREAT);
    if (idCola == -1)
    {
        printf("Error al obtener el identificador para la cola mensajes\n");
        exit(-1);
    }

    msg.tipo = 1;

    for (int i = 0; i < 10; i++)
    {
        msg.num = (rand() % 100) + 1;
        tiempo = (rand() % 3) + 1;

        sleep(tiempo);

        msgsnd(idCola, (struct msgbuf *)&msg, sizeof(msg) - sizeof(long), IPC_NOWAIT);
    }

    return 0;
}
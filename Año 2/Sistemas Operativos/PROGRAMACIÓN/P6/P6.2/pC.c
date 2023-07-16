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
    int idCola;
    struct mensaje msg;

    srand(getpid());

    // CREA LA COLA DE MENSAJES C->B

    clave = ftok("./makefile", 66);
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

    for (int i = 1; i <= 5; i++)
	{
		sleep(rand() % 3 + 1);
		msg.tipo = i;
		msg.num = i;
		msgsnd(idCola, (struct msgbug *)&msg, sizeof(msg) - sizeof(long), 0);
	}

    return 0;
}
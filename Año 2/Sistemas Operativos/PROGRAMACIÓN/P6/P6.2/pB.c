#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <sys/msg.h>
#include <errno.h>
#include <sys/types.h>
#include <unistd.h>
#include <sys/wait.h>

struct mensaje
{
    long tipo;
    int num;
};

int main()
{
    key_t clave;
    struct mensaje msg;

    // CREA LA COLA DE MENSAJES A->B

    clave = ftok("./makefile", 33);
    if (clave == (key_t)-1)
    {
        printf("Error al obtener la clave para la cola mensajes\n");
        exit(-1);
    }

    int idColaAB = msgget(clave, 0600 | IPC_CREAT);
    if (idColaAB == -1)
    {
        printf("Error al obtener el identificador para la cola mensajes\n");
        exit(-1);
    }

    // CREA LA COLA DE MENSAJES C->B

    clave = ftok("./makefile", 66);
    if (clave == (key_t)-1)
    {
        printf("Error al obtener la clave para la cola mensajes\n");
        exit(-1);
    }

    int idColaCB = msgget(clave, 0600 | IPC_CREAT);
    if (idColaCB == -1)
    {
        printf("Error al obtener el identificador para la cola mensajes\n");
        exit(-1);
    }

    // CREA AL PROCESO C

    int pidC = fork();
    if (pidC == 0)
    {
        execl("pC", "pC", NULL);
        perror("no se puede ejecutar pC");
        exit(-1);
    }
    else if (pidC == -1)
        printf("Imposible hacer el fork\n");

    for (int i = 1; i <= 5; i++)
	{
        sleep(2);

        if (msgrcv(idColaAB, (struct msgbug *)&msg, sizeof(msg) - sizeof(long), (long)i, IPC_NOWAIT) != -1)
			printf("A pone a tiempo el %i\n", i);
		else
			printf("A NO pone a tiempo el %i\n", i);

		if (msgrcv(idColaCB, (struct msgbug *)&msg, sizeof(msg) - sizeof(long), (long)i, IPC_NOWAIT) != -1)
			printf("C pone a tiempo el %i\n", i);
		else
			printf("C NO pone a tiempo el %i\n", i);
    }

    wait(NULL);

    msgctl(idColaCB, IPC_RMID, (struct msqid_ds *)NULL);

    return 0;
}
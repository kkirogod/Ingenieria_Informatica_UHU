#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <sys/msg.h>
#include <errno.h>
#include <unistd.h>
#include <sys/types.h>

struct mensaje
{
    long tipo;
    int num;
};

int main()
{
    key_t clave;
    int idCola;
    struct mensaje msgB, msgC;

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

    // CREA LOS PROCESOS B Y C

    int pidB = fork();
    if (pidB == 0)
    {
        execl("pB", "pB", NULL);
        perror("no se puede ejecutar pB");
        exit(-1);
    }
    else if (pidB == -1)
        printf("Imposible hacer el fork\n");

    int pidC = fork();
    if (pidC == 0)
    {
        execl("pC", "pC", NULL);
        perror("no se puede ejecutar pC");
        exit(-1);
    }
    else if (pidC == -1)
        printf("Imposible hacer el fork\n");

    for (int i = 0; i < 10; i++)
    {
        msgrcv(idCola, (struct msgbuf *)&msgB, sizeof(msgB) - sizeof(long), 1, 0);
        printf("B: %d\n", msgB.num);

        msgrcv(idCola, (struct msgbuf *)&msgC, sizeof(msgC) - sizeof(long), 2, 0);
        printf("C: %d\n", msgC.num);
    }

    msgctl(idCola, IPC_RMID, (struct msqid_ds *)NULL);

    return 0;
}
#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <signal.h>
#include <sys/types.h>
#include <sys/wait.h>
#include <sys/ipc.h>
#include <sys/msg.h>

#include "comun.h"

int llega10=0;

void f10()
{
    llega10=1;
}

int main()
{
    signal(10, f10);

    //crear cuerpo y cabeza aleatoriamente

    srand(getpid());

    int cabeza=rand()%MAXCABEZAS;
    int cuerpo=rand()%MAXCUERPOS;

    //abrir la cola para el servidor grafico

    key_t clave = ftok("./fichcola.txt", 18);

    if (clave == (key_t)-1)
        printf("ERROR: clave\n");

    int cola = msgget(clave, 0600 | IPC_CREAT);

    if (cola == -1)
        printf("ERROR: cola\n");

    //pintar en la cola de LLEGADA

    struct tipo_elemento peticion;

    peticion.tipo = 1;
    peticion.pid = getpid();
    peticion.donde = VCOLALLEGAR;
    peticion.que = PINTAR;
    peticion.cualidad = TIPOTODO;
    peticion.cabeza = cabeza;
    peticion.cuerpo = cuerpo;

    // Enviar mensaje

    msgsnd(cola, (struct msgbuf *)&peticion, sizeof(peticion) - sizeof(long), 0);

    // Comprobamos si ha pintado correctamente

    if(!llega10)
        pause();

    //reiniciar llega10 para futuros mensajes

    llega10=0;

    //esperar un poco

    sleep(rand()%6+5);

    //borrar de la cola de llegada

    peticion.tipo = 1;
    peticion.pid = getpid();
    peticion.donde = VCOLALLEGAR;
    peticion.que = BORRAR;             //es lo unico que se cambia
    peticion.cualidad = TIPOTODO;
    peticion.cabeza = cabeza;
    peticion.cuerpo = cuerpo;

    msgsnd(cola, (struct msgbuf *)&peticion, sizeof(peticion) - sizeof(long), 0);

    //al BORRAR no se espera NINGUNA señal

    //pintar en la cola de ABURRIDOS

    peticion.tipo = 1;
    peticion.pid = getpid();
    peticion.donde = VABURRIDO;
    peticion.que = PINTAR;
    peticion.cualidad = TIPOTODO;
    peticion.cabeza = cabeza;
    peticion.cuerpo = cuerpo;

    msgsnd(cola, (struct msgbuf *)&peticion, sizeof(peticion) - sizeof(long), 0);

    if(!llega10)
        pause();

    return 0;
}
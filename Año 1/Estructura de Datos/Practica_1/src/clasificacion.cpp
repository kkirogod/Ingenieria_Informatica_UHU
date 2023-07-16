#include "clasificacion.h"
#include <iostream>
#include <stdlib.h>
#include <time.h>

using namespace std;

Clasificacion::Clasificacion()
{
    participantes = 0;
    tamano = 0;
    elementos = NULL;
    ordenado = false;
}

void Clasificacion::anadirparticipante(Participante a)
{
    if(participantes == tamano)
    {
        Participante *nuevo = new Participante[tamano + SALTO];

        if(nuevo != NULL)
        {
            for(int i = 0; i < participantes;i++)
                nuevo[i] = elementos[i];

            if(elementos != NULL) //SI ELEMENTOS = NULL E HICIESÉMOS: DELETE [] ELEMENTOS -> PANTALLAZO AZUL!!!!
                delete [] elementos;

            elementos = nuevo;

            tamano += SALTO;
        }
        else
            cout << "ERROR: No queda memoria en el sistema. \n";
    }

    if(participantes < tamano)
    {
        elementos[participantes] = a;
        participantes++;
    }

}

void Clasificacion::eliminar(int i)
{
    if(i < 0 || i >= participantes)
        cout << "ERROR: Posicion no valida.\n";
    else
    {
        participantes--;

        while(i < participantes)
        {
            elementos[i] = elementos[i + 1];

            i++;
        }
    }
}

Participante Clasificacion::consultar(int i)
{
    return elementos[i];
}

bool Clasificacion::vacio()
{
    return (participantes==0);
}

int Clasificacion::numparticipantes()
{
    return participantes;
}

void Clasificacion::ordenar()
{
    for (int i = 0; i < (participantes - 1); i++)
        for (int j = (participantes - 1); j > i; j--)
            if(elementos[j].marca < elementos[j-1].marca)
            {
                Participante tmp = elementos[j];
                elementos[j] = elementos[j-1];
                elementos[j-1] = tmp;
            }
    ordenado = true;
}

int Clasificacion::buscarcorredor(int dorsal)//ÉSTE MÉTODO ESTÁ IMPLEMENTADO DENTRO DEL MÉTODO Clasificacioncarrera() DE LA CLASE PRUEBA
{
    Participante p;
    bool encontrado = false;
    int i = 0;

    while(!encontrado && i < participantes)
    {
        p = consultar(i);

        if(p.dorsal == dorsal)
            encontrado = true;
        else
            i++;
    }

    if(!encontrado)
        i = -1;
    else
        i++;

    return i;
}

Clasificacion::~Clasificacion()
{
    if(elementos != NULL)
        delete [] elementos;
}

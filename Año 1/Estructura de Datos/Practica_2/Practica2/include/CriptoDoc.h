#ifndef CRIPTODOC_H
#define CRIPTODOC_H

#include "lista.h"
#include <iostream>
#include <cstring>
#include <fstream>
#include <string>

using namespace std;

typedef string linea;

class CriptoDoc
{
    lista texto;
public:
    bool leer(char fichero[]);
    bool escribir(char fichero[]);
    void cifrar(int codigo);
    void descifrar(int codigo);
    void vaciar();
    void concatenar(CriptoDoc &doc);
    int numlineas();
    linea observar(int i);
    void insertarlinea(linea e);
};

#endif // CRIPTODOC_H

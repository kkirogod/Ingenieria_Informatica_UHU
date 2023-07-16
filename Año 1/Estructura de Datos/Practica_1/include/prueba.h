#ifndef PRUEBA_H
#define PRUEBA_H

#include <fstream>
#include <cstring>

#include "clasificacion.h"

using namespace std;

struct Ciclista {
 int dorsal;
 cadena pais;
 cadena nombre;
 cadena apellidos;
 int marca;
 int posicion;//consideramos que el primer ciclista está en la posición 1
};

class Prueba {
        fstream fich; //fichero primera fase
        fstream fichero; //fichero segunda fase
        fstream fichres; //fichero modificacion examen
        int numCiclistas;
        bool carrera_hecha;

public:
         Prueba(char FicheroOrigen[],char FicheroDestino[]);
         ~Prueba();                                                 //para cerrar fichero
         int getNumCiclistas();
         void mostrar(cadena pais);
         Ciclista consultar(int posicion);
         int buscar(int dorsal);
         void insertar(Ciclista c);
         void modificar(Ciclista c, int posicion);
         void eliminar(int posicion);
         void Clasificacioncarrera();
         void MarcaSup();
};

#endif // PRUEBA_H

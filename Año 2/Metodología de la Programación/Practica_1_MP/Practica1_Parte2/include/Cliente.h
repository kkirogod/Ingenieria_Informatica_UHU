#ifndef CLIENTE_H
#define CLIENTE_H

#include <iostream>
#include "Fecha.h"
#include <cstring>

using namespace std;

class Cliente
{
    long int dni;
    char *nombre;
    Fecha fechaAlta;

    public:
        Cliente(long int d, char *nom, Fecha f);
        virtual ~Cliente();
        //Cliente(const Cliente &c); //constructor de copia
        Cliente &operator=(const Cliente &c); //operator =
        long int getDni () const {return dni;}
        const char *getNombre() const {return nombre;} //FALLO EN SEGURIDAD
        Fecha getFecha() const {return fechaAlta;} //FALLO EN SEGURIDAD
        void setNombre(char *nom);
        void setFecha(Fecha f);

        bool operator==(Cliente c) const;
};

ostream& operator<<(ostream& s, const Cliente &o);

#endif // CLIENTE_H

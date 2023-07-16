#include "Persona.h"

Persona::Persona(const string& n, int e)
{
    nombre=n;
    edad=e;
}

const string& Persona::getNombre() const
{
    return nombre;
}

int Persona::getEdad() const
{
    return edad;
}

void Persona::setNombre(const string& n)
{
    nombre=n;
}

void Persona::setEdad(int e)
{
    edad=e;
}

bool Persona::operator==(const Persona& p) const
{
    if(p.edad==edad && p.nombre==nombre)
        return true;
    else
        return false;
}

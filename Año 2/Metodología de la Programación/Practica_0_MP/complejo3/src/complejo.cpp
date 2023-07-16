#include "complejo.h"
#include <iostream>

using namespace std;


complejo::complejo(int r, int i)
{
    real = r;
    imaginario = i;
}

complejo::complejo(const complejo &c)
{
    real = c.real;
    imaginario = c.imaginario;
}


void complejo::set(int real, int imaginario)
{
    this->real = real;
    this->imaginario = imaginario;
}

void complejo::set()
{
    cout << "Introduce la parte real: ";
    cin >> real;

    cout << endl << "Introduce la parte imaginaria: ";
    cin >> imaginario;
}

void complejo::ver() const
{
    if(imaginario >= 0)
    {
        cout << "z = " << real << " +" << imaginario << "i" << endl;
    }
    else
        cout << "z = " << real << " " << imaginario << "i" << endl;
}


complejo complejo::operator+(complejo c) const
{
    complejo suma(real + c.real, imaginario + c.imaginario);

    return suma;
}

complejo complejo::operator+(int i) const
{
    complejo suma(real + i, imaginario);

    return suma;
}

complejo complejo::operator-() const
{
    return complejo(-real, -imaginario);
}

complejo operator+(int i, complejo c)
{
    complejo suma(c.getr() + i, c.geti());

    return suma;

    //ALTERNATIVA: return c+i;
}

ostream& operator<<(ostream& s, const complejo &c) //IMPORTANTE APRENDER ESTA CABECERA
{
    if(c.imaginario >= 0)
    {
        s << "z = " << c.real << " +" << c.imaginario << "i"; //MUY SIMILAR AL MÉTODO ver(); [cout -> s]
    }
    else
        s << "z = " << c.real << " " << c.imaginario << "i"; //NO PONER: "endl" EN LOS "operator<<"

    return s;
}

complejo complejo::operator++()
{
    real++;

    return (*this);
}

complejo complejo::operator++(int notused)
{
    complejo copia(*this);
    real++;

    return copia;
}

bool complejo::operator==(complejo c) const
{
    if(real == c.real && imaginario == c.imaginario)
    {
        return true;
    }

    return false;
}

complejo::operator int(){
    return real;
}

bool complejo::operator==(int i) const {
    if (real==i && imaginario==0)
    return true;
   else
    return false;

   //return (real==i && imaginario==0);
}

#ifndef FECHA_H
#define FECHA_H

#include <iostream>

using namespace std;

class Fecha
{
    int dia, mes, anio;

    public:
        Fecha(const int &d, const int &m, const int &a);
        int getDia() const {return dia;}
        int getMes() const {return mes;}
        int getAnio() const {return anio;}
        void setFecha(const int &d, const int &m, const int &a);
        bool bisiesto() const ;
        void ver() const;

        Fecha operator++(); //++f
        Fecha operator++(int notused); //f++
        Fecha operator+(const int &i) const; //f+i

        friend Fecha operator+(const int &i, const Fecha &f); //5+i
        friend ostream& operator<<(ostream &s, const Fecha &f);
};

#endif // FECHA_H

#include "Fecha.h"

Fecha::Fecha(const int &d, const int &m, const int &a)
{
    setFecha(d, m, a);
}


void Fecha::setFecha(const int &d, const int &m, const int &a)
{
    int dias[] = {0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

    anio = a;

    if(bisiesto())
        dias[2] = 29;


    if(m < 1)
        mes = 1;
    else if(m > 12)
        mes = 12;
    else
        mes = m;


    if(d < 1)
        dia = 1;
    else if (d > dias[mes])
        dia = dias[mes];
    else
        dia = d;
}


bool Fecha::bisiesto() const
{
    if(anio%400==0 || (anio%4==0 && anio%100!=0))
        return true;
    else
        return false;
}


void Fecha::ver() const
{
    if (dia < 10)
        cout << "0";

    cout << dia << "/";

    if (mes < 10)
        cout << "0";

    cout << mes << "/" << anio;
}


Fecha Fecha::operator++() //++f
{
    int dias[] = {0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

    if(bisiesto())
        dias[2] = 29;

    if(dia==dias[mes])
    {
        dia=1;

        if(mes==12)
        {
            mes=1;
            anio++;
        }
        else
            mes++;
    }
    else
        dia++;


    return *this;
}


Fecha Fecha::operator++(int notused) //f++
{
    Fecha copia(dia, mes, anio);

    ++(*this);

    return copia;
}


Fecha Fecha::operator+(const int &i) const //f+i
{
    Fecha suma(dia, mes, anio);

    for (int n=1; n<=i; n++)
        ++suma;

    return suma;
}


Fecha operator+(const int &i, const Fecha &f) //5+i
{
    return f+i;
}

ostream& operator<<(ostream &s, const Fecha &f)
{
    const char *meses[] = {"", "ene", "feb", "mar", "abr", "may", "jun", "jul", "ago", "sep", "oct", "nov", "dic"};

    if (f.dia < 10)
        s << "0";

    s << f.dia << " " << meses[f.mes] << " " << f.anio;

    return s;
}

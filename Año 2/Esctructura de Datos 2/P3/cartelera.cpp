#include "cartelera.h"
#include <iostream>

using namespace std;

Cartelera::Cartelera(): espectaculos() {}

void Cartelera::insertaEspectaculo(const string& e)
{
    DSalas salas;
    PEspectaculos par;

    par.first=e;
    par.second=salas;

    espectaculos.insert(par);
}

void Cartelera::insertaSala(const string& e, const string& s, const string& c)
{
    PSalas par;
    par.first=s;
    par.second=c;

    espectaculos[e].insert(par);
}

void Cartelera::eliminaEspectaculo(const string& e)
{
    espectaculos.erase(e);
}

void Cartelera::eliminaSala(const string& e, const string& s)
{
    if(espectaculos.find(e)!=espectaculos.end())
        espectaculos[e].erase(s);
}

void Cartelera::listaEspectaculos()
{
    for (DEspectaculos::iterator it = espectaculos.begin(); it != espectaculos.end(); it++)
    {
        cout << it -> first << endl;
    }
}

void Cartelera::listaSalas(const string& e)
{
    //DEspectaculos::iterator it = espectaculos.find(e);
    //DSalas salas = it -> second;

    for (DSalas::iterator it2 = espectaculos.find(e)->second.begin(); it2 != espectaculos.find(e)->second.end(); it2++)
    {
        cout << it2 -> first << endl;
        cout << it2 -> second << endl << endl;
    }
}


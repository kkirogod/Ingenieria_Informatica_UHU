#ifndef _CARTELERA_H_
#define _CARTELERA_H_

#include <map>
#include <utility>
#include <string>

using namespace std;

class Cartelera {
      public:
             Cartelera();
             void insertaEspectaculo(const string& e);
             void insertaSala(const string& e, const string& s, const string& c);
             void eliminaEspectaculo(const string& e);
             void eliminaSala(const string& e, const string& s);
             void listaEspectaculos();
             void listaSalas(const string& e);
      private:
              typedef map<string, string> DSalas; // NOMBRE SALA - CIUDAD
              typedef pair<string, string> PSalas;
              typedef map<string, DSalas> DEspectaculos; // TIPO ESPECT�CULO - DSALAS
              typedef pair<string, DSalas> PEspectaculos;
              DEspectaculos espectaculos;
};


#endif

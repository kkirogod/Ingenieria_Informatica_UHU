#ifndef CONTRATOTP_H
#define CONTRATOTP_H

#include <iostream> //cin, cout
#include "Fecha.h"
#include "Contrato.h"

using namespace std;

class ContratoTP: public Contrato {
  static int minutosTP;
  static float precioTP;
  int minutosHablados;
  static const float precioExcesoMinutos;

public:
  ContratoTP(long int dni, Fecha f, int m);
  //virtual ~ContratoTP(); //�es necesario? pensar y reflexionad
  ContratoTP(const ContratoTP& c);  //�es necesario? pensar y reflexionad - SI, en el main() se usa
  //ContratoTP& operator=(const ContratoTP& c); //�es necesario? pensar y reflexionad

  static int getLimiteMinutos() { return ContratoTP::minutosTP; }
  static float getPrecio() { return ContratoTP::precioTP; }
  static void setTarifaPlana(int m, float p); //en el .cpp se pone la cabecera sin la palabra static
  float factura() const;
  int getMinutosHablados() const { return minutosHablados; }
  void setMinutosHablados(int m) { this->minutosHablados=m; }
  void ver() const;

};

ostream& operator<<(ostream &s, const ContratoTP &c);

#endif // CONTRATOTP_H

#ifndef CONTRATO_H
#define CONTRATO_H

#include <iostream> //cin, cout
#include "Fecha.h"

using namespace std;

class Contrato {
  static int contador;
  const int idContrato;
  long int dniContrato;
  Fecha fechaContrato;
public:
  Contrato(long int dni, Fecha f);
  Contrato(const Contrato& c); //�es necesario? pensar y reflexionad - SI, nos hace flata para ContratoMovil.cpp

//Contrato& operator=(const Contrato& c); //no es necesario y ademas no puede ser usado porque Contrato tiene un
                                          //atributo constante idContrato que no puede modificarse
                                          //no se puede usar el = en el main con objetos Contrato

  virtual ~Contrato();
  int getIdContrato() const { return this->idContrato; }
  long int getDniContrato() const { return this->dniContrato; }
  Fecha getFechaContrato() const { return this->fechaContrato; }
  void setFechaContrato(Fecha f) { this->fechaContrato=f; }
  void setDniContrato(long int dni) { this->dniContrato=dni; }
  virtual void ver(ostream &s) const; //VIRTUAL NO SE PONE EN EL .CPP
  virtual void nada() const = 0; //indico que este m�todo no lo vamos a implementar

  friend ostream& operator<<(ostream &s, const Contrato &c);
};

#endif // CONTRATO_H

#include "Contrato.h"
#include "ContratoMovil.h"

int Contrato::contador=1;

Contrato::Contrato(long int dni, Fecha f): idContrato(contador), fechaContrato(f) {
  //idContrato=contador; //ERROR es constante y debe ir en zona inicializadores
  Contrato::contador++;
  this->dniContrato=dni;
  //this->fechaContrato=f; //ERROR es tipo no primitivo y debe ir en zona inicializadores
}

Contrato::Contrato(const Contrato& c): idContrato(contador), fechaContrato(c.fechaContrato) {
  Contrato::contador++;
  this->dniContrato=c.dniContrato;
}

Contrato::~Contrato()
{

}

void Contrato::ver(ostream &s) const {
  cout << this->dniContrato << " (" << this->idContrato << " - ";
  this->fechaContrato.ver(); //llamo al ver del objeto fecha
  cout << ")";
}

ostream& operator<<(ostream &s, const Contrato &c) {
  c.ver(s);
  return s;
}

//RESTO DE METODOS Y FUNCIONES A RELLENAR POR EL ALUMNO...

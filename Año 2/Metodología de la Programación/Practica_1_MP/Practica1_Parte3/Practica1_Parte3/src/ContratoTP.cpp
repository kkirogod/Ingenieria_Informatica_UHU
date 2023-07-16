#include "ContratoTP.h"
#include "Contrato.h"

int ContratoTP::minutosTP=300;
float ContratoTP::precioTP=10;
const float ContratoTP::precioExcesoMinutos=0.15;


ContratoTP::ContratoTP(long int dni, Fecha f, int m): Contrato(dni, f)
{
    this->minutosHablados=m;
}


ContratoTP::ContratoTP(const ContratoTP& c):Contrato(c.getDniContrato(), c.getFechaContrato())
{
    this->minutosHablados=c.minutosHablados;
}


//static se pone en el .h (no se pone en el .cpp)
void ContratoTP::setTarifaPlana(int m, float p) {
  ContratoTP::minutosTP=m; //puedo poner minutosTP=m ...pongo ContratoTP::minutosTP para recordar que es estatico
  ContratoTP::precioTP=p;  //puedo poner precioTP=p  ...pongo ContratoTP::precioTP para recordar que es estatico
}

float ContratoTP::factura() const
{
    if(minutosHablados > ContratoTP::minutosTP)
        return precioTP+((minutosHablados - ContratoTP::minutosTP)*ContratoTP::precioExcesoMinutos);
    else
        return precioTP;
}

ostream& operator<<(ostream &s, const ContratoTP &c)
{
    s << (Contrato &)c; //IMPORTANTE: convierto el objeto c (ContratoMovil &) a objeto Contrato &
    // de esta forma se cree que es un objeto Contrato y muestra lo que indica el operator<< de Contrato
    //... y a continuacion solo "me preocupo" de mostrar lo que es exclusivo del hijo
    s << " " << c.getMinutosHablados() << "m, " << c.getLimiteMinutos() << "(" << c.getPrecio() << ") - ";
    s << c.factura() << "€";
    return s;
}

void ContratoTP::ver(ostream &s) const
{
    Contrato::ver(s); //IMPORTANTE: llamamos al ver que heredo de mi padre PARA QUE MUESTRE LO DEL PADRE
    //... y a continuacion solo "me preocupo" de mostrar lo que es exclusivo del hijo
    s << " " << this->minutosHablados << "m, " << ContratoTP::minutosTP << "(" << ContratoTP::precioTP << ") - "
    << factura() << "€";
}


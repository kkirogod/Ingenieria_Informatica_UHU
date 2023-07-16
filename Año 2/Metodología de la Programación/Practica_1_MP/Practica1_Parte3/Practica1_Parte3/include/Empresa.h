#ifndef EMPRESA_H
#define EMPRESA_H
#include "Fecha.h" //definicion clase Fecha
#include "Cliente.h" // definicion clase Cliente
#include "Contrato.h" // definicion de la clase Contrato
#include "ContratoTP.h" // definicion de la clase ContratoTP
#include "ContratoMovil.h" // definicion de la clase ContratoMovil
#include <typeinfo>

using namespace std;

class Empresa
{
    Cliente *clientes[100];                //array est�tico (tama�o 100) de punteros a Clientes
    int ncli;                              //para saber cu�ntos clientes hay en el array (al principio 0)
    const int nmaxcli;                     //constante que indica cu�ntos caben en el array clientes (100)
    Contrato **contratos;                  //array din�mico de punteros a Contratos (capacidad ilimitada)
    int ncon;                              //para saber cu�ntos Contratos hay en el array (al principio 0)
    int nmaxcon;                           //para saber cu�ntos caben en array Contratos. No es constante
                                           //porque var�a conforme la tabla se llena (10, 20, 40, 80...)
protected:                                 //m�todos auxiliares usados por los m�todos p�blicos
    int buscarCliente(long int dni) const; //si no existe devuelve -1 y si existe devuelve
                                           //la posici�n del cliente en el array clientes
    int altaCliente(Cliente *c);           //a�ade el cliente apuntado por c al array clientes
                                           //devuelve la posici�n donde lo mete (-1 si no cabe)
public:
    Empresa();
    virtual ~Empresa();
//EL CONTRUCTOR DE COPIA Y EL OPERADOR DE ASIGNACION NO LO IMPLEMENTAMOS
//PORQUE EXPLICITAMENTE SE INDICA EN LA PRACTICA QUE NO SE HAGA

    void ver() const;
    void crearContrato();
    bool cancelarContrato(int idContrato);  //true si el Contrato existe, false si no
    bool bajaCliente(long int dni);         //true si el Cliente existe, false si no
    int descuento (float porcentaje) const; //devuelve a cuantos aplica el descuento
    int nContratosTP() const;
    void cargarDatos();                     //crea 3 clientes y 7 contratos. metodo creado para no
};                                          //tener que meter datos cada vez que pruebo el programa
#endif // EMPRESA_H

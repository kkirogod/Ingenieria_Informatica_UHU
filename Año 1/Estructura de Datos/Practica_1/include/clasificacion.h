#ifndef CLASIFICACION_H
#define CLASIFICACION_H

using namespace std;

const int TAM_CADENA = 30;

typedef char cadena[TAM_CADENA];

#define SALTO 4

struct Participante {
    int indice; //consideramos que el primer participante está en la posición 1 (indice = 1), pero no significa que haya sido el más rápido
    int dorsal;
    int marca;
};

class Clasificacion {
    Participante  *elementos; //elementos de la tabla
    int participantes;//inicializar a 0
    int tamano; //inicializar a 4
    bool ordenado; //verifica que se ha ejecutado el método ordenar
 public:
    Clasificacion();
    ~Clasificacion();
    void anadirparticipante(Participante a);
    void eliminar(int i);
    Participante consultar(int i);
    bool vacio();
    int numparticipantes();
    void ordenar();
    int buscarcorredor(int dorsal);
};

#endif // CLASIFICACION_H

const int TAM_CADENA = 30;

typedef char cadena[TAM_CADENA];

#define SALTO 4

struct Ciclista {
	int dorsal;
	cadena pais;
	cadena nombre;
	cadena apellidos;
	int marca;
	int posicion;
};

struct Participante {
    int indice;
	int dorsal;
	int marca;
};


class Prueba {
	    fstream fich; //fichero primera fase
        fstream fichero; //fichero segunda fase
		int numCiclistas;
		
public:
		Prueba(char FicheroOrigen[],char FicheroDestino[]);
		~Prueba();
		int getNumCiclistas();
		void mostrar(cadena pais);
		Ciclista consultar(int posicion);
		int buscar(int dor);
		void insertar(Ciclista c);
		void modificar(Ciclista c, int posicion);
		void eliminar(int posicion);
		void Clasificacion();
};


class Clasificacion {
    	Participante *elementos; //elementos de la tabla
    	int Participantes;
    	int tamano;
 public:
    	Clasificacion(); 
    	~Clasificacion();
    	void anadirparticipante(Participante a);
    	void eliminar(int i);
    	Participante consultar(int n);
    	bool vacio();
    	int numparticipantees();
    	void ordenar();
};


int marcas(int s) {
    return (rand()%s + 7000);
}

// METODO DE ORDENACION BURBUJA de una clase vector con los elementos en el atributo celda
void vector::Burbuja(int inicio, int fin)
        {
        int pos,ele;
        for (pos=inicio; pos<fin; pos++)
            for (ele=fin; ele>pos; ele--)
                if(celda[ele-1]>celda[ele])
                    intercambiar(celda[ele-1],celda[ele]);//Función genérica que tendrá que ser implementada
        }

#include <iostream>
#include <stdlib.h>
#include <time.h>

#include "clasificacion.h"
#include "prueba.h"

//gets() -> leer cadenas de caracteres

using namespace std;

int main()
{
    Prueba p("huelvaextremapro.dat", "huelvaextrema.dat");

    int numciclistas;
    int opc, dorsal, pos, h, m, s;
    Ciclista c;
    cadena pais;
    srand(time(0));

    do
    {
        numciclistas = p.getNumCiclistas();

        cout << "Huelva Extrema \n"
             << "-------------------------------------------------------- \n"
             << "Ciclistas: " << numciclistas << "\n\n\n"
             << "\t\t 1. Consulta de inscripciones \n"
             << "\t\t 2. Inscripcion a la prueba \n"
             << "\t\t 3. Busqueda de una inscripcion \n"
             << "\t\t 4. Modificar datos de una inscripcion \n"
             << "\t\t 5. Eliminar una inscripcion \n"
             << "\t\t 6. Mostrar clasificacion \n"
             << "\t\t 7. Nueva \n" //Modificacion practica examen
             << "\t\t 8. Buscar corredor \n"
             << "\t\t 9. Salir \n\n"
             << "Indique la opcion deseada: ";

        cin >> opc;

        system("cls");

        switch(opc)
        {
        case 1:
            cout << "Introduce el nombre de un pais o * (para mostrar todos): ";
            fflush (stdin);
            gets(pais);

            if(numciclistas == 0)
                cout << "\nERROR: No hay ciclistas inscritos.";
            else
            {
                system("cls");
                p.mostrar(pais);
            }

            break;

        case 2:
            cout << "Rellene los datos de la inscripcion: \n\n"
                 << "\t Dorsal: ";
            cin >> c.dorsal;
            fflush (stdin);

            cout << "\n\t Pais: ";
            gets(c.pais);
            fflush (stdin);

            cout << "\n\t Nombre: ";
            gets(c.nombre);
            fflush (stdin);

            cout << "\n\t Apellidos: ";
            gets(c.apellidos);
            fflush (stdin);

            c.marca = 0;
            c.posicion = 0;

            p.insertar(c);

            numciclistas++;

            break;

        case 3:
            cout << "Introduce el dorsal del ciclista: ";
            cin >> dorsal;

            pos = p.buscar(dorsal); //devuelve la pos del ciclista

            if(pos == -1)
                cout << "\n\n ERROR: Ciclista no inscrito.";
            else
            {
                c = p.consultar(pos);

                cout << "Ciclista: " << c.nombre << " " << c.apellidos
                 << "\nDorsal: " << c.dorsal
                 << "\nPais: " << c.pais
                 << "\nPosicion: " << c.posicion
                 << "\nMarca: " << c.marca/3600 << ":" << (c.marca%3600)/60 << ":" << (c.marca%3600)%60 << endl;
            }
            break;

        case 4:
            cout << "Introduce el dorsal del ciclista: ";
            cin >> dorsal;
            fflush(stdin);

            pos = p.buscar(dorsal); //devuelve la pos del ciclista

            if(pos == -1)
                cout << "\n\n ERROR: Ciclista no inscrito.";
            else
            {
                c = p.consultar(pos);

                cout << "Rellene los datos de la inscripcion: \n"
                     << "\n\t Nombre: ";
                gets(c.nombre);
                fflush(stdin);

                cout << "\n\t Apellidos: ";
                gets(c.apellidos);
                fflush(stdin);

                cout << "\n\t Marca: \n"
                     << "\t\t - Horas: ";
                cin >> h;
                fflush(stdin);

                cout << "\t\t - Minutos: ";
                cin >> m;
                fflush(stdin);

                cout << "\t\t - Segundos: ";
                cin >> s;
                fflush(stdin);

                c.marca = ((h*3600)+(m*60)+(s));

                p.modificar(c, pos);
            }
            break;

        case 5:
            cout << "Introduce el dorsal del ciclista: ";
            cin >> dorsal;

            pos = p.buscar(dorsal); //devuelve la pos del ciclista

            if(pos == -1)
                cout << "\n\n ERROR: Ciclista no inscrito.";
            else
            {
                p.eliminar(pos);
            }
            break;

        case 6:
            p.Clasificacioncarrera();

            break;

        case 7:
            p.MarcaSup();

            break;

        case 8:
            cout << "Este metodo se utiliza para mostrar la posicion del ciclista al mostrar la clasificacion simulada. \n";

            break;

        case 9:
            break;

        default:
            cout << "\nERROR: Opcion no valida.";

            break;
        }
    cout << endl << endl;

    system("pause");
    system("cls");

    } while(opc != 9);

    return 0;
}

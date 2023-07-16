#include "prueba.h"
#include "clasificacion.h"
#include <iostream>
#include <time.h>
#include <stdlib.h>

using namespace std;

int randmarca(int smin)
{
    return ((rand() % 3600) + smin);
}

void mostrarmarcas(int tseg)//segundos que ha tardado en completar la prueba
{
    int h, m, s;
    h = tseg / 3600;
    m = (tseg % 3600) / 60;
    s = (tseg % 3600) % 60;

    cout << h << ":" << m << ":" << s << endl;
}

Prueba::Prueba(char FicheroOrigen[],char FicheroDestino[])
{
    fich.open(FicheroOrigen, ios::binary | ios::in);
    fichero.open(FicheroDestino, ios::binary | ios::out);

    numCiclistas = 0;
    carrera_hecha = false;

    fichero.seekp(sizeof(int), ios::beg);//dejamos al principio espacio para un entero que será el numero total de ciclistas

    if(fichero.fail())
        cout << "Error al abrir el fichero de destino.\n";

    if(fich.fail())
    {
        cout << "Error al abrir el fichero de origen.\n";

        fichero.seekp(0, ios::beg);
        fichero.write((char*) &numCiclistas, sizeof(int));

        fichero.close();
        fichero.open(FicheroDestino, ios::binary | ios::in | ios::out);

        fich.close();
    }
    else
    {
        cout << "Ficheros abiertos exitosamente.\n";

        int num = 0;
        Ciclista c;

        fich.seekg(0, ios::beg);
        fich.read((char*) &num, sizeof(int)); //pasamos a la variable num (por referencia) el numero de ciclistas que hay a continuación en la secuencia.

        while(!fich.eof())
        {
            numCiclistas = numCiclistas + num;

            for(int i = 0; i < num; i++)
            {
                fich.read((char*) &c, sizeof(c));
                fichero.write((char*) &c, sizeof(c));
            }
            fich.read((char*) &num, sizeof(int));
        }
        //escribimos al principio del fichero el numero total de ciclistas
        fichero.seekp(0, ios::beg);
        fichero.write((char*) &numCiclistas, sizeof(int));

        fichero.close();
        fichero.open(FicheroDestino, ios::binary | ios::in | ios::out);

        if(fichero.fail())
            cout << "Error al abrir el fichero de destino.\n";
        else
            cout << "Fichero de destino abierto exitosamente.\n\n";

        fich.close();
    }
}


int Prueba::getNumCiclistas()
{
    return numCiclistas;
}


void Prueba::mostrar(cadena pais)
{
    fichero.seekg(sizeof(int), ios::beg);
    Ciclista c;
    int i = 0;

    if(strcmp(pais, "*") != 0)
    {
        while(!fichero.eof() && i < numCiclistas)
        {
            fichero.read((char*) &c, sizeof(c));

            if(strcmp(c.pais, pais) == 0)
            {
                cout << "Ciclista: " << c.nombre << " " << c.apellidos
                     << "\nDorsal: " << c.dorsal
                     << "\nPais: " << c.pais
                     << "\nPosicion: " << c.posicion
                     << "\nMarca: " << c.marca;
                     mostrarmarcas(c.marca);
                cout << endl;
            }

            i++;
        }
    }
    else
    {
        while(!fichero.eof() && i < numCiclistas)
        {
            fichero.read((char*) &c, sizeof(c));

            cout << "Ciclista: " << c.nombre << " " << c.apellidos
                 << "\nDorsal: " << c.dorsal
                 << "\nPais: " << c.pais
                 << "\nPosicion: " << c.posicion
                 << "\nMarca: ";
                 mostrarmarcas(c.marca);
            cout << endl;

            i++;
        }
    }
}

Ciclista Prueba::consultar(int posicion)
{
    fichero.seekg(sizeof(int), ios::beg); //SIEMPRE NOS POSICIONAMOS ANTES DEL PRIMER CICLISTA PARA LEER LA SECUENCIA (para ello avanzamos el entero que nos dice el num de ciclistas)
    Ciclista c;

    if (posicion < 1 || posicion > numCiclistas)
        cout << "ERROR: Posicion no encontrada.";
    else
    {
        for(int i = 0; i < posicion; i++)
            fichero.read((char*) &c, sizeof(c));
    }

    return c;
}

int Prueba::buscar(int dorsal)
{
    fichero.seekg(sizeof(int), ios::beg);

    Ciclista c;
    bool encontrado = false;

    int pos = 1;

    do
    {
        fichero.read((char*) &c, sizeof(c));

        if(c.dorsal == dorsal)
            encontrado = true;
        else
            pos++;

    }while(!encontrado && !fichero.eof() && pos <= numCiclistas);

    if(!encontrado)
        pos = -1;

    return pos;
}

void Prueba::insertar(Ciclista c)
{
    if(buscar(c.dorsal) != -1)
        cout << "ERROR: Este ciclista ya esta inscrito. \n";
    else
    {
        Ciclista c1;
        int pos = 1;
        bool encontrado = false;

        //ALGORTIMO DE BUSQUEDA DEL PAIS AL QUE PERTENECE EL CICLISTA
        do
        {
            c1 = consultar(pos);

            if(strcmp(c1.pais, c.pais) == 0)
                encontrado = true;
            else
                pos++;

        }while(!encontrado && pos <= numCiclistas);

        if(!encontrado)//significa que el ciclista pertenecerá a un país no registrado, se le insertará al final
        {
            fichero.seekp(0, ios::end);
            fichero.write((char*) &c, sizeof(c));
        }
        else
        {
            int i = (pos - 1);

            while(i < numCiclistas)
            {
                fichero.seekg(sizeof(int), ios::beg);
                fichero.seekg((sizeof(c) * i), ios::cur);
                fichero.read((char*) &c1, sizeof(c1));

                fichero.seekp(sizeof(int), ios::beg);
                fichero.seekp((sizeof(c) * i), ios::cur);
                fichero.write((char*) &c, sizeof(c));

                c = c1;

                i++;
            }

            fichero.write((char*) &c, sizeof(c));
        }

        numCiclistas++;
    }
}

void Prueba::modificar(Ciclista c, int posicion)
{
    if(buscar(c.dorsal) == -1)
        cout << "ERROR: Este ciclista no esta inscrito. \n";
    else
    {
        Ciclista c1;

        c1 = consultar(posicion);

        if(strcmp(c.pais, c1.pais) != 0)
            cout << "ERROR: Los paises de origen no coinciden. \n";
        else
        {
            fichero.seekp(sizeof(int), ios::beg);
            fichero.seekp((sizeof(c) * (posicion - 1)), ios::cur);
            fichero.write((char*) &c, sizeof(c));
        }
    }
}

void Prueba::eliminar(int posicion)
{
    if(posicion > numCiclistas || posicion < 1)
        cout << "ERROR: La posicion no existe. \n";
    else
    {
        int i = posicion;
        Ciclista c;

        while(i < numCiclistas)
        {
            fichero.seekg(sizeof(int), ios::beg);
            fichero.seekg((sizeof(c) * i), ios::cur);
            fichero.read((char*) &c, sizeof(c));

            fichero.seekp(sizeof(int), ios::beg);
            fichero.seekp((sizeof(c) * (i - 1)), ios::cur);
            fichero.write((char*) &c, sizeof(c));

            i++;
        }

        numCiclistas--;
        cout << "Inscripcion eliminada con exito!";
    }
}

void Prueba::Clasificacioncarrera()
{
    Ciclista c;
    Clasificacion clasi;
    Participante p;
    int pos;
    int smin = (3600*4); //4 PQ SECONSIDERA QUE EL CICLISTA TARDARÁ COMO MÍNIMO 4 HORAS

    for(int i = 1; i <= numCiclistas; i++)
    {
        c = consultar(i);

        p.dorsal = c.dorsal;
        p.marca = randmarca(smin);
        p.indice = i;

        clasi.anadirparticipante(p);
    }
    clasi.ordenar();

    for(int i = 0; i < numCiclistas; i++)
    {
        p = clasi.consultar(i);
        pos = buscar(p.dorsal);
        c = consultar(pos);
        c.posicion = i+1;
        c.marca = p.marca;
        modificar(c, pos);

        cout << clasi.buscarcorredor(c.dorsal) << endl
             << "Ciclista: " << c.nombre << " " << c.apellidos
             << "\nDorsal: " << c.dorsal
             << "\nPais: " << c.pais
             << "\nMarca: ";
             mostrarmarcas(c.marca);

        cout << endl;
    }
    carrera_hecha = true;
}

void Prueba::MarcaSup()
{
    int h, m, s, mcorte;
    Ciclista c;

    if(!carrera_hecha)
        cout << "ERROR: Primero debe simular la carrera (opcion 6).";
    else
    {
        fichres.open("reshuelva.dat", ios::binary | ios::out);

        if(fichres.fail())
            cout << "Error al abrir el fichero.";
        else
        {
            cout << "Fichero abiertos exitosamente!\n\n"
                 << "Introduce la marca de corte: \n"
                 << "\t - Horas: ";
            cin >> h;
            fflush(stdin);

            cout << "\t - Minutos: ";
            cin >> m;
            fflush(stdin);

            cout << "\t - Segundos: ";
            cin >> s;
            fflush(stdin);

            mcorte = ((h*3600)+(m*60)+(s));

            fichres.seekp(sizeof(int), ios::beg);

            int num = 0;

            for(int i = 1; i <= numCiclistas; i++)
            {
                c = consultar(i);

                if(c.marca < mcorte)
                {
                    fichres.write((char*) &c, sizeof(c));

                    num++;
                }
            }
            fichres.seekp(0, ios::beg);
            fichres.write((char*) &num, sizeof(int));


            //PARA COMPROBAR QUE FUNCIONA:
            /*
            fichres.close();
            fichres.open("reshuelva.dat", ios::binary | ios::in);
            fichres.seekg(sizeof(int), ios::beg);

            int i = 0;

            cout << "Numero de ciclistas que han pasado el corte: " << num << endl << endl;

            while(!fichres.eof() && i < num)
            {
                fichres.read((char*) &c, sizeof(c));

                cout << "Ciclista: " << c.nombre << " " << c.apellidos
                     << "\nDorsal: " << c.dorsal
                     << "\nPais: " << c.pais
                     << "\nPosicion: " << c.posicion
                     << "\nMarca: ";
                     mostrarmarcas(c.marca);
                cout << endl;

                i++;
            }*/
        }
    }
}


Prueba::~Prueba()
{
    fichres.close();
    fichero.close();
}


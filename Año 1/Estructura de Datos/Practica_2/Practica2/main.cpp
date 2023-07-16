#include <iostream>
#include <string>
#include "CriptoDoc.h"
#include "pila.h"

using namespace std;

typedef char cadena[50];

int menu();

int main()
{
    cadena nombre1, nombre2;
    CriptoDoc doc;
    int op, codigo;
    bool p = true;

    do
    {
        op = menu();

        system("cls");

        switch(op)
        {
        case 1:
            fflush(stdin);

            cout << "Introduce el nombre del documento a cifrar: ";
            gets(nombre1);
            fflush(stdin);

            cout << "Introduce el nombre del documento resultante: ";
            gets(nombre2);
            fflush(stdin);

            if(doc.leer(nombre1) == true)
            {
                do
                {
                    cout << "Introduce el codigo que desea emplear para cifrar (debe pertenecer al intervalo [100, 200]): ";
                    cin >> codigo;
                }
                while(codigo < 100 || codigo > 200);

                doc.cifrar(codigo);

                if(doc.escribir(nombre2) == true)
                {
                    cout << "Documento resultante creado con exito.";

                    if(p)
                    {
                        ofstream log("log", ios::trunc);

                        if(log.fail())
                            cout << "ERROR: No se pudo abrir el fichero log." << endl;

                        else
                        {
                            log << nombre2 << " " << codigo << endl;
                            log.close();

                            p=false;
                        }
                    }
                    else
                    {
                        ofstream log("log", ios::app);

                        if(log.fail())
                            cout << "ERROR: No se pudo abrir el fichero log." << endl;

                        else
                        {
                            log << nombre2 << " " << codigo << endl;
                            log.close();
                        }
                    }
                }
                else
                    cout << "ERROR: Fallo al abrir el fichero de salida.";

                doc.vaciar();
            }
            else
                cout << "ERROR: Fallo al abrir el fichero de entrada.";

            break;

        case 2:
            fflush(stdin);

            cout << "Introduce el nombre del documento a descifrar: ";
            gets(nombre1);
            fflush(stdin);

            cout << "Introduce el nombre del documento resultante: ";
            gets(nombre2);
            fflush(stdin);

            if(doc.leer(nombre1) == true)
            {
                do
                {
                    cout << "Introduce el codigo que desea emplear para descifrar (debe pertenecer al intervalo [100, 200]): ";
                    cin >> codigo;
                }
                while(codigo < 100 || codigo > 200);

                doc.descifrar(codigo);

                if(doc.escribir(nombre2) == true)
                    cout << "Documento resultante creado con exito.";
                else
                    cout << "ERROR: Fallo al abrir el fichero de salida.";

                doc.vaciar();
            }
            else
                cout << "ERROR: Fallo al abrir el fichero de entrada.";

            break;

        case 3:
            if(doc.leer("fichCodigos") == true)
            {
                doc.descifrar(123);

                int cod1 = stoi(doc.observar(1));
                int cod2 = stoi(doc.observar(2));
                int cod3 = stoi(doc.observar(3));

                doc.vaciar();

                if(doc.leer("doc-1") == true)
                {
                    doc.descifrar(cod1);

                    CriptoDoc doc2;

                    if(doc2.leer("doc-2") == true)
                    {
                        doc2.descifrar(cod2);
                        doc.concatenar(doc2);

                        CriptoDoc doc3;

                        if(doc3.leer("doc-3") == true)
                        {
                            doc3.descifrar(cod3);
                            doc.concatenar(doc3);

                            if(doc.escribir("docDescifrado.txt") == true)
                            {
                                cout << "Documento resultante creado con exito.";
                            }
                            else
                                cout << "ERROR: Fallo al abrir el fichero resultante (docDescifrado).";
                        }
                        else
                            cout << "ERROR: Fallo al abrir el tercer fichero (doc-3) a descifrar.";
                    }
                    else
                        cout << "ERROR: Fallo al abrir el segundo fichero (doc-2) a descifrar.";
                }
                else
                    cout << "ERROR: Fallo al abrir el primer fichero (doc-1) a descifrar.";
            }
            else
                cout << "ERROR: Fallo al abrir el fichero de entrada.";

            break;

        case 4:
            if(doc.leer("fichCodigos") == true)
            {
                doc.descifrar(123);
                int lon = doc.numlineas();
                pila pcod;

                for(int i=0; i<lon; i++)
                {
                    pcod.apilar(stoi(doc.observar(i+1)));
                }

                doc.vaciar();

                linea lcod;

                while(!pcod.esvacia())
                {
                    lcod = to_string(pcod.cima());
                    doc.insertarlinea(lcod);
                    pcod.desapilar();
                }

                doc.cifrar(123);

                if(doc.escribir("fichCodigos") == true)
                    cout << "Documento invertido con exito.";

                else
                    cout << "ERROR: Fallo al reescribir el fichero de codigos.";

                /*ofstream f("fichCodigosInv");

                if(!f.fail())
                {
                    linea lcod;

                    while(!pcod.esvacia())
                    {
                        lcod = to_string(pcod.cima());
                        f << lcod << endl;
                        pcod.desapilar();
                    }
                    doc.vaciar();

                    if(doc.leer("fichCodigosInv") == true)
                    {
                        doc.cifrar(123);

                        if(doc.escribir("fichCodigosInv") == true)
                            cout << "Documento final creado con exito.";
                        else
                            cout << "ERROR: Fallo al escribir el fichero final de codigos.";

                        doc.vaciar();
                    }
                    else
                        cout << "ERROR: Fallo al leer el fichero final de codigos.";
                }
                else
                    cout << "ERROR: Fallo al abrir el fichero final de codigos.";*/
            }
            else
                cout << "ERROR: Fallo al abrir el fichero de codigos.";

            break;

        case 5:
        {
            ifstream log("log");

            log >> nombre2 >> codigo;

            while(!log.eof())
            {
                cout << nombre2 << " " << codigo << endl;

                log >> nombre2 >> codigo;
            }
            log.close();

            break;
        }
        case 6:
            break;

        default:
            cout << "ERROR: Opcion no valida.";
        }
        cout << endl;
        system("pause");
        system("cls");

    }
    while(op!=6);

    return 0;
}

int menu()
{
    int op;

    cout << "CriptoDoc" << endl
         << "-----------" << endl << endl
         << "\t 1. Cifrar documento" << endl
         << "\t 2. Descifrar documento" << endl
         << "\t 3. Descifrar varios documentos" << endl
         << "\t 4. Invertir fichero de codigos" << endl
         << "\t 5. Visualizar log" << endl
         << "\t 6. Salir" << endl << endl
         << "Indique la opcion deseada: ";

    cin >> op;

    return op;
}

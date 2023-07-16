//FALTA PONER LAS CONDICIONES DE VICTORIA

#include <iostream>
#include <stdlib.h>
#include <time.h>


using namespace std;

int main()
{
    char nombre [15], ficha;
    int num, numrandf, numrandc, f, c;
    bool endgame= true;
    srand(time(0));

    cout << "Introduzca su nombre: ";
    cin >> nombre;

    cout << endl << "Bienvenid@ " << nombre;

    do
    {
        cout << endl << "Elija el numero de filas y columnas del juego (entre 3 y 6): ";
        cin >> num;
    } while (num<3 && num>6);

    //Creación del tablero de juego:
    char tablero[num][num];

    for (int i=0; i<num; i++)
        for (int j=0; j<num; j++)
        {
            tablero[i][j]= '*';
        }

    do
    {
    cout << endl << "Elija X o O (empieza quien elija X): ";
    cin >> ficha;

    if (ficha!= 'X' && ficha!='O')
        cout << "Elije solo X o O" << endl;

    } while (ficha!= 'X' && ficha!='O');

    cout << "TABLERO: " << endl << endl;
    for (int i=0; i<num; i++)
        for (int j=0; j<num; j++)
        {
            cout << "\t" << tablero[i][j];

            if (j == (num-1))
                cout << endl;
        }


    if (ficha == 'X')
    {
        do
        {
            //Tu jugada:
            do
            {
                cout << endl << "Seleccione el lugar que quiere cubrir: " << endl;
                do
                {
                cout << "Fila: ";
                cin >> f;
                } while (f<0 || f>(num-1));

                do
                {
                cout << "Columna: ";
                cin >> c;
                } while (c<0 || c>(num-1));

                if (tablero[f][c] == 'X' || tablero[f][c] == 'O')
                    cout << endl << "Ese hueco ya esta ocupado" << endl;

            } while (tablero[f][c] == 'X' || tablero[f][c] == 'O');

            tablero[f][c]= 'X';

            //Mostramos tablero:
            cout << "TABLERO: " << endl << endl;
            for (int i=0; i<num; i++)
                for (int j=0; j<num; j++)
                {
                    cout << tablero[i][j] << "\t";

                    if (j == (num-1))
                        cout << endl;
                }

            //Jugada random de la CPU:
            do
            {
                numrandf= rand()%num;
                numrandc= rand()%num;

                if (tablero[numrandf][numrandc] == '*')
                    tablero[numrandf][numrandc]= 'O';

            } while (tablero[numrandf][numrandc] != 'O');

            //Mostramos tablero:
            cout << "TABLERO: " << endl << endl;
            for (int i=0; i<num; i++)
                for (int j=0; j<num; j++)
                {
                    cout << tablero[i][j] << "\t";

                    if (j == (num-1))
                        cout << endl;
                }

            //Comprobamos si el juego ha acabado:
            for (int i=0; i<num; i++)
                for (int j=0; j<num; j++)
                {
                    if (tablero[i][j] == '*')
                        endgame= false;
                }

        } while (endgame == false);
    }

    //Caso ficha == 'O':
    else
    {
        do
        {
            //Jugada random de la CPU:
            do
            {
                numrandf= rand()%num;
                numrandc= rand()%num;

                if (tablero[numrandf][numrandc] == '*')
                    tablero[numrandf][numrandc]= 'O';

            } while (tablero[numrandf][numrandc] != 'O');


            //Mostramos tablero:
            cout << "TABLERO: " << endl << endl;
            for (int i=0; i<num; i++)
                for (int j=0; j<num; j++)
                {
                    cout << tablero[i][j] << "\t";

                    if (j == (num-1))
                        cout << endl;
                }

            //Tu jugada:
            do
            {
                cout << endl << "Seleccione el lugar que quiere cubrir: " << endl;
                do
                {
                cout << "Fila: ";
                cin >> f;
                } while (f<0 || f>(num-1));

                do
                {
                cout << "Columna: ";
                cin >> c;
                } while (c<0 || c>(num-1));

                if (tablero[f][c] == 'X' || tablero[f][c] == 'O')
                    cout << endl << "Ese hueco ya esta ocupado" << endl;

            } while (tablero[f][c] == 'X' || tablero[f][c] == 'O');

            tablero[f][c]= 'X';

            //Mostramos tablero:
            cout << "TABLERO: " << endl << endl;
            for (int i=0; i<num; i++)
                for (int j=0; j<num; j++)
                {
                    cout << tablero[i][j] << "\t";

                    if (j == (num-1))
                        cout << endl;
                }

            //Comprobamos si el juego ha acabado:
            for (int i=0; i<num; i++)
                for (int j=0; j<num; j++)
                {
                    if (tablero[i][j] == '*')
                        endgame= false;
                }

        } while (endgame == false);
    }


    return 0;
}

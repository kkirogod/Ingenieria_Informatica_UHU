#include <iostream>
#include <time.h>
#include <stdlib.h>
#include <windows.h>

#define N 99 //CUIDADO!! SI LE PONES UN NÚMERO ALTO NO FUNCIONA EL PROGRAMA

using namespace std;

class mundo
{
    int f, c, tablero[N][N], tablero2[N][N];

public:
    void crearmundo();
    void mostrarmundo();
    void diadespues();
    void tabconf();
    void tabrand();
    int menu();
};

void mundo::crearmundo()
{
    int op;

    do
    {
        cout << "Introduce el numero de filas: ";
        cin >> f;

        cout << "\nIntroduce el numero de columnas: ";
        cin >> c;
    }
    while(f > N || c > N);

    for (int j = 0; j < f; j++)
        for (int k = 0; k < c; k++)
        {
            tablero[j][k] = 0;
            tablero2[j][k] = 0;
        }

    system("cls");
    op = menu();

    switch(op)
    {
    case 1:
        tabrand();
        break;

    case 2:
        tabconf();
        break;
    }

    cout << "\n************** BIENVENIDO AL NUEVO MUNDO ************** \n\n";
}

void mundo::mostrarmundo()
{
    for (int j = 0; j < f; j++)
        for (int k = 0; k < c; k++)
        {
            if(tablero[j][k] == 0)
                cout << "   ";
            if(tablero[j][k] == 1)
                cout << " 0 ";

            if(k == (c-1))
                cout << endl;
        }
}

void mundo::diadespues()
{
    int vecinas, l, m;

    //HACEMOS UNA COPIA DEL TABLERO ORIGINAL
    for (int j = 0; j < f; j++)
        for (int k = 0; k < c; k++)
            tablero2[j][k] = tablero[j][k];

    //ALGORITMO DE RECUENTO DE VECINAS
    for (int j = 0; j < f; j++)
    {
        for (int k = 0; k < c; k++)
        {
            //EN ESTE PUNTO ESTOY YA SITUADO EN UNA CASILLA
            vecinas = 0;
            l = -1;

            if(j == 0)
                l++;

            while(l < 2 && (l + j) < f)
            {
                m = -1;

                if(k == 0)
                    m++;

                while(m < 2 && (m + k) < c)
                {
                    if(l == 0 && m == 0)
                        m++;

                    if(tablero[j + l][k + m] == 1)
                        vecinas++;

                    m++;
                }
                l++;
            }

            if(tablero[j][k] == 1 && vecinas != 2 && vecinas != 3)
                tablero2[j][k] = 0;

            if(tablero[j][k] == 0 && vecinas == 3)
                tablero2[j][k] = 1;
        }
    }

    //IMPLEMENTAMOS EL NUEVO ESCENARIO EN EL TABLERO ORIGINAL
    for (int j = 0; j < f; j++)
        for (int k = 0; k < c; k++)
            tablero[j][k] = tablero2[j][k];
}

void mundo::tabconf()
{
    int j, k;
    char ans;

    do
    {
        system("cls");

        do
        {
            cout << "\nFila de la casilla a rellenar: ";
            cin >> j;

            cout << "\nColumna de la casilla a rellenar: ";
            cin >> k;

            if (j < 0 || j > f || k < 0 || k > c)
                cout << "ERROR: Fila y/o columna no valida.";
        }
        while(j < 0 || j > f || k < 0 || k > c);

        tablero[j][k] = 1;

        cout << "\nPulse 's' si quiere introducir otra: ";
        cin >> ans;

        ans = toupper(ans);
    }
    while(ans == 'S');
}

void mundo::tabrand()
{
    int nrand, j, k;
    srand(time(0));

    nrand = (f*c)/5;
    cout << "\n Se generaran " << nrand << " celulas vivas \n";

    system("pause");

    for (int i = 0; i < nrand; i++)
    {
        j = rand()%(f+1);
        k = rand()%(c+1);

        tablero[j][k] = 1;
        tablero2[j][k] = 1;
    }
}

int mundo::menu()
{
    int op;
    do
    {
        cout << "\n\t\t\t\t\t********* MENU **********\n"
             << "\t\t\t\t\t1. Generar mundo aleatorio\n"
             << "\t\t\t\t\t2. Generar mundo manualmente\n"
             << "\t\t\t\t\tElige una opcion: ";
        cin >> op;

        if(op != 1 && op != 2)
            cout << "\nERROR: Opcion no valida.";
    }
    while(op != 1 && op != 2);

    return op;
}


int main()
{
    mundo m1;

    m1.crearmundo();

    for(int i = 0; i < 500; i++)
    {
        m1.mostrarmundo();
        m1.diadespues();

        Sleep(500);
        //system("pause");
        system("cls");
    }

    return 0;
}

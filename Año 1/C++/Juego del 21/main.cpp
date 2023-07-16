#include <iostream>
#include <stdlib.h>

using namespace std;

int main()
{
string nick;
cout << "BIENVENIDO AL JUEGO DEL 21!!" << endl << "Ingrese su nickname: ";
cin >> nick;

cout <<"Bienvenido " << nick << "!" << endl;

cout << "Pulsa 1 si quieres empezar tu, pulsa cualquier otro numero si quieres que empiece la CPU: ";

int z, a;
cin >> z;

    if (z ==1 ) {

        int total = 0;
        int b = 0;
        while (total <= 21) {

        if (b>1) {
            cout << "CPU: " << b << endl;
            cout << "Introduce el ultimo numero de tu cadena (de uno a tres numeros): ";
            cin >> a;
            system("cls");

            switch(a - total) {

                case 1: b=(a+3);
                break;

                case 2: b=(a+2);
                break;

                case 3: b=(a+1);
                break;

                default: cout << "Numero no valido." << endl;
            }
        }

        else {
            cout << "Introduce el ultimo numero de tu cadena (de uno a tres numeros): ";
            cin >> a;
            system("cls");

            switch(a - total) {

                case 1: b=(a+3);
                break;

                case 2: b=(a+2);
                break;

                case 3: b=(a+1);
                break;

                default: cout << "Numero no valido." << endl;
        }
        }
        total = b;
        }
        cout << "TE HE GANADO " << nick << "!";
    }

    else {
        int n = 1;
        int c = 1;
        int total;
        while (c < 21) {

            cout << "CPU: " << c << endl;
            cout << "Introduce el ultimo numero de tu cadena (de uno a tres numeros): ";
            cin >> a;
            system("cls");

            switch(a - c) {

            case 1: c=(a+((n*4)-a));
            n = n+1;
            break;

            case 2: c=(a+((n*4)-a));
            n = n+1;
            break;

            case 3: c = (a+1);
            n = n+1;
            break;

            default: cout << "Numero no valido." << endl;
        }
        total = c;
        }
    if ((total = 21) && (a > 20)) {
        cout << "TE HE GANADO " << nick << "!";
    }
    else {
        cout << "CPU: 21" << endl
        << "O vaya he perdido :(";
    }
    }
}

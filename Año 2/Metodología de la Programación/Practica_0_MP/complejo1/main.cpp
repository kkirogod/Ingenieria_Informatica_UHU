#include <iostream>
#include "complejo.h"

using namespace std;

int main()
{
    complejo a(5, 4), b(3, -4);
    a.ver();
    b.ver();

    cout << endl << endl;

    a = a+b;
    a.ver();
    b.ver();

    cout << endl << endl;

    a = -b;
    a.ver();

    a = a+4; //PONER: a+=4 -> NO FUNCIONARÍA, "+=" NO ESTÁ SOBRECARGADO
    a.ver();

    a = -2+a;
    a.ver();

    a = -a;
    a.ver();

    a = -8+a+b;
    a.ver();

    cout << endl << endl;

    cout << a << endl << b;

    return 0;
}

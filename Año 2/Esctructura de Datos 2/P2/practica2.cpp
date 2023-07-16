#include <iostream>
#include <cstdlib>
#include <queue>
#include "arbin.h"
#include "abb.h"
#include "Excep_Ej6.h"

// Recorridos

template <typename T>
void inorden(const Arbin<T>& a, const typename Arbin<T>::Iterador& r)
{
    if (!r.arbolVacio())
    {
        inorden(a, a.subIzq(r));
        cout << r.observar() << " ";
        inorden(a, a.subDer(r));
    }
}

template <typename T>
void preorden(const Arbin<T>& a, const typename Arbin<T>::Iterador& r)
{
    if (!r.arbolVacio())
    {
        cout << r.observar() << " ";
        preorden(a, a.subIzq(r));
        preorden(a, a.subDer(r));
    }
}

template <typename T>
void postorden(const Arbin<T>& a, const typename Arbin<T>::Iterador& r)
{
    if (!r.arbolVacio())
    {
        postorden(a, a.subIzq(r));
        postorden(a, a.subDer(r));
        cout << r.observar() << " ";
    }
}

template <typename T>
void anchura(const Arbin<T>& a)
{
    if (!a.esVacio())
    {
        queue<typename Arbin<T>::Iterador> c;
        typename Arbin<T>::Iterador ic = a.getRaiz();
        c.push(ic);
        while (!c.empty())
        {
            ic = c.front();
            c.pop();
            cout << ic.observar() << " ";
            if (!a.subIzq(ic).arbolVacio())
                c.push(a.subIzq(ic));
            if (!a.subDer(ic).arbolVacio())
                c.push(a.subDer(ic));
        }
    }
}


/***************************************************************************/
/****************************** EJERCICIOS *********************************/
/***************************************************************************/
//Ejercicio 1

template <typename T>
int numHojas(const Arbin<T>& a)
{
    int n=0;

    cuentaHojas(a, a.getRaiz(), n);

    return n;
}

template <typename T>
void cuentaHojas(const Arbin<T>& a, const typename Arbin<T>::Iterador& r, int& n)
{
    if (!r.arbolVacio())
    {
        if (a.subIzq(r).arbolVacio() && a.subDer(r).arbolVacio())
        {
            n++;
        }
        else
        {
            cuentaHojas(a, a.subIzq(r), n);
            cuentaHojas(a, a.subDer(r), n);
        }
    }
}

/****************************************************************************/
//Ejercicio 2

template <typename T>
Arbin<T>& simetrico(const Arbin<T>& a)
{
    return copiaSim(a, a.getRaiz());
}

template <typename T>
Arbin<T>& copiaSim(const Arbin<T>& a, const typename Arbin<T>::Iterador& r)
{
    if (r.arbolVacio())
        return Arbin<T>();
    else
        return Arbin<T>(r.observar(), copiaSim(a, a.subDer(r)), copiaSim(a, a.subIzq(r)));
}

/****************************************************************************/
//Ejercicio 3

template <typename T>
void recorridoZigzag(const Arbin<T>& a, char sentido)
{
    recorridoZigzag(a, sentido, a.getRaiz());
}

template <typename T>
void recorridoZigzag(const Arbin<T>& a, char sentido, const typename Arbin<T>::Iterador& r)
{
    if (!r.arbolVacio())
    {
        cout << r.observar() << " ";

        switch(sentido)
        {
        case 'D':
            recorridoZigzag(a, 'I', a.subDer(r));
            break;

        case 'I':
            recorridoZigzag(a, 'D', a.subIzq(r));
            break;
        }
    }
}

/******************************************************************************/
//Ejercicio 4

template <typename T>
bool compensado(const Arbin<T>& a)
{
    int n=0;
    bool comp=true;

    compensado(a, a.getRaiz(), n, comp);

    return comp;
}

template <typename T>
void compensado(const Arbin<T>& a, const typename Arbin<T>::Iterador& r, int& n, bool& comp)
{
    if(!r.arbolVacio())
    {
        int n1=n, n2=n;

        if(!a.subIzq(r).arbolVacio())
        {
            n1++;

            compensado(a, a.subIzq(r), n1, comp);
        }

        if(!a.subDer(r).arbolVacio())
        {
            n2++;

            compensado(a, a.subDer(r), n2, comp);
        }

        if(n1>n2)
            n=n1;
        else
            n=n2;

        if((n1-n2)>1 || (n1-n2)<-1)
            comp=false;
    }
}

/*****************************************************************************/
//Ejercicio 5

void palabras(const Arbin<char>& a, const typename Arbin<char>::Iterador& r, string s)
{
    if (!r.arbolVacio())
    {
        s+=r.observar();

        if (a.subIzq(r).arbolVacio() && a.subDer(r).arbolVacio())
            cout << s << endl;
        else
        {
            palabras(a, a.subIzq(r), s);
            palabras(a, a.subDer(r), s);
        }
    }
}

void palabras(const Arbin<char>& a)
{
    string s="";

    palabras(a, a.getRaiz(), s);
}

/******************************************************************************/
//Ejercicio 6

void siguienteMayor(const ABB<int>& a, const ABB<int>::Iterador& r, int x, int &sm)
{
    if(!r.arbolVacio())
    {
        if(x<r.observar())
        {
            sm=r.observar();
            siguienteMayor(a, a.subIzq(r), x, sm);
        }
        else
            siguienteMayor(a, a.subDer(r), x, sm);
    }
}

int siguienteMayor(const ABB<int>& a, int x) throw(NoHaySiguienteMayor)
{
    int sm=0;

    siguienteMayor(a, a.getRaiz(), x, sm);

    if(sm==0)
        throw NoHaySiguienteMayor();
    else
        return sm;
}

/******************************************************************************/
//Ejercicio 7

void posicionInorden(const ABB<int>& a, const typename ABB<int>::Iterador& r, int nodo, int &pos, bool &encontrado)
{
    if (!r.arbolVacio() && !encontrado)
    {
        posicionInorden(a, a.subIzq(r), nodo, pos, encontrado);

        if(r.observar()==nodo)
            encontrado=true;
        else if(!encontrado)
            pos++;

        posicionInorden(a, a.subDer(r), nodo, pos, encontrado);
    }
}


int posicionInorden(const ABB<int>& a, int nodo)
{
    int pos=1;
    bool encontrado=false;

    posicionInorden(a, a.getRaiz(), nodo, pos, encontrado);

    if(encontrado)
        return pos;
    else
        return 0;
}


/******************************************************************************/
//Ejercicio 8

void haySumaCamino(const Arbin<int>& a, const ABB<int>::Iterador& r, const int suma, int total, bool &hay)
{
    if (!r.arbolVacio())
    {
        total+=r.observar();

        if (!a.subIzq(r).arbolVacio())
            haySumaCamino(a, a.subIzq(r), suma, total, hay);

        if (!a.subDer(r).arbolVacio())
            haySumaCamino(a, a.subDer(r), suma, total, hay);

        if (a.subIzq(r).arbolVacio() && a.subDer(r).arbolVacio() && total == suma)
            hay = true;
    }
    else
    {
        if (suma == 0)
            hay = true;
    }
}

bool haySumaCamino(const Arbin<int>& a, int suma)
{
    int total = 0;
    bool hay = false;

    haySumaCamino(a, a.getRaiz(), suma, total, hay);

    return hay;
}


/****************************************************************************/
/****************************************************************************/
int main(int argc, char *argv[])
{
    Arbin<char> A('t', Arbin<char>('m', Arbin<char>(),
                                   Arbin<char>('f', Arbin<char>(), Arbin<char>())),
                  Arbin<char>('k', Arbin<char>('d', Arbin<char>(), Arbin<char>()),
                              Arbin<char>()));

    Arbin<char> B('t', Arbin<char>('n', Arbin<char>(),
                                   Arbin<char>('d', Arbin<char>('e', Arbin<char>(), Arbin<char>()),
                                           Arbin<char>())),
                  Arbin<char>('m', Arbin<char>('f', Arbin<char>(), Arbin<char>()),
                              Arbin<char>('n', Arbin<char>(), Arbin<char>())));

    Arbin<char> D('t', Arbin<char>('k', Arbin<char>('d', Arbin<char>(), Arbin<char>()),
                                   Arbin<char>()),
                  Arbin<char>('m', Arbin<char>(),
                              Arbin<char>('f', Arbin<char>(), Arbin<char>())));

    Arbin<char> E('o', Arbin<char>('r', Arbin<char>(),
                                   Arbin<char>('o', Arbin<char>(), Arbin<char>())),
                  Arbin<char>('l', Arbin<char>('a', Arbin<char>(), Arbin<char>()),
                              Arbin<char>('e', Arbin<char>(), Arbin<char>())));

    Arbin<int> F(2, Arbin<int>(7, Arbin<int>(2, Arbin<int>(), Arbin<int>()),
                               Arbin<int>(6, Arbin<int>(5, Arbin<int>(), Arbin<int>()),
                                          Arbin<int>(11, Arbin<int>(), Arbin<int>()))),
                 Arbin<int>(5, Arbin<int>(),
                            Arbin<int>(9, Arbin<int>(),
                                       Arbin<int>(4, Arbin<int>(), Arbin<int>()))));

    ABB<int> BB6, BB7;

    /*
        // NUMERO HOJAS //
        cout << "Num. hojas del arbol B: " << numHojas(B) << endl;
        cout << "Num. hojas del arbol E: " << numHojas(E) << endl << endl;

        // COPIA SIMETRICA //
        Arbin<char> C = simetrico(B);
        cout << "Recorrido en inorden del arbol B: " << endl;
        inorden(B, B.getRaiz());
        cout << endl << "Recorrido en inorden del arbol C: " << endl;
        inorden(C, C.getRaiz());
        cout << endl << endl;


        // RECORRIDO EN ZIG-ZAG //
        cout << "Recorrido en zigzag I de B:\n";
        recorridoZigzag(B, 'I');
        cout << endl;
        cout << "Recorrido en zigzag D de C:\n";
        //recorridoZigzag(C, 'D');
        cout << endl << endl;


        // COMPENSADO //
        cout << "Esta A compensado?:";
        cout << (compensado(A) ? " SI" : " NO") << endl;
        cout << "Esta B compensado?:";
        cout << (compensado(B) ? " SI" : " NO") << endl << endl;

        // PALABRAS DE UN ARBOL //
        cout << "PALABRAS DE A:\n";
        palabras(E);
        cout << "PALABRAS DE B:\n";
        palabras(B);
        cout << endl;

    // SIGUIENTE MAYOR
    BB6.insertar(8);
    BB6.insertar(3);
    BB6.insertar(10);
    BB6.insertar(1);
    BB6.insertar(6);
    BB6.insertar(14);
    BB6.insertar(4);
    BB6.insertar(7);
    BB6.insertar(13);
    try
    {
        cout << "Siguiente mayor en BB6 de 5: " << siguienteMayor(BB6, 5) << endl;
        cout << "Siguiente mayor en BB6 de 8: " << siguienteMayor(BB6, 8) << endl;
        cout << "Siguiente mayor en BB6 de 13: " << siguienteMayor(BB6, 13) << endl;
        cout << "Siguiente mayor en BB6 de 14: " << siguienteMayor(BB6, 14) << endl;
    }
    catch(const NoHaySiguienteMayor& e)
    {
        cout << e.Mensaje() << endl << endl;
    }

            // POSICION INORDEN //
            BB7.insertar(5); BB7.insertar(1); BB7.insertar(3); BB7.insertar(8); BB7.insertar(6);
            cout << "Posicion Inorden en BB7 de 3: ";
            cout << posicionInorden(BB7, 3);
            cout << endl << "Posicion Inorden en BB7 de 8: ";
            cout << posicionInorden(BB7, 8);
            cout << endl << "Posicion Inorden en BB7 de 7: ";
            cout << posicionInorden(BB7, 7);
            cout << endl << endl;
    */
    // SUMA CAMINO
    cout << "Hay un camino de suma 26 en F?:";
    cout << (haySumaCamino(F, 26) ? " SI" : " NO") << endl;
    cout << "Hay un camino de suma 9 en F?:";
    cout << (haySumaCamino(F, 9) ? " SI" : " NO") << endl;
    //*/

    system("PAUSE");
    return 0;
}

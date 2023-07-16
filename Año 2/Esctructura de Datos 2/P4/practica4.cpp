#include <iostream>
#include <cstdlib>
#include "grafo.h"
#include "conjunto.h"
#include <queue>
#include <sstream>
#include <map>

using namespace std;

//Ejercicio 1
template <typename T>
T verticeMaxCoste(const Grafo<T, float>& G)
{
    typedef Conjunto<Vertice<T> > ConjVertices;
    typedef Conjunto<Arista<T, float> > ConjAristas;

    ConjVertices cV = G.vertices();
    ConjAristas cA = G.aristas();

    typedef map<T, float> DiccionarioVertices;
    DiccionarioVertices dVertices;

    while(!cV.esVacio())
    {
        Vertice<T> v(cV.quitar());

        dVertices[v.getObj()]=0;
    }

    while(!cA.esVacio())
    {
        Arista<T, float> a(cA.quitar());

        dVertices[a.getOrigen()]+=a.getEtiqueta();
    }

    T verticeMax;
    float costeMax=0;
    cV = G.vertices();

    while(!cV.esVacio())
    {
        Vertice<T> v(cV.quitar());

        if(dVertices[v.getObj()]>costeMax)
        {
            verticeMax=v.getObj();
            costeMax=dVertices[v.getObj()];
        }
    }

    return verticeMax;
}


//Ejercicio 2
template <typename T, typename U>
void inaccesibles(const Grafo<T, U>& G)
{
    typedef Conjunto<Arista<T, U> > ConjAristas;

    ConjAristas cA = G.aristas();

    typedef map<T, int> DiccionarioVertices;
    DiccionarioVertices dVertices;

    while(!cA.esVacio())
    {
        Arista<T, U> a(cA.quitar());

        dVertices[a.getDestino()]+=1;
    }

    typedef Conjunto<Vertice<T> > ConjVertices;
    ConjVertices cV = G.vertices();

    while(!cV.esVacio())
    {
        Vertice<T> v(cV.quitar());

        if(dVertices[v.getObj()]==0)
            cout << endl << v.getObj() << endl;
    }
}


// Ejercicio 3
template <typename T, typename U>
bool caminoEntreDos(const Grafo<T, U>& G, const T& vo, const T& vd)
{
    typedef Conjunto<Vertice<T> > ConjVertices;
    ConjVertices c = G.vertices();

    typedef map<T, float> DiccionarioVertices;
    DiccionarioVertices dVertices;

    while(!c.esVacio())
    {
        Vertice<T> v(c.quitar());
        dVertices[v.getObj()]=0;
    }

    queue<T> cola;
    cola.push(vo);

    dVertices[vo]=1;

    bool hayCamino = false;

    while(!cola.empty() && !hayCamino)
    {
        if(cola.front()==vd)
            hayCamino=true;
        else
        {
            ConjVertices cV = G.adyacentes(cola.front());

            while(!cV.esVacio())
            {
                Vertice<T> v(cV.quitar());

                if(dVertices[v.getObj()]==0)
                {
                    cola.push(v.getObj());

                    dVertices[v.getObj()]=1;
                }
            }
            cola.pop();
        }
    }
    return hayCamino;
}


//Ejercicio 4
template <typename T>
void caminosAcotados(const Grafo<T, float>& G, const T u, float maxCoste, float coste, string camino)
{
    typedef Conjunto<Vertice<T> > ConjVertices;
    typedef Conjunto<Arista<T, float> > ConjAristas;

    if(coste <= maxCoste)
    {
        ConjVertices cAd = G.adyacentes(u);

        stringstream ss;
        ss << u;
        camino = camino + " -> " + ss.str();

        if(!cAd.esVacio())
        {
            while(!cAd.esVacio())
            {
                ConjAristas cA = G.aristas();
                float costeAux = coste;

                bool encontrado = false;

                Vertice<T> v(cAd.quitar());

                while(!cA.esVacio() && !encontrado)
                {
                    Arista<T, float> a(cA.quitar());

                    if(a.getDestino() == v.getObj() && a.getOrigen() == u)
                    {
                        encontrado = true;
                        costeAux += a.getEtiqueta();
                    }
                }
                caminosAcotados(G, v.getObj(), maxCoste, costeAux, camino);
            }
        }
        else
            cout << camino << endl;
    }
    else
        cout << camino << endl;
}

template <typename T>
void caminosAcotados(const Grafo<T, float>& G, const T& u, float maxCoste)
{
    stringstream ss;

    caminosAcotados(G, u, maxCoste, 0, ss.str());
}


//Ejercicio 5
template <typename T, typename U>
T outConectado(const Grafo<T, U>& G)
{
    typedef Conjunto<Vertice<T> > ConjVertices;
    typedef Conjunto<Arista<T, U> > ConjAristas;

    ConjVertices cV = G.vertices();

    T vertice;

    while(!cV.esVacio())
    {
        Vertice<T> v(cV.quitar());

        ConjAristas cA = G.aristas();

        int sal=0, ent=0;

        while(!cA.esVacio())
        {
            Arista<T, U> a(cA.quitar());

            if(a.getOrigen() == v.getObj())
                sal++;

            if(a.getDestino() == v.getObj())
                ent++;
        }
        if(sal > ent)
            vertice = v.getObj();
    }
    return vertice;
}


//Ejercicio 6
template <typename T, typename U>
void recorrido_profundidad(const Grafo<T, U>& G, const T u, map<T, int> &dVertices)
{
    typedef Conjunto<Vertice<T> > ConjVertices;

    cout << u << endl;

    dVertices[u]=1;

    ConjVertices cV = G.adyacentes(u);

    while(!cV.esVacio())
    {
        Vertice<T> v(cV.quitar());

        if(dVertices[v.getObj()]==0)
            recorrido_profundidad(G, v.getObj(), dVertices);
    }
}

template <typename T, typename U>
void recorrido_profundidad(const Grafo<T, U>& G, const T& v)
{
    typedef Conjunto<Vertice<T> > ConjVertices;
    typedef map<T, int> DiccionarioVertices;

    DiccionarioVertices dVertices;

    ConjVertices cV = G.vertices();

    while(!cV.esVacio())
    {
        Vertice<T> v(cV.quitar());

        dVertices[v.getObj()]=0;
    }

    recorrido_profundidad(G, v, dVertices);
}


//********************************************************************//
int main()
{
    Grafo<int, float> G(7);
    for (int i = 1; i <= 7; i++) G.insertarVertice(i);
    G.insertarArista(2, 1, 4);
    G.insertarArista(1, 3, 3);
    G.insertarArista(1, 4, 2);
    G.insertarArista(1, 6, 1);
    G.insertarArista(6, 4, 5);
    G.insertarArista(4, 7, 3);
    G.insertarArista(5, 1, 6);

    Grafo<string, float> H(7);
    H.insertarVertice("Huelva");
    H.insertarVertice("Lepe");
    H.insertarVertice("Niebla");
    H.insertarVertice("Mazagon");
    H.insertarVertice("Almonte");
    H.insertarVertice("Aljaraque");
    H.insertarVertice("Matalascañas");
    H.insertarArista("Lepe", "Huelva", 4);
    H.insertarArista("Huelva", "Niebla", 3);
    H.insertarArista("Huelva", "Mazagon", 2);
    H.insertarArista("Huelva", "Aljaraque", 1);
    H.insertarArista("Mazagon", "Almonte", 3);
    H.insertarArista("Mazagon", "Matalascañas", 4);
    H.insertarArista("Aljaraque", "Mazagon", 5);
    H.insertarArista("Almonte", "Huelva", 6);

    /*
    cout << " Vertice de maximo coste en G: " << verticeMaxCoste(G) << endl;
    cout << " Vertice de maximo coste en H: " << verticeMaxCoste(H) << endl;

    cout << endl << " Vertices inaccesibles en G: ";
    inaccesibles(G);

    cout << endl << " Camino entre Dos en H de Lepe a Almonte: ";
    cout << (caminoEntreDos(H, string("Lepe"), string("Almonte")) ? " SI " : " NO ") << endl;
    cout << endl << " Camino entre Dos en H de Aljaraque a Lepe: ";
    cout << (caminoEntreDos(H, string("Aljaraque"), string("Lepe")) ? " SI " : " NO ") << endl;

    cout << endl << " Caminos acotados en G a coste 9 desde el vertice 2:" << endl;
    caminosAcotados(G, 2, 9);

    cout << endl << endl << " Vertice outConectado en G: " << outConectado(G);
    cout << endl << " Vertice outConectado en H: " << outConectado(H);

    cout << endl << endl << " Recorrido en profundidad de H desde el vertice Huelva:  ";
    recorrido_profundidad(H, string("Huelva"));
    */

    cout << endl << endl;

    system("PAUSE");

    return EXIT_SUCCESS;
}

//CAMINOS SIMPLES

template <typename T, typename U>
void Caminos(const Grafo<T, U>& G, const T& v1, const T& v2)
{
    T x;
    typedef map <T,bool> Dic;
    Dic Diccionario;
    stringstream ss;
    Conjunto<Vertice<T> > conjuntovertices = G.vertices();

    ss << v1;

    while (!conjuntovertices.esVacio())
    {
        x=conjuntovertices.quitar().getObj();
        Diccionario[x] = false;
    }

    Caminos(G,v1,v2,Diccionario,ss.str());
}

template <typename T, typename U>
void Caminos(const Grafo<T, U>& G, const T& v1, const T& v2, map<T,bool>& Diccionario, string s)
{
    T x;
    Diccionario[v1]=true;
    Conjunto<Vertice<T> > ady = G.adyacentes(v1);

    while(!ady.esVacio())
    {
        x = ady.quitar().getObj();

        if (x == v2)
        {
            stringstream ss;
            ss << v2;
            s = s + " - " + ss.str();
            cout << s << endl;
        }
        else if(Diccionario[x]==false)
        {
            stringstream ss;
            ss << x;
            s = s + " - " + ss.str();
            Caminos(G,x,v2,Diccionario,s);
        }
    }
    Diccionario[v1]=false;
}

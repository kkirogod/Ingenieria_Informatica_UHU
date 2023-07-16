#include "Multiconjunto.h"
#include "Persona.h"

template <typename T> Multiconjunto<T>::Multiconjunto()
{
    num=0;
}

template <typename T> bool Multiconjunto<T>::esVacio() const
{
    if(num==0)
        return true;
    else
        return false;
}

template <typename T> int Multiconjunto<T>::cardinalidad() const
{
    return num;
}

template <typename T> void Multiconjunto<T>::anade(const T& objeto)
{
    c[num] = objeto;
}

template <typename T> void Multiconjunto<T>::elimina(const T& objeto)
{
    for(int i=(num-1); i>=0; i--)
    {
        if(c[i]==objeto)
        {
            int j=i;

            while(j<(num-1))
            {
                c[j]=c[j+1];
                j++;
            }
            num--;
        }
    }
}

template <typename T> bool Multiconjunto<T>::pertenece(const T& objeto) const
{
    bool encontrado=false;
    int i=0;

    while(!encontrado && i<num)
    {
        if(c[i]==objeto)
        {
            encontrado=true;
        }
        i++;
    }
    return encontrado;
}

// INSTANCIAS
template class Multiconjunto<int>;
template class Multiconjunto<char>;
template class Multiconjunto<Persona>;


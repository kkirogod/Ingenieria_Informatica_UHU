#include "CriptoDoc.h"

bool CriptoDoc::leer(char fichero[])
{
    vaciar();

    bool exito;
    linea lineatexto;
    ifstream f(fichero);

    if(!f.fail())
    {
        fflush(stdin);
        getline(f, lineatexto);

        while(!f.eof())
        {
            texto.anadirDch(lineatexto);

            fflush(stdin);
            getline(f, lineatexto); //lee las lineas de texto del fichero hasta su final
        }
        exito = true;
    }
    else
        exito = false;

    f.close();

    return exito;
}

bool CriptoDoc::escribir(char fichero[])
{
    bool exito;
    linea lineatexto;
    ofstream f(fichero);

    if(!f.fail())
    {
        for(int i = 1; i <= texto.longitud(); i++)
        {
            lineatexto = texto.observar(i);
            f << lineatexto << endl;
        }
        exito = true;
    }
    else
        exito = false;

    f.close();

    return exito;
}

void CriptoDoc::cifrar(int codigo)
{
    linea cadena;
    int lon;

    for(int i=1; i<=texto.longitud(); i++)
    {
        cadena = texto.observar(i);
        lon = cadena.length();

        for(int j=0; j<lon; j++)
        {
            cadena[j] += codigo;

            if(cadena[j]>255)
                cadena[j] -= 256;
        }
        texto.modificar(i, cadena);
    }
}

void CriptoDoc::descifrar(int codigo)
{
    linea cadena;
    int lon;

    for(int i=1; i<=texto.longitud(); i++)
    {
        cadena = texto.observar(i);
        lon = cadena.length();

        for(int j=0; j<lon; j++)
        {
            cadena[j] -= codigo;

            if(cadena[j]<0)
                cadena[j] += 256;
        }
        texto.modificar(i, cadena);
    }
}

void CriptoDoc::vaciar()
{
    while(!texto.esvacia())
        texto.eliminarDch();
}

void CriptoDoc::concatenar(CriptoDoc &doc)
{
    texto.concatenar(doc.texto);

    //for(int i=0; i<doc.numlineas(); i++)
    //texto.anadirDch(doc.observar(i));
}

int CriptoDoc::numlineas()
{
    return texto.longitud();
}

linea CriptoDoc::observar(int i)
{
    return texto.observar(i);
}

void CriptoDoc::insertarlinea(linea e)
{
    texto.anadirDch(e);
}

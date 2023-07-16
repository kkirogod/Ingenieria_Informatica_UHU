#include "Cliente.h"
#include "Fecha.h"

Cliente::Cliente(long int d, char *nom, Fecha f):fechaAlta(f)
{
    this->dni=d;
    this->nombre=new char[strlen(nom)+1];
    strcpy(this->nombre, nom);
}

Cliente::~Cliente()
{
    delete [] nombre;
}

/*Cliente::Cliente(const Cliente &c) //constructor de copia
{
    dni=c.dni;
    fechaAlta(c.fechaAlta.getDia(), c.fechaAlta.getMes(), c.fechaAlta.getAnio());
    nombre=new char[strlen(c.nombre)+1];
    strcpy(nombre, c.nombre);
}*/

Cliente& Cliente::operator=(const Cliente &c) //operator =
{
    if (this != &c)   //si no es x=x
    {
        this->dni=c.dni;
        delete [] this->nombre;
        //this->nombre=c.nombre;  //MAL!!!!
        this->nombre=new char[strlen(c.nombre)+1];
        strcpy(this->nombre, c.nombre);
        this->fechaAlta=c.fechaAlta;
    }
    return *this;
}

void Cliente::setNombre(char *nom)
{
    delete [] this->nombre;

    this->nombre=new char[strlen(nom)+1];
    strcpy(this->nombre, nom);
}

void Cliente::setFecha(Fecha f)
{
    fechaAlta=f;
}

bool Cliente::operator==(Cliente c) const
{
    if (dni!=c.dni)
        return false;

    if (strcmp(nombre, c.nombre)!=0)
        return false;

    if (fechaAlta.getDia()!=c.fechaAlta.getDia() ||
            fechaAlta.getMes()!=c.fechaAlta.getMes() ||
            fechaAlta.getAnio()!=c.fechaAlta.getAnio())
        return false;

    return true;
}

ostream& operator<<(ostream& s, const Cliente &o)
{
    s << o.getNombre() << " (" << o.getDni() << " - ";

    if (o.getFecha().getDia() < 10)
        s << "0";

    s << o.getFecha().getDia() << " ";

    char* meses[12]= {"ene", "feb", "mar", "abr", "may", "jun", "jul", "ago", "sep", "oct", "nov", "dic"};

    for(int i=0; i<3; i++)
        s << meses[o.getFecha().getMes()-1][i];

    s << " " << o.getFecha().getAnio() << ")";

    return s;
}

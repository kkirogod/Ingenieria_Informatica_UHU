#include "Empresa.h"
#include <stdio.h>

//el constructor de la clase empresa debe crear un array dinámico de tamaño inicial 10
//debe inicializar a 0 los contadores de clientes (ncli) y contratos (ncon)
//y debe inicializar la constante nmaxcli a 100 y la variable nmaxcon a 10
Empresa::Empresa():nmaxcli(100)
{
    this->ncli=0;
    this->ncon=0;
    this->contratos=new Contrato *[10]; //inicialmente capacidad para 10 Contratos
    this->nmaxcon=10;
}

//el destructor debe, además de eliminar el array dinámico creado en el constructor,
//eliminar los objetos clientes y contratos apuntados por ambos arrays
Empresa::~Empresa()
{
    for(int i=0; i<this->ncon; i++)   //primero elimino los objetos contratos
    {
        delete this->contratos[i];
    }
    delete [] this->contratos; //luego elimino el array de punteros

    for(int i=0; i<this->ncli; i++)
    {
        delete this->clientes[i];
    }
    //delete [] this->clientes; //ERROR el array clientes no es dinámico
}

//método auxiliar usado por el método crearContrato
int Empresa::altaCliente(Cliente *c)   //añade cliente apuntado por c al array clientes
{
    int pos=-1; //devuelve -1 si no cabe y la posición donde lo he metido si cabe

    if (this->ncli<nmaxcli)
    {
        this->clientes[this->ncli]=c;
        pos=this->ncli;
        this->ncli++;
    }
    else
    {
        cout << "Lo siento, el cupo de clientes esta lleno";
        pos=-1;
    }

    return pos;
}

//método auxiliar usado por el método crearContrato
int Empresa::buscarCliente(long int dni) const   //si no existe devuelve -1 y si existe devuelve la posición del cliente
{
    int i= 0;
    int pos= -1;

    while(pos==-1 && i<ncli)
    {
        if(clientes[i]->getDni()==dni)
            pos=i;
        else
            i++;
    }

    return pos;
}

void Empresa::crearContrato()
{
    long int dni;
    int pos, dia, mes, anio;
    Cliente *c;                 //NO CREO NINGUN CLIENTE SINO SOLO UN PUNTERO A CLIENTE

    cout << "Introduce DNI: ";
    cin >> dni;

    //supongo que hay un metodo buscarCliente(dni) que devuelve -1 si no existe y si
    //existe devuelve la posicion del cliente en el array this->clientes

    pos=this->buscarCliente(dni);

    if (pos==-1)   //el cliente no existe y hay que darlo de alta
    {
        char* nombre=new char[100];
        cout << "Nombre del cliente: ";
        fflush(stdin);
        gets(nombre);
        cout << "dia: ";
        cin >> dia;
        cout << "mes: ";
        cin >> mes;
        cout << "anio: ";
        cin >> anio;
        c=new Cliente(dni, nombre, Fecha(dia, mes, anio));
        pos=this->altaCliente(c); //según la variable pos, sé si el cliente se ha dado de alta o no
        delete [] nombre;
    }

    if (pos!=-1)   //el cliente existe o se ha dado de alta
    {
//PREGUNTAR QUE TIPO DE CONTRATO QUIERE Y LOS DATOS NECESARIOS
//CREAR EL OBJETO CONTRATO CORRESPONDIENTE Y AÑADIR AL ARRAY
//contratos UN PUNTERO A DICHO OBJETO

        if(ncon==nmaxcon) //si no caben más contratos ampliamos el array al doble
        {
            Contrato **aux;
            aux=contratos;
            contratos=new Contrato*[nmaxcon*2];

            for(int i=0; i<nmaxcon; i++)
                contratos[i]=aux[i];

            delete [] aux;
            nmaxcon*=2;
        }

        int tcontrato;

        do
        {
            cout << "Tipo de Contrato a abrir (1-Tarifa Plana, 2-Movil): ";
            cin >> tcontrato;

        }
        while(tcontrato!=1 && tcontrato!=2);

        cout << "Fecha del contrato" << endl;
        cout << "dia: ";
        cin >> dia;
        cout << "mes: ";
        cin >> mes;
        cout << "anio: ";
        cin >> anio;

        int minutosHablados;

        cout << "minutos hablados: ";
        cin >> minutosHablados;

        if(tcontrato==2) //Movil
        {
            float precioMinuto;
            char nacionalidad[100];

            cout << "Precio minuto: ";
            cin >> precioMinuto;
            cout << "Nacionalidad: ";
            cin >> nacionalidad;
            cout << endl;

            this->contratos[ncon] = new ContratoMovil(dni, Fecha(dia, mes, anio), precioMinuto, minutosHablados, nacionalidad);
            ncon++;
        }
        else //TP
        {
            this->contratos[ncon] = new ContratoTP(dni, Fecha(dia, mes, anio), minutosHablados);
            ncon++;
        }
    }
    else          //no cabían más clientes en el array
        delete c;
}

void Empresa::cargarDatos()
{
    Fecha f1(29,2,2001), f2(31,1,2002), f3(1,2,2002);
    this->clientes[0] = new Cliente(75547001, "Peter Lee", f1);
    this->clientes[1] = new Cliente(45999000, "Juan Perez", Fecha(29,2,2000));
    this->clientes[2] = new Cliente(37000017, "Luis Bono", f2);
    this->ncli=3;
    this->contratos[0] = new ContratoMovil(75547001, f1, 0.12, 110, "DANES"); //habla 110m a 0.12€/m
    this->contratos[1] = new ContratoMovil(75547001, f2, 0.09, 170, "DANES"); //habla 170m a 0.09€/m
    this->contratos[2] = new ContratoTP(37000017, f3, 250); //habla 250m (300m a 10€, exceso 0.15€/m)
    this->contratos[3] = new ContratoTP(75547001, f1, 312); //habla 312m (300m a 10€, exceso 0.15€/m)
    this->contratos[4] = new ContratoMovil(45999000, f2, 0.10, 202, "ESPAÑOL"); //habla 202m a 0.10/m
    this->contratos[5] = new ContratoMovil(75547001, f2, 0.15, 80, "DANES"); //habla 80m a 0.15€/m
    this->contratos[6] = new ContratoTP(45999000, f3, 400); //habla 400m (300m a 10€, exceso 0.15€/m)
    this->ncon=7;
}

bool Empresa::cancelarContrato(int idContrato) //true si el Contrato existe, false si no
{
    int i=0;
    bool encontrado=false;

    while (i<ncon && encontrado==false)
    {
        if (this->contratos[i]->getIdContrato()==idContrato)
        {
            encontrado=true;
            delete contratos[i];

            //para eliminarlo desplazo las celdas adyacentes una posición a la izquierda
            for(int j=i+1; j<ncon; j++)
                contratos[j-1]=contratos[j];

            ncon--;
        }
        else
            i++;
    }

    /*
    if(!encontrado)
        cout << "El contrato" << idContrato << "no existe" << endl;
    else
        cout << "El contrato" << idContrato << "ha sido cancelado" << endl;
    */

    return encontrado;
}

bool Empresa::bajaCliente(long int dni) //true si el Cliente existe, false si no
{
    bool encontrado=false;
    int pos=buscarCliente(dni);

    if(pos!=-1) //si ha encontrado al cliente
    {
        encontrado=true;

        //eliminamos primero todos sus contratos

        for(int i=ncon-1; i>=0; i--) //recorremos el array descendentemente para evitar el error de contratos consecutivos
        {
            if(contratos[i]->getDniContrato()==dni) //si el contrato pertenece a este cliente
            {
                cancelarContrato(contratos[i]->getIdContrato());
            }
        }

        delete clientes[pos];

        //para eliminarlo desplazo las celdas adyacentes una posición a la izquierda
        for(int j=pos+1; j<ncli; j++)
            clientes[j-1]=clientes[j];

        ncli--;
    }

    return encontrado;
}

int Empresa::descuento(float porcentaje) const //devuelve a cuantos aplica el descuento(n)
{
    int n=0;

    for(int i=0; i<ncon; i++)
    {
        ContratoMovil *c = dynamic_cast<ContratoMovil*>(contratos[i]); //comprobamos si contratos[i] es de tipo ContratoMovil

        if(c) //si es de tipo ContratoMovil...
        {
            float p=c->getPrecioMinuto();
            p-=(p*porcentaje/100);
            c->setPrecioMinuto(p);
            n++;
        }
    }
    return n;
}

int Empresa::nContratosTP() const // devuelve el número de contratos de Tarifa Plana existentes
{
    int n=0;

    for(int i=0; i<ncon; i++)
    {
        //comprobamos si contratos[i] es de tipo ContratoTP
        if(typeid(*contratos[i])==typeid(ContratoTP)) //si es de tipo ContratoTP...
        {
            n++;
        }
    }
    return n;
}

void Empresa::ver() const
{
    cout << endl << "La Empresa tiene " << ncli << " clientes y " << ncon << " contratos" << endl
         << "Clientes:" << endl;

    for(int i=0; i<ncli; i++)
        cout << *clientes[i] << endl;

    cout << endl << "Contratos:" << endl;

    for(int i=0; i<ncon; i++)
    {
        cout << *contratos[i] << endl;
    }
    cout << endl;
}

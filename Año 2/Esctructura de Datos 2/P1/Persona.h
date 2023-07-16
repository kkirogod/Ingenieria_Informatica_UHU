#ifndef __Persona_H__
#define __Persona_H__

#include <string>

using namespace std;

class Persona
{
public:
    Persona(const string& n = "", int e = 0);
    const string& getNombre() const;
    int getEdad() const;
    void setNombre(const string& n);
    void setEdad(int e);
    bool operator==(const Persona& p) const;
private:
    string nombre;
    int edad;
};

#endif

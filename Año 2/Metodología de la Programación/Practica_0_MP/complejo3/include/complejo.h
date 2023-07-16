#ifndef COMPLEJO_H
#define COMPLEJO_H

#include <iostream>

using namespace std;

class complejo
{
    private:
        int real, imaginario;

    public:
        complejo(int r, int i=0);
        complejo(const complejo &c);
        int getr() const {return real; }
        int geti() const {return imaginario; }
        void set(int real, int imaginario);
        void set();
        void ver() const;
        complejo operator+(complejo c) const;
        complejo operator+(int i) const;
        complejo operator-() const;
        complejo operator++();
        complejo operator++(int notused);
        operator int();
        bool operator==(complejo c) const;
        bool operator==(int i) const;

        friend ostream& operator<<(ostream& s, const complejo &c);
};

complejo operator+(int i, complejo c);

#endif // COMPLEJO_H

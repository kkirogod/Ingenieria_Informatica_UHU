#include <iostream>
#include <string>
#include <fstream>
#include <iomanip>
#include <stdio.h>
#include <windows.h>
#include <math.h>

using namespace std;

double performancecounter_diff(LARGE_INTEGER *a, LARGE_INTEGER *b)
{
    LARGE_INTEGER freq;
    QueryPerformanceFrequency(&freq);

    return (double)(a->QuadPart - b->QuadPart) / (double)freq.QuadPart;
}

int sumar_asm(int a, int b) {  //Los nombres de registros van precedidos por % y 'eax' = reg. acumulador
    int resultado;

    asm("movl %1, %%eax\n"      //El operando fuente es el primero, el destino el segundo
        "addl %2, %%eax\n"
        : "=r" (resultado)      // Salida: variable resultado
        : "r" (a), "r" (b)      // Entrada: argumentos a y b
        : "%eax");              // Registro modificado: eax

    return resultado;
}

int main()
{
    int iter=4000;

    LARGE_INTEGER t_ini, t_fin;  //la clase LARGE_INTEGER representa un int de 64 bits, para acceder a éste accedemos al atributo QuadPart
    double secs;
    int a=0;

    QueryPerformanceCounter(&t_ini);

    for (register int i=0; i<iter; i++)  //considero que el bucle son 4 instrucciones: mov cx, 0; (bucle: ...;) inc cx; cmp cx, iter; jnz bucle
        sumar_asm(1, 2);

    QueryPerformanceCounter(&t_fin);

    secs = performancecounter_diff(&t_fin, &t_ini);

    cout << "Tiempo de ejecucion: " << secs << " segundos" << endl;

    double tiempo_promedio = secs/iter;

    cout << "Tiempo promedio por iteracion: " << tiempo_promedio << " segundos" << endl;

    cout << "MIPS: " << ((iter*6)/pow(10, 6))/secs << endl;  // MIPS=(total de instrucciones ejecutadas/10^6)/tiempo de ejecución

    system("pause");

    return 0;
}

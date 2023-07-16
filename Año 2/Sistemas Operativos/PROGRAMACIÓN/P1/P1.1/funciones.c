#include <stdio.h>
#include <math.h>
#include "funciones.h"

void leer(int *a, int *b, int *c){
	printf("Coeficiente a:\n");
	scanf("%d", &(*a));
	
	printf("Coeficiente b:\n");
	scanf("%d", &(*b));
	
	printf("Coeficiente c:\n");
	scanf("%d", &(*c));
}

void visualizar(int a, int b, int c){
	printf("Ecuacion actual: %dx² + %dx + %d = 0\n", a, b, c);
}

void calcular(int a, int b, int c){
	int d=pow(b ,2)-4*a*c;
	float x1, x2;
	
	if(d>0){
		printf("Las dos raices son reales:\n");
        x1=((-b+sqrt(d))/(2*a));
        x2=((-b-sqrt(d))/(2*a));
        printf("x1=%.2f\nx2=%.2f\n", x1, x2);
	}
	else{
    	if(d==0){
        	x1=(-b)/(2*a);
            printf("La ecuacion solo tiene una raiz:\nx1=%.2f", x1);
        }
        else{
            printf("La ecuacion no tiene raices reales :(");
        }
	}
}

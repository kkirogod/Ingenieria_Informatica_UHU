#include <stdio.h>
#include "funciones.h"
//#include "funciones.c"

int main(){
	int op=-1, a, b, c;
	
	while(op!=4){
		printf("*****MENU*****\n");
		printf("1. Leer Coeficientes\n");
		printf("2. Visualizar Ecuación\n");
		printf("3. Calcular Raíces\n");
		printf("4. Salir\n");
		printf("Escoja una opcion: ");
		scanf("%d", &op);
		
		switch(op){
			case 1:
				leer(&a, &b, &c); 
				break;
			case 2:
				visualizar(a, b, c); 
				break;
			case 3:
				calcular(a, b, c); 
				break;
			case 4:
				printf("Saliendo del programa...\n");
				break;
			default:
				printf("Escoje un numero del 1 al 4 por favor\n");
		}
	}

	return 0;
}

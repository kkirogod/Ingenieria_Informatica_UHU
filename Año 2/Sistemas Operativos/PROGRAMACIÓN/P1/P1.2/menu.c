#include <stdio.h>
#include "funciones.h"

int main(){
	int op=-1, n=0, a[10];
	
	while(op!=4){
		printf("*****MENU*****\n");
		printf("1. Leer datos\n");
		printf("2. Visualizar maximo\n");
		printf("3. Calcular seno del maximo\n");
		printf("4. Salir\n");
		printf("Escoja una opcion: ");
		scanf("%d", &op);
		
		switch(op){
			case 1:
				leer(&a, &n);
				break;
			case 2:
				printf("\nMaximo: %d\n", maximo(a, n));
				break;
			case 3:
				printf("\nSeno: %.2f\n", seno(maximo(a, n)));
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

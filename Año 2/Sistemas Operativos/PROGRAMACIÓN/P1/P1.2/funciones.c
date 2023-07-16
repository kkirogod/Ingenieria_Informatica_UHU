#include <stdio.h>
#include <math.h>
#include <stdbool.h>
#include "funciones.h"

void leer(int a[], int *n){
	bool end=false;
	int num;
	(*n)=0;

	while((*n)<10 && !end){
		printf("Numero %d: ", (*n)+1);
		scanf("%d", &num);

		if(num==-1)
			end=true;
		else{
			a[*n]=num;
			(*n)++;
		}
	}
}

int maximo(int a[], int n){
	int max=a[0];

	for(int i=1; i<n; i++){
		if(a[i]>max)
			max=a[i];
	}

	return max;
}

double seno(int max){
	return sin((double) max);
}

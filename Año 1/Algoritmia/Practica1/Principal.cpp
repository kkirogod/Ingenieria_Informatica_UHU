#include "TestAlgoritmo.h"
#include <iostream>
#include <cstdlib>
#include "Constantes.h"
using namespace std;

/* Programa principal */

int menu()
{
	int op;
	system("cls");

	cout << "*** FAA. Practica 1. Curso 21/22 ***" << endl
		<< "\t\t\t\t Alumno: Miguel Quiroga" << endl << endl 
		<< "*** ESTDUIO DE LA COMPLEJIDAD DEL ALGORITMO BUSQUEDA SECUENCIAL ***" << endl << endl
		<< "1.- ESTUDIO TEORICO" << endl
		<< "2.- ESTUDIO EMPIRICO" << endl
		<< "0.- Salir" << endl
		<< "----------" << endl
		<< "ELIJA OPCION: ";

	cin >> op;

	return op;
}

int menuteorico()
{
	int op;
	system("cls");

	cout << "*** MENU TEORICO DEL ALGORITMO DE BUSQUEDA SECUENCIAL ***" << endl << endl
		<< "1.- Probar el algoritmo de busqueda secuencial" << endl
		<< "2.- Obtener los casos del metodo de busqueda secuencial" << endl
		<< "3.- Comparar los casos" << endl
		<< "0.- Volver al menu principal" << endl
		<< "---------------" << endl
		<< "ELIJA OPCION: ";

	cin >> op;

	return op;
}

int menucasosteorico()
{
	int op;
	system("cls");

	cout << "*** CASO A ESTUDIAR PARA LA BUSQUEDA SECUENCIAL ***" << endl << endl
		<< "0.- Caso peor" << endl
		<< "1.- Caso medio" << endl
		<< "2.- Caso mejor" << endl
		<< "---------------" << endl
		<< "ELIJA OPCION: ";

	cin >> op;

	return op;
}

int menuempirico()
{
	int op;
	system("cls");

	cout << "*** MENU EMPIRICO DEL ALGORITMO DE BUSQUEDA SECUENCIAL ***" << endl << endl
		<< "1.-Probar el algoritmo de busqueda secuencial" << endl
		<< "2.-Obtener los casos del metodo de busqueda secuencial" << endl
		<< "3.-Comparar los casos" << endl
		<< "0.-Volver al menu principal" << endl
		<< "---------------" << endl
		<< "ELIJA OPCION: ";

	cin >> op;

	return op;
}

int menucasosempirico()
{
	int op;
	system("cls");

	cout << "*** CASO A ESTUDIAR PARA LA BUSQUEDA SECUENCIAL ***" << endl << endl
		 << "0.- Caso peor" << endl
		 << "1.- Caso medio" << endl
		 << "2.- Caso mejor" << endl
		 << "---------------" << endl
		 << "ELIJA OPCION: ";

	cin >> op;

	return op;
}

int main()
{
	TestAlgoritmo test;

	int op, op1, op2, op3, op4;

	do
	{
		op = menu();

		switch (op)
		{
		case 1:
			do
			{
				op2 = menuteorico();

				switch (op2)
				{
				case 0:
					break;

				case 1: 
					test.comprobarAlgoritmo();

					break;

				case 2: 
					op1 = menucasosteorico();
					
					test.costeCasoTeorico(op1);

					break;

				case 3: 
					test.compararTeorico(SECUENCIALPEOR, SECUENCIALMEDIO, SECUENCIALMEJOR);

					break;

				default: 
					cout << "Introduzca 0, 1, 2 o 3" << endl;
					system("pause");

					break;
				}
			} while (op2 != 0);

			break;

		case 2:
			do
			{
				op3 = menuempirico();

				switch (op3)
				{
				case 0:
					break;
				
				case 1:
					test.comprobarAlgoritmo();

					break;

				case 2:
					op4 = menucasosempirico();

					test.costeCasoEmpirico(op4);

					break;

				case 3:
					test.compararEmpirico(SECUENCIALPEOR, SECUENCIALMEDIO, SECUENCIALMEJOR);

					break;

				default:
					cout << "Introduzca 0, 1, 2 o 3" << endl;
					system("pause");

					break;
				}
			} while (op3 != 0);

			break;
		}
	} while (op != 0);

return 0;
}


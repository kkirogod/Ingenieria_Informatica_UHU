%EJEMPLO 1

H=sym([2 -1 0 -1; 3 2 -1 0])
K=sym([1 -4 1 -2; 0 0 1 -1])
HmK=[H;K]
ReHmK=rref(HmK) %Los vectores no nulos de la FER dorman una base del espacio fila
BaseHmK=ReHmK(1:3,:) %Almacenamos las tres primeras filas de la FER, es decir, una base H+K

%EJEMPLO 2

syms x y z t
A=transpose([x y z t; 1 0 -3 1; 2 2 -1 5]) %obtenemos A traspuesta
E1=kf(2,1/A(2,3),4)*sumaf(3,1,-A(3,2),4)*sumaf(4,1,-A(4,2),4);A1=E1*A
E2=sumaf(3,2,-A1(3,3),4)*sumaf(4,2,-A1(4,3),4);A2=E2*A1
Ecu=A2(3:4,1) %Mostramos las ecuaciones de la priemra columna

%EJEMPLO 3

%a)
A=sym([1 2 -4 -1 9;-1 -2 4 0 -5;1 1 -1 0 3;-1 1 -5 1 -3])
RA=rref(A)
BaseFilaA=RA(1:3,:)
BaseColA=[A(:,1) A(:,2) A(:,4)]

%EJERCICIO 5

P=[0.4 0.3 0.2 0.1 0; 0 0 0.3 0 0.7; 0 0 0.8 0.2 0; 0.5 0 0 0.5 0; 0 0.6 0 0 0.4]
%c
P^6
%d
P^40
%e
p0=[0.2 0.1 0.1 0.3 0.3];
p40=p0*(P^40)
%f
[V, D]=eig(P) %diagonalizamos P
syms n
L=D^n %potencia enésima de D
Pn=V*L*inv(V)
F=limit(Pn, n, inf) %%hallamos el límite de Pn
p0*F
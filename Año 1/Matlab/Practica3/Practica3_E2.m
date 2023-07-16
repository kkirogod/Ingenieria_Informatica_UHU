%EJERCICIO 2: GRAFOS
A = [2 1 0 0 1 0; 1 0 1 0 1 0; 0 1 0 1 0 0; 0 0 1 0 1 1; 1 1 0 1 0 0; 0 0 0 1 0 0]
G=graph(A)
plot(G)

%PARA POSIBLES CAMINOS DE LONGITUD 3:
A2 = A^2
A3 = A^3
S = A3 + A2 + A
%etc.
%(Repasar teoría)
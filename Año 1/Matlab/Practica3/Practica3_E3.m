%EJERCICIO 3: DETERMINANTES
format rat

A = sym([-5 1 -3 3; 1 0 2 -2; 5 -7 4 6; 0 -1 -9 7])
L1 = sumac(1,2,5,4)*sumac(3,2,3,4)*sumac(4,2,-3,4)
A1 = A*L1
A1 = A1(2:4,[1 3 4]) %rebajamos el grado de la matriz
A1(2,:) = -A1(2,:) %cambiamos de signo la columna 2
L2 = sumac(2,1,-2,3)*sumac(3,1,2,3)
A2 = A1*L2
A2 = A2(2:3,2:3)
d = A2(1,1)*A2(2,2)-A2(1,2)*A2(2,1)

%PARTE 2: MENOR PRINCIPAL MATRIZ
A =([1 -1 -1 2; 2 -2 -1 3; 2 -2 -3 5])
[Menor,r,F,C] = menorp(A)
det(Menor)

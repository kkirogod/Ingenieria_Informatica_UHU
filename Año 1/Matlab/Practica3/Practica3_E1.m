format rat %para que los resultados salgan en forma de fraccion

%EJERCICIO 1: FACTORIZACIÓN LU
%Hallando U
C=[4 -2 -1; 5 1 -1; 1 2 -1]
E1=eye(3); E1(2,:)= E1(2,:)-(5/4)*E1(1,:);
C=E1*C
E2=eye(3); E2(3,:)= E2(3,:)-(1/4)*E2(1,:);
C=E2*C
E3=eye(3); E3(3,:)= E3(3,:)-(5/7)*E3(2,:);
C=E3*C
U=C

%Hallando L
L=inv(E1)*inv(E2)*inv(E3)
b=[9 7 12]' % las comillas hacen que b sea un vector columna
Y=L \ b
X=U \ Y
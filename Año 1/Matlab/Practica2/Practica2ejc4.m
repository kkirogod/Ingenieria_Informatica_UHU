%EJERCICIO 4
A=[1 -1 0 1;2 -1 1 1; 2 1 4 -1;0 -1 1 0]
AI=[A eye(4)]

E1=eye(4); E1(1,:)=(2)*E1(1,:); AI1=E1*AI

E2=eye(4); E2(2,:)=E2(2,:)-E2(1,:); AI2=E2*AI1

E3=eye(4); E3(3,:)=E3(3,:)-E3(1,:); AI3=E3*AI2

E4=eye(4); E4(1,:)=(1/2)*E4(1,:); AI4=E4*AI3

E5=eye(4); E5(1,:)=E5(2,:)+E5(1,:); AI5=E5*AI4

E6=eye(4); E6(3,:)=E6(3,:)-(3)*E6(2,:); AI6=E6*AI5

E7=eye(4); E7(4,:)=E7(4,:)+E7(2,:); AI7=E7*AI6

E8=eye(4); E8(1,:)=E8(1,:)-E8(3,:); AI8=E8*AI7

E9=eye(4); E9(2,:)=E9(2,:)-E9(3,:); AI9=E9*AI8

E10=eye(4); E10(4,:)=E10(4,:)-(2)*E10(3,:); AI10=E10*AI9

E11=eye(4); E11(2,:)=E11(4,:)-E11(2,:); AI11=E11*AI10

E12=eye(4); E12(2,:)=(-1)*E12(2,:); AI12=E12*AI11

E13=eye(4); E13(4,:)=(-1)*E13(4,:); AI13=E13*AI12

%SACAMOS LA INVERSA DE AI13:
Ainv = AI13(:,5:8)

%DEBE DAR I:
A*Ainv

%HALLAR LA INV DE A EN 1 PASO:
inv(A)
%EJERCICIO 4: ESPACIOS VECTORIALES
v1=sym([1 -2 5 -1]'); v2=sym([0 3 -2 2]'); v3=sym([1 0 1 -1]'); v4=sym([3 5 5 3]')
linsolve([v1], v2) %si te sale algo distinto de Inf es que es combianción lineal de los otros
linsolve([v1 v2], v3)
linsolve([v1 v2 v3], v4) 

%por lo tanto la base B = v1, v2, v3
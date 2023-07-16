(deffacts hechos 
	(numeros 6 3 9 2 5 7 2)
	(suma 0))



(defrule sumaNumeros
	?a <- (numeros $?i ?x $?f)
	?b <- (suma ?r) 
	=>
	(retract ?a ?b)
	(assert (numeros $?i $?f) 
	(suma (+ ?r ?x)))) 
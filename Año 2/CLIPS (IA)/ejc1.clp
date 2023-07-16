(deffacts hechos
	(numeros 1 8 4 2 8 2 5)
	(solucion ))

(defrule repeticion
	?h1 <- (solucion $?t)
	?h2 <- (numeros $?a ?x $?b ?x $?c)
	(not (solucion $? ?x $?))
	=>
	(retract ?h1 ?h2)
	(assert (numeros $?a $?b $?c)
		(solucion $?t ?x)))



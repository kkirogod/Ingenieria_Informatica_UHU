(deffacts hechos
	(numeros 4 82 33 9 46 65 2)
	(solucion 0))



(defrule comienzo
	?n1 <- (solucion ?s)
	?n2 <- (min ?i)
	?n3 <- (max ?a)
	=>
	(retract ?n1 ?n2 ?n3)
	(assert (solucion (- ?a ?i))))


(defrule min
	(numeros $?n ?y $?t)
	(not (numeros $? ?z&:(< ?z ?y) $?))
	=>
	(assert (min ?y)))


(defrule max
	(numeros $?n ?x $?t)
	(not (numeros $? ?w&:(> ?w ?x) $?))
	=>
	(assert (max ?x)))
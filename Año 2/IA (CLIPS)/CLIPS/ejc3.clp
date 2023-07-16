(deffacts hechos
	(cto A 56 4 14 33 6 9)
	(cto B 33 7 18 91 1 4))



(defrule comienzo
	(cto ? $?int)
	(not (interseccion $?))
	=>
	(assert (interseccion $?int)))


(defrule interseccion
	?sol <- (interseccion $?inter ?y $?a)
	(cto ?c $?)
	(not (cto ?c $? ?y $?))
	=>
	(retract ?sol)
	(assert (interseccion $?inter $?a)))
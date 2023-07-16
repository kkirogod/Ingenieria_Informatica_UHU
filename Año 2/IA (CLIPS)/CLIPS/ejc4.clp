(deffacts hechos
	(cto1 8 4 6 31 7 23)
	(cto2 2 19 6 92 7 1)
	(resta))



(defrule resta
	(cto1 $? ?x $?)
	(not (cto2 $? ?x $?))	
	(not (resta $? ?x $?))
	?a <- (resta $?y)
	=>
	(retract ?a)
	(assert (resta ?y ?x)))
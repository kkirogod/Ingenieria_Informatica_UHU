(defrule regla
?h1 <- (resultado $?r)
?h2 <- (datos ?x $?d)
(datos ?y&:(> ?y ?x) $?)
=>
(retract ?h1 ?h2)
(assert (resultado $?r ?x)
(datos ?d)))

(deffacts hechos
(datos -1 2 5)
(datos 0 3)
(resultado))
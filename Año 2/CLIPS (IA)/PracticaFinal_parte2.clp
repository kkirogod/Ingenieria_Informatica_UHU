(deffacts BaseDatos
	(Medicamento
		(nom Jarabe)
		(enf Gripe))
	(Medicamento
		(nom Contrex)
		(enf Gripe))
	(Medicamento
		(nom Vitamina(deffacts Sintomas
	(sint Cansancio Fiebre))



(deftemplate Medicamento
	(field nom)
	(multifield enf))


(deftemplate Enfermedad
	(field nom)
	(multifield sint))


(deftemplate Doctor
	(field nom)
	(multifield med))



(defrule Reconocimiento
	(sint $? ?s $?)
	(Enfermedad
		(nom ?n)
		(sint $? ?s $?))
	(not (Enfermedades $? ?n $?))
	?x <- (Enfermedades $?e)
	=>
	(retract ?x)
	(assert (Enfermedades ?e ?n))
	(printout t "Podrías padecer " ?n ", ya que tienes " ?s crlf))


(defrule MedicamentoDoctor
	(Enfermedades $? ?e $?)
	(Medicamento
		(nom ?n)
		(enf $? ?e $?))
	(Doctor
		(nom ?d)
		(med $? ?n $?))
	(not (Medicamentos $? ?n $?))
	?x <- (Medicamentos $?m)
	=>
	(retract ?x)
	(assert (Medicamentos ?m ?n))
	(printout t "Con " ?e " debería ir al " ?d " a que le recete " ?n crlf))
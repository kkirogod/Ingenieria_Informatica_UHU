(deffacts BaseDatos
	(Medicamento
		(nom Jarabe)
		(enf Gripe))
	(Medicamento
		(nom Contrex)
		(enf Gripe))
	(Medicamento
		(nom Vitamina)
		(enf Anemia))
	(Medicamento
		(nom Vacuna)
		(enf Gripe Rubeola Malaria Hepatitis))
	(Medicamento
		(nom Pastilla)
		(enf Rubeola Hepatitis Tuberculosis))
	(Enfermedad
		(nom Gripe)
		(sint Tos Cansancio Fiebre Dolor))
	(Enfermedad
		(nom Rubeola)
		(sint Fiebre Escalofrios Jaqueca Secredon))
	(Enfermedad
		(nom Malaria)
		(sint Diarrea Fiebre Ictericia Escalofrios))
	(Enfermedad
		(nom Hepatitis)
		(sint Diarrea Nauseas Ictericia))
	(Enfermedad
		(nom Tuberculosis)
		(sint Tos Cansancio Fiebre Escalofrios))
	(Enfermedad
		(nom Anemia)
		(sint Cansancio Nauseas Apatia))
	(Doctor
		(nom Otorrino)
		(med Jarabe Contrex))
	(Doctor
		(nom Endocrinologo)
		(med Vacuna))
	(Doctor
		(nom Nutricionista)
		(med Vitamina))
	(Doctor
		(nom MedicoGeneral)
		(med Vacuna Pastilla)))


(deffacts Diagnostico
	(Medicamentos )
	(Enfermedades ))
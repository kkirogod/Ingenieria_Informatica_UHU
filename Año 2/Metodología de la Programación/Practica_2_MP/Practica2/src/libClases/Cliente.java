package libClases;

public class Cliente implements Cloneable, Proceso {
	private final String nif; // dni del cliente (letra incluida) (NO puede cambiar)
	private final int codCliente; // codigo único (y fijo) generado por la aplicación
	private String nombre; // nombre completo del cliente (SI se puede modificar)
	private final Fecha fechaNac; // fecha nacimiento del cliente (NO se puede cambiar)
	private final Fecha fechaAlta; // fecha de alta del cliente (SI se puede modificar)
	private static int n = 1; // contador para codCliente
	private static Fecha FechaPorDefecto = new Fecha(1, 1, 2018);

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getNif() {
		return nif;
	}

	public int getCodCliente() {
		return codCliente;
	}

	public Fecha getFechaNac() {
		return new Fecha(fechaNac);
	}

	public Fecha getFechaAlta() {
		return new Fecha(fechaAlta);
	}

	public void setFechaAlta(Fecha f) {
		Fecha aux = new Fecha(f);
		f = this.fechaAlta;
		f.setFecha(aux.getDia(), aux.getMes(), aux.getAnio());
	}
	
	public static String getFechaPorDefecto() {
		return FechaPorDefecto.toString();
	}

	public static void setFechaPorDefecto(Fecha f) {
		FechaPorDefecto = new Fecha(f);
	}

	public Cliente(String NIF, String nom, Fecha fNac, Fecha fAlta) {
		nif = NIF;
		nombre = nom;
		fechaNac = new Fecha(fNac); 							//PREGUNTAR SI ES NECESARIO O BASTA CON fechaNac=fNac
		fechaAlta = new Fecha(fAlta);
		codCliente = n;
		n++;
	}

	public Cliente(String NIF, String nom, Fecha fNac) {
		nif = NIF;
		nombre = nom;
		fechaNac = new Fecha(fNac);
		fechaAlta = new Fecha(FechaPorDefecto);
		codCliente = n;
		n++;
	}

	public Cliente(Cliente c) {
		nif = c.nif;
		nombre = c.nombre;
		fechaNac = c.getFechaNac(); 
		fechaAlta = c.getFechaAlta();
		codCliente = n;
		n++;
	}

	public String toString() { // devuelve una cadena con la información del cliente
		return (nif + " " + fechaNac + ": " + nombre + " (" + codCliente + " - " + fechaAlta + ")");
	}
	
	public void ver() {
		System.out.println(toString());
	}

	public Object clone() { 
		return new Cliente(this);                                                       //ESTÁ BIEN?
	}
	
	public boolean equals(Object obj) { // true sin son iguales                   
		if (this == obj)
			return true; // si apuntan al mismo sitio son iguales

		if (obj == null)
			return false;

		if (getClass() != obj.getClass()) // if (!(obj instanceof Cliente))
			return false; // si los 2 no son de la misma clase no son iguales

		Cliente c = (Cliente) obj;

		return (nif == c.nif);
	}
}
package libClases;

public class ClienteTarifaPlana extends Cliente implements Cloneable, Proceso {
	private static float precioTP = 20;
	private static float limiteMinutos = 300;
	private float minutosHablados;
	private String nacionalidad;

	public static void setTarifa(int m, float p) {
		setLimite(m);
		setPrecioTP(p);
	}

	public static float getTarifa() {
		return precioTP;
	}

	public static void setPrecioTP(float pTP) {
		precioTP = pTP;
	}

	public static float getLimite() {
		return limiteMinutos;
	}

	public static void setLimite(int limMinutos) {
		limiteMinutos = limMinutos;
	}

	public float getMinutos() {
		return minutosHablados;
	}

	public void setMinutos(float minHablados) {
		minutosHablados = minHablados;
	}

	public String getNacionalidad() {
		return nacionalidad;
	}

	public void setNacionalidad(String nac) {
		nacionalidad = nac;
	}

	public ClienteTarifaPlana(String NIF, String nom, Fecha fNac, Fecha fAlta, float minHablados, String nac) {
		super(NIF, nom, fNac, fAlta);
		minutosHablados = minHablados;
		nacionalidad = nac;
	}
	
	public ClienteTarifaPlana(ClienteTarifaPlana c) {
		super(c.getNif(), c.getNombre(), c.getFechaNac(), c.getFechaAlta());
		minutosHablados = c.getMinutos();
		nacionalidad = c.getNacionalidad();
	}

	public ClienteTarifaPlana(String NIF, String nom, Fecha fNac, float minHablados, String nac) {
		super(NIF, nom, fNac);
		minutosHablados = minHablados;
		nacionalidad = nac;
	}

	public String toString() {
		String s = super.toString() + " " + nacionalidad + " [" + limiteMinutos + " por " + precioTP + "] " + minutosHablados + " --> ";

		if (minutosHablados > limiteMinutos)
			s += (float) (precioTP + ((minutosHablados - limiteMinutos) * 0.15));
		else
			s += precioTP;

		return s;
	}
	
	public ClienteTarifaPlana clone() {
		return new ClienteTarifaPlana(super.getNif(), super.getNombre(), super.getFechaNac(), super.getFechaAlta(), minutosHablados, nacionalidad);
	}
}

package libClases;

public class ClienteMovil extends Cliente implements Cloneable, Proceso {
	private Fecha fechaPermanencia;
	private float minutosHablados;
	private float precioMinuto;

	public float getMinutosHablados() {
		return minutosHablados;
	}

	public void setMinutosHablados(float minHablados) {
		minutosHablados = minHablados;
	}

	public float getPrecioMinuto() {
		return precioMinuto;
	}

	public void setPrecioMinuto(float pMinuto) {
		precioMinuto = pMinuto;
	}

	public ClienteMovil(String NIF, String nom, Fecha fNac, Fecha fAlta, Fecha fPermanencia, float minHablados, float pMinuto) {
		super(NIF, nom, fNac, fAlta);
		fechaPermanencia = new Fecha(fPermanencia);
		minutosHablados = minHablados;
		precioMinuto = pMinuto;
	}
	
	public ClienteMovil(ClienteMovil c) {
		super(c.getNif(), c.getNombre(), c.getFechaNac(), c.getFechaAlta());
		fechaPermanencia = new Fecha(c.getFPermanencia());
		minutosHablados = c.getMinutosHablados();
		precioMinuto = c.getPrecioMinuto();
	}

	public ClienteMovil(String NIF, String nom, Fecha fNac, float minHablados, float pMinuto) {
		super(NIF, nom, fNac);
		fechaPermanencia = new Fecha(super.getFechaAlta().getDia(), super.getFechaAlta().getMes(), super.getFechaAlta().getAnio()+1);
		minutosHablados = minHablados;
		precioMinuto = pMinuto;
	}

	public void setFPermanencia(Fecha f) {
		fechaPermanencia = new Fecha(f);
	}

	public Fecha getFPermanencia() {
		return new Fecha(fechaPermanencia);
	}
	
	public String toString() {
		return (super.toString() + " " + fechaPermanencia + " " + minutosHablados + " x " + precioMinuto + " --> " 
				+ minutosHablados*precioMinuto);
	}
	
	public ClienteMovil clone() {
		return new ClienteMovil(super.getNif(), super.getNombre(), super.getFechaNac(), super.getFechaAlta(), fechaPermanencia, minutosHablados, precioMinuto);
	}
}

package libClases;

import java.util.Scanner;

public class Empresa implements Cloneable {
	private Cliente clientes[];
	private int nCli;

	public Empresa() {
		clientes = new Cliente[10];
		nCli = 0;
	}

	public String toString() {
		String s = "";

		for (int i = 0; i < nCli; i++)
			s += ((clientes[i]).toString() + "\n");

		return s;
	}

	private int buscarCliente(String NIF) { // devuelve el índice del cliente en la lista
		int pos = -1;

		for (int i = 0; i < nCli; i++) {
			if (clientes[i].getNif().equals(NIF) == true) // si está en la lista
				pos = i;
		}
		return pos;
	}

	public void alta(Cliente c) {
		int pos = buscarCliente(c.getNif());

		if (pos == -1) // si no está en la lista
		{
			if (clientes.length == nCli) { // amplío el array
				Cliente aux[] = clientes;
				clientes = new Cliente[aux.length * 2]; // es buena idea ampliarlo duplicando su tamaño en vez de
														// ampliarlo poco

				for (int i = 0; i < aux.length; i++) // la operacion es costosa porque hay que copiar todo
					clientes[i] = aux[i];
			}
			clientes[nCli] = c;
			nCli++;
		} /*
			 * else { System.out.println("Ya existe un Cliente con ese dni: " +
			 * clientes[pos].toString()); }
			 */
	}

	public void alta() {
		Scanner sc = new Scanner(System.in);

		System.out.println("DNI: ");
		String NIF = sc.nextLine();

		int pos = buscarCliente(NIF);

		if (pos == -1) { // si no está en la lista
			System.out.println("Nombre: ");
			String nom = sc.nextLine();
			System.out.println("Fecha Nacimiento: ");
			Fecha fNac = Fecha.pedirFecha();
			System.out.println("Fecha de Alta: ");
			Fecha fAlta = Fecha.pedirFecha();
			System.out.println("Minutos que habla al mes: ");
			float minutosHablados = sc.nextFloat();

			int op;

			do {
				System.out.println("Indique tipo de cliente (1-Movil, 2-Tarifa Plana): ");
				op = sc.nextInt();
				sc.nextLine(); // limpiar el buffer de entrada (al introducir un int, se queda /n en el buffer)

			} while (op != 1 && op != 2);

			if (op == 1) {
				System.out.println("Precio por minuto: ");
				float pMinuto = sc.nextFloat();
				System.out.println("Fecha fin permanencia: ");
				Fecha fPermanencia = Fecha.pedirFecha();

				Cliente c = new ClienteMovil(NIF, nom, fNac, fAlta, fPermanencia, minutosHablados, pMinuto);
				alta(c);
			} else {
				System.out.println("Nacionalidad: ");
				String nac = sc.nextLine();

				Cliente c = new ClienteTarifaPlana(NIF, nom, fNac, fAlta, minutosHablados, nac);
				alta(c);
			}
		} else
			System.out.println("Ya existe un Cliente con ese dni:\n" + (clientes[pos]).toString() + "\n");

		// sc.close();
	}

	public void baja(String NIF) {
		int pos = buscarCliente(NIF);

		if (pos != -1) { // si existe lo doy de baja
			for (int i = pos; i < (nCli - 1); i++)
				clientes[i] = clientes[i + 1];

			nCli--;
		}
	}

	public void baja() {
		Scanner sc = new Scanner(System.in);

		System.out.println("Introduzca nif cliente a dar de baja: ");
		String NIF = sc.nextLine();

		int pos = buscarCliente(NIF);
		System.out.println(clientes[pos].toString());

		char op;

		do {
			System.out.println("¿Seguro que desea eliminarlo (s/n)? ");
			op = sc.next().charAt(0);

		} while (op != 's' && op != 'n');

		if (op == 's') {
			System.out.println("El cliente " + clientes[pos].getNombre() + " con nif " + NIF + " ha sido eliminado\n");
			baja(NIF);
		} else
			System.out.println("El cliente con nif " + NIF + " no se elimina\n");

		// sc.close();
	}

	public Object clone() {
		Empresa e = new Empresa();

		for (int i = 0; i < nCli; i++) {
			if (clientes[i].getClass() == ClienteMovil.class)
				e.alta(new ClienteMovil((ClienteMovil) clientes[i]));
			else
				e.alta(new ClienteTarifaPlana((ClienteTarifaPlana) clientes[i]));
		}

		return e;
	}

	public float factura() {
		float f = 0;

		for (int i = 0; i < nCli; i++) { // recorro el array y compruebo de qué tipo es el cliente
			if (clientes[i].getClass() == ClienteMovil.class)
				f += ((ClienteMovil) clientes[i]).getMinutosHablados() * ((ClienteMovil) clientes[i]).getPrecioMinuto();
			else {
				if (((ClienteTarifaPlana) clientes[i]).getMinutos() > ClienteTarifaPlana.getLimite())
					f += ((float) (ClienteTarifaPlana.getTarifa()
							+ ((((ClienteTarifaPlana) clientes[i]).getMinutos() - ClienteTarifaPlana.getLimite())
									* 0.15)));
				else
					f += ClienteTarifaPlana.getTarifa();
			}
		}
		return f;
	}

	public void descuento(float dto) {
		for (int i = 0; i < nCli; i++) {
			if (clientes[i].getClass() == ClienteMovil.class)
				((ClienteMovil) clientes[i]).setPrecioMinuto(((((ClienteMovil) clientes[i]).getPrecioMinuto() * dto) / 100));
		}
	}

	public int getN() {
		return nCli;
	}

	public int nClienteMovil() {
		int n = 0;

		for (int i = 0; i < nCli; i++) {
			if (clientes[i].getClass() == ClienteMovil.class)
				n++;
		}
		return n;
	}
}

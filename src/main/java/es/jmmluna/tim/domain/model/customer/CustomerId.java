package es.jmmluna.tim.domain.model.customer;

import java.util.Objects;

public class CustomerId {

	private String dni;

	public CustomerId(String dni) throws InvalidDNIException {
		if (!this.isValid(dni))
			throw new InvalidDNIException();
		this.dni = dni;
	}

	public static CustomerId create(String dni) throws InvalidDNIException {
		return new CustomerId(dni);
	}

	public String getValue() {
		return this.dni;
	}

	@Override
	public int hashCode() {
		return Objects.hash(dni);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CustomerId other = (CustomerId) obj;
		return Objects.equals(dni, other.dni);
	}

	@Override
	public String toString() {
		return "CustomerId [dni=" + dni + "]";
	}

	private boolean isValid(String dni) {
		// Solo puede medir 9 (sin guión) o 10 (con guión)
		if (dni.length() != 9 && dni.length() != 10) {
			return false;
		}
		// Si mide 10, debe tener un guión en la posición 8
		if (dni.length() == 10) {
			// Extraerlo y regresar false si no es un guión
			String posibleGuion = String.valueOf(dni.charAt(8));
			if (!posibleGuion.equals("-")) {
				return false;
			}
		}
		// Hasta ahora sabemos que mide 9 o 10 y que el guión es válido (esté presente o
		// no)
		String primerosOcho = dni.substring(0, 8);
		String ultimo = String.valueOf(dni.charAt(dni.length() - 1));
		// Comprobar que los primeros 8 sean numéricos
		if (!primerosOcho.matches("[0-9]+")) {
			return false;
		}
		// Comprobar que el último sea una letra
		if (!ultimo.matches("[A-Z]+")) {
			return false;
		}

		return true;
	}
}

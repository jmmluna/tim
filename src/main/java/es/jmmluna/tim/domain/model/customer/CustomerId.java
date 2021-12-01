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
		return true;
	}
}

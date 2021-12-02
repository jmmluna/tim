package es.jmmluna.tim.domain.model.customer;

import java.util.Objects;

import es.jmmluna.tim.domain.model.customer.validation.InvalidDNIException;

public class CustomerId {

	private String dni;
	private boolean valid = true;
	
	public CustomerId() {
		
	}

	public CustomerId(String dni)  {
		if (!this.isValid(dni))
			valid = false;
		this.dni = dni;
	}
	
	public boolean isValid() {
		return valid;
	}

	public static CustomerId of(String dni)  {
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

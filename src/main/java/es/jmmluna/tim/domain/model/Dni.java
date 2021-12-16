package es.jmmluna.tim.domain.model;

import java.util.Objects;

public class Dni {
	private String dni;
	private boolean valid = true;

	public Dni(String dni)  {
		if (!this.isValid(dni))
			this.valid = false;
		this.dni = dni;
	}
	
	public boolean isValid() {
		return this.valid;
	}

	public static Dni of(String dni)  {
		return new Dni(dni);
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
		Dni other = (Dni) obj;
		return Objects.equals(dni, other.dni);
	}

	@Override
	public String toString() {
		return "Dni [dni=" + dni + "]";
	}

	private boolean isValid(String dni) {		
		return true;
	}
}

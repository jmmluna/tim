package es.jmmluna.tim.domain.model;

import java.util.Objects;

public class Hour {
	private Float value;
	

	public Hour(Float value)  {
		if(value < 0) 
			throw new InvalidHourException("El valor de la hora especificada no puede ser inferios a 0");
		this.value = value;
	}
	
	
	public static Hour of(Float value)  {
		return new Hour(value);
	}

	public Float getValue() {
		return this.value;
	}


	@Override
	public String toString() {
		return "Hour [value=" + value + "]";
	}


	@Override
	public int hashCode() {
		return Objects.hash(value);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Hour other = (Hour) obj;
		return Objects.equals(value, other.value);
	}

	
}

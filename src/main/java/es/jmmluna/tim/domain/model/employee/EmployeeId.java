package es.jmmluna.tim.domain.model.employee;

import java.util.Objects;

import es.jmmluna.tim.domain.model.ValueObject;

public class EmployeeId implements ValueObject {
	private Long id;

	public EmployeeId(Long id) {
		this.id = id;
	}

	public static EmployeeId of(Long id) {
		return new EmployeeId(id);
	}

	public Long getValue() {
		return this.id;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EmployeeId other = (EmployeeId) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "EmployeeId [id=" + id + "]";
	}

}

package es.jmmluna.tim.domain.model.employee.hour;

import java.util.Objects;
import java.util.UUID;

public class EmployeeHourId {
	private UUID uuid;

	public EmployeeHourId(UUID uuid) {
		this.uuid = uuid;
	}

	public EmployeeHourId() {
		this.uuid = UUID.randomUUID();
	}

	public static EmployeeHourId of(UUID uuid) {
		return new EmployeeHourId(uuid);
	}

	public UUID getValue() {
		return this.uuid;
	}

	@Override
	public int hashCode() {
		return Objects.hash(uuid);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EmployeeHourId other = (EmployeeHourId) obj;
		return Objects.equals(uuid, other.uuid);
	}

	@Override
	public String toString() {
		return "EmployeeHourId [uuid=" + uuid + "]";
	}

}

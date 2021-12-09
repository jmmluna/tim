package es.jmmluna.tim.domain.model.customer;

import java.util.Objects;
import java.util.UUID;

public class CustomerId {
	private UUID uuid;

	public CustomerId(String uuid) {
		this.uuid = UUID.fromString(uuid);
	}

	public CustomerId(UUID uuid) {
		this.uuid = uuid;
	}

	public CustomerId() {
		this.uuid = UUID.randomUUID();
	}

	public static CustomerId of(String uuid) {
		return new CustomerId(uuid);
	}

	public static CustomerId of(UUID uuid) {
		return new CustomerId(uuid);
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
		CustomerId other = (CustomerId) obj;
		return Objects.equals(uuid, other.uuid);
	}

	@Override
	public String toString() {
		return "CustomerId [uuid=" + uuid + "]";
	}

}

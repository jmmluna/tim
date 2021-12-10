package es.jmmluna.tim.domain.model.work;

import java.util.Objects;
import java.util.UUID;

public class WorkId {
	private UUID uuid;

	public WorkId(String uuid) {
		this.uuid = UUID.fromString(uuid);
	}

	public WorkId(UUID uuid) {
		this.uuid = uuid;
	}

	public WorkId() {
		this.uuid = UUID.randomUUID();
	}

	public static WorkId of(String uuid) {
		return new WorkId(uuid);
	}

	public static WorkId of(UUID uuid) {
		return new WorkId(uuid);
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
		WorkId other = (WorkId) obj;
		return Objects.equals(uuid, other.uuid);
	}

	@Override
	public String toString() {
		return "WorkId [uuid=" + uuid + "]";
	}

}

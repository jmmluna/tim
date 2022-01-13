package es.jmmluna.tim.domain.model.work;

import java.util.Objects;
import java.util.UUID;

public class WorkItemId {
	private UUID uuid;

	public WorkItemId(UUID uuid) {
		this.uuid = uuid;
	}

	public WorkItemId() {
		this.uuid = UUID.randomUUID();
	}

	public static WorkItemId of(UUID uuid) {
		if(uuid == null) uuid = UUID.randomUUID();
		return new WorkItemId(uuid);
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
		WorkItemId other = (WorkItemId) obj;
		return Objects.equals(uuid, other.uuid);
	}

	@Override
	public String toString() {
		return "WorkId [uuid=" + uuid + "]";
	}

}

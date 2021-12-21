package es.jmmluna.tim.domain.model.budget;

import java.util.Objects;
import java.util.UUID;

public class BudgetItemId {
	private UUID uuid;

	public BudgetItemId(UUID uuid) {
		this.uuid = uuid;
	}

	public BudgetItemId() {
		this.uuid = UUID.randomUUID();
	}

	public static BudgetItemId of(UUID uuid) {
		return new BudgetItemId(uuid);
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
		BudgetItemId other = (BudgetItemId) obj;
		return Objects.equals(uuid, other.uuid);
	}

	@Override
	public String toString() {
		return "WorkId [uuid=" + uuid + "]";
	}

}

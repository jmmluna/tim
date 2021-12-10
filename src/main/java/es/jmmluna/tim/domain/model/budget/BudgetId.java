package es.jmmluna.tim.domain.model.budget;

import java.util.Objects;
import java.util.UUID;

public class BudgetId {
	private UUID uuid;

	public BudgetId(String uuid) {
		this.uuid = UUID.fromString(uuid);
	}

	public BudgetId(UUID uuid) {
		this.uuid = uuid;
	}

	public BudgetId() {
		this.uuid = UUID.randomUUID();
	}

	public static BudgetId of(String uuid) {
		return new BudgetId(uuid);
	}

	public static BudgetId of(UUID uuid) {
		return new BudgetId(uuid);
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
		BudgetId other = (BudgetId) obj;
		return Objects.equals(uuid, other.uuid);
	}

	@Override
	public String toString() {
		return "WorkId [uuid=" + uuid + "]";
	}

}

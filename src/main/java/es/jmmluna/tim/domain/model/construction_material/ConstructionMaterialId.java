package es.jmmluna.tim.domain.model.construction_material;

import java.util.Objects;
import java.util.UUID;

import es.jmmluna.tim.domain.model.ValueObject;

public class ConstructionMaterialId implements ValueObject {
	private UUID uuid;

	public ConstructionMaterialId(String uuid) {
		this.uuid = UUID.fromString(uuid);
	}

	public ConstructionMaterialId(UUID uuid) {
		this.uuid = uuid;
	}

	public ConstructionMaterialId() {
		this.uuid = UUID.randomUUID();
	}

	public static ConstructionMaterialId of(String uuid) {
		return new ConstructionMaterialId(uuid);
	}

	public static ConstructionMaterialId of(UUID uuid) {
		return new ConstructionMaterialId(uuid);
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
		ConstructionMaterialId other = (ConstructionMaterialId) obj;
		return Objects.equals(uuid, other.uuid);
	}

	@Override
	public String toString() {
		return "ConstructionMaterialId [uuid=" + uuid + "]";
	}

}

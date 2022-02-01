package es.jmmluna.tim.domain.model.invoice;

import java.util.Objects;
import java.util.UUID;

public class InvoiceItemId {
	private UUID uuid;

	public InvoiceItemId(UUID uuid) {
		this.uuid = uuid;
	}

	public InvoiceItemId() {
		this.uuid = UUID.randomUUID();
	}

	public static InvoiceItemId of(UUID uuid) {
		if(uuid == null) uuid = UUID.randomUUID();
		return new InvoiceItemId(uuid);
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
		InvoiceItemId other = (InvoiceItemId) obj;
		return Objects.equals(uuid, other.uuid);
	}

	@Override
	public String toString() {
		return "InvoiceItemId [uuid=" + uuid + "]";
	}

}

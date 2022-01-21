package es.jmmluna.tim.domain.model.invoice;

import java.util.Objects;
import java.util.UUID;

public class InvoiceId {
	private UUID uuid;

	public InvoiceId(UUID uuid) {
		this.uuid = uuid;
	}

	public InvoiceId() {
		this.uuid = UUID.randomUUID();
	}

	public static InvoiceId of(UUID uuid) {
		return new InvoiceId(uuid);
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
		InvoiceId other = (InvoiceId) obj;
		return Objects.equals(uuid, other.uuid);
	}

	@Override
	public String toString() {
		return "InvoiceId [uuid=" + uuid + "]";
	}

}

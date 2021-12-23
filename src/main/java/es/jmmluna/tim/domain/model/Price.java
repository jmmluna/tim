package es.jmmluna.tim.domain.model;

import java.util.Objects;

public class Price {
	private Double amount;
	private String currency = "€";

	public Price(Double amount)  {
        if (amount < 0) {
            throw new InvalidPriceException("El importe debe ser mayor que 0 y se ha recibido " + amount);
        }
        
        this.amount = amount;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(amount);
	}

	@Override
	public String toString() {
		return "Price [amount=" + amount + ", currency=" + currency + "]";
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Price other = (Price) obj;
		return Objects.equals(amount, other.amount) && Objects.equals(currency, other.currency);
	}

	public static Price of(Double amount)  {
		return new Price(amount);
	}

	public Double getValue() {
		return this.amount;
	}
	
	public String getCurrency() {
		return this.currency;
	}
}

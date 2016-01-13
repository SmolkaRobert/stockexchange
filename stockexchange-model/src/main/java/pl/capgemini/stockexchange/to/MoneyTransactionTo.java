package pl.capgemini.stockexchange.to;

import java.math.BigDecimal;
import java.util.Currency;

public class MoneyTransactionTo {
	private Currency currency;
	private BigDecimal ammount;
	
	public MoneyTransactionTo(Currency currency, BigDecimal ammount) {
		this.currency = currency;
		this.ammount = ammount;
	}
	
	public Currency getCurrency() {
		return currency;
	}

	public BigDecimal getAmmount() {
		return ammount;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((ammount == null) ? 0 : ammount.hashCode());
		result = prime * result + ((currency == null) ? 0 : currency.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MoneyTransactionTo other = (MoneyTransactionTo) obj;
		if (ammount == null) {
			if (other.ammount != null)
				return false;
		} else if (!ammount.equals(other.ammount))
			return false;
		if (currency == null) {
			if (other.currency != null)
				return false;
		} else if (!currency.equals(other.currency))
			return false;
		return true;
	}
}

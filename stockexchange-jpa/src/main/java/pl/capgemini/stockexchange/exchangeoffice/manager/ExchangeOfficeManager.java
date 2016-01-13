package pl.capgemini.stockexchange.exchangeoffice.manager;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.List;

import org.springframework.stereotype.Component;

import pl.capgemini.stockexchange.to.MoneyTransactionTo;

@Component
public class ExchangeOfficeManager {
	public BigDecimal changeMoneyToCurrency(List<MoneyTransactionTo> money, Currency defaultCurrency) {
		return null;
	}
}

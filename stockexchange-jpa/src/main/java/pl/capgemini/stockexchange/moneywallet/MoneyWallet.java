package pl.capgemini.stockexchange.moneywallet;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.List;
import java.util.Map;

import pl.capgemini.stockexchange.to.MoneyTransactionTo;

public interface MoneyWallet {
	public void clear();
	
	public boolean isEmpty();

	public List<MoneyTransactionTo> getAllMoney();
	
	public List<MoneyTransactionTo> getMoneyByCurrencies(List<Currency> currenciesToGet);

	public void updateContent(List<MoneyTransactionTo> moneyToChange) throws NegativeMoneyForCurrencyInWalletException;
}

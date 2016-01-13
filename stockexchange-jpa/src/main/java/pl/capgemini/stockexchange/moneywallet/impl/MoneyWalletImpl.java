package pl.capgemini.stockexchange.moneywallet.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Currency;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import pl.capgemini.stockexchange.moneywallet.MoneyWallet;
import pl.capgemini.stockexchange.moneywallet.NegativeMoneyForCurrencyInWalletException;
import pl.capgemini.stockexchange.to.MoneyTransactionTo;

@Component
public class MoneyWalletImpl implements MoneyWallet {

	private static final Integer ZERO_FOR_COMPARISON = 0;
	
	private Map<Currency, BigDecimal> money = new HashMap<Currency, BigDecimal>();

	@Override
	public List<MoneyTransactionTo> getAllMoney() {
		List<MoneyTransactionTo> allMoney = new ArrayList<MoneyTransactionTo>();
		for (Map.Entry<Currency, BigDecimal> moneyOfCurrency : money.entrySet()) {
			allMoney.add(new MoneyTransactionTo(moneyOfCurrency.getKey(), moneyOfCurrency.getValue()));
		}
		return allMoney;
	}

	@Override
	public List<MoneyTransactionTo> getMoneyByCurrencies(List<Currency> currenciesToGet) {
		List<MoneyTransactionTo> selectedMoney = new ArrayList<MoneyTransactionTo>();
		for (Currency singleCurrency : currenciesToGet) {
			addOnlyExistingCurrencies(selectedMoney, singleCurrency);
		}
		return selectedMoney;
	}

	private void addOnlyExistingCurrencies(List<MoneyTransactionTo> selectedMoney, Currency singleCurrency) {
		BigDecimal ammountForSingleCurrency = money.get(singleCurrency);
		if (ammountForSingleCurrency != null) {
			selectedMoney.add(new MoneyTransactionTo(singleCurrency, ammountForSingleCurrency));
		}
	}

	@Override
	public boolean isEmpty() {
		return this.money.isEmpty();
	}

	@Override
	public void updateContent(List<MoneyTransactionTo> moneyToChange) throws NegativeMoneyForCurrencyInWalletException {
		updateExisitingContentWith(moneyToChange);
	}

	public void clear() {
		this.money.clear();
	}

	private void updateExisitingContentWith(List<MoneyTransactionTo> moneyToChange) throws NegativeMoneyForCurrencyInWalletException {
		for(MoneyTransactionTo singleMoneyTransactionTo : moneyToChange) {
			Currency currency = singleMoneyTransactionTo.getCurrency();

			BigDecimal previousMoney = getPreviousMoney(currency);

			BigDecimal newMoney = previousMoney.add(singleMoneyTransactionTo.getAmmount());
			
			if(newMoney.compareTo(BigDecimal.ZERO) < ZERO_FOR_COMPARISON){
				throw new NegativeMoneyForCurrencyInWalletException(currency);
			}
			this.money.put(currency, newMoney);
		}
	}

	private BigDecimal getPreviousMoney(Currency currency) {
		BigDecimal previousMoney = money.get(currency);
		if (previousMoney == null) {
			previousMoney = new BigDecimal(0.0);
		}
		return previousMoney;
	}

}

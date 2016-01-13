package pl.capgemini.stockexchange.moneywallet;

import java.util.Currency;

public class NegativeMoneyForCurrencyInWalletException extends Exception {
	private static final String NEGATIVE_MONEY_FOR_CURRENCY_EXCEPTION_MESSAGE = "Cannot have negative money for currency: ";

	public NegativeMoneyForCurrencyInWalletException(Currency curr) {
		super(NEGATIVE_MONEY_FOR_CURRENCY_EXCEPTION_MESSAGE + curr.getCurrencyCode());
	}

}
package pl.capgemini.stockexchange.stockmarket;

public class DateFromTheFutureForStockExchangeException extends Exception {
	private static final String DATE_FROM_FUTURE_EXCEPTION_MESSAGE = "Cannot get shares from future.";

	public DateFromTheFutureForStockExchangeException() {
		super(DATE_FROM_FUTURE_EXCEPTION_MESSAGE);
	}

}
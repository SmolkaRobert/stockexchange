package pl.capgemini.stockexchange.stockmarket;

public class DateFromTheFutureForStockMarketException extends Exception {
	public DateFromTheFutureForStockMarketException() {
		super();
	}

	public DateFromTheFutureForStockMarketException(String message) {
		super(message);
	}
}
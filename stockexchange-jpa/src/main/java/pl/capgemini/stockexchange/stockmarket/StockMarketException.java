package pl.capgemini.stockexchange.stockmarket;

public class StockMarketException extends Exception {
	public StockMarketException() {
		super();
	}

	public StockMarketException(String message) {
		super(message);
	}
}
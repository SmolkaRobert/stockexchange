package pl.capgemini.stockexchange.gamestrategy;

import pl.capgemini.stockexchange.stockmarket.DateFromTheFutureForStockExchangeException;
import pl.capgemini.stockexchange.to.OfferListTo;


public interface GameStrategy {
	public OfferListTo calculate() throws DateFromTheFutureForStockExchangeException;
}

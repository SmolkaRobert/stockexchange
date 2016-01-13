package pl.capgemini.stockexchange.game;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;

import pl.capgemini.stockexchange.bigdecimalutils.BigDecimalUtils;
import pl.capgemini.stockexchange.brokeroffice.manager.BrokerOfficeManager;
import pl.capgemini.stockexchange.calendar.StockCalendar;
import pl.capgemini.stockexchange.exchangeoffice.manager.ExchangeOfficeManager;
import pl.capgemini.stockexchange.gamestrategy.GameStrategy;
import pl.capgemini.stockexchange.player.Player;
import pl.capgemini.stockexchange.stockmarket.DateFromTheFutureForStockExchangeException;
import pl.capgemini.stockexchange.to.MoneyTransactionTo;
import pl.capgemini.stockexchange.to.OfferListTo;
import pl.capgemini.stockexchange.to.ShareEvaluatedTo;

//@Component
public class Game {
	private final Currency defaultCurrency = Currency.getInstance(new Locale("pl", "PL"));
	private BigDecimal currentPlayerMoneyInOneCurrency;
	private BigDecimal currentBuyCost;

	@Autowired
	private GameStrategy gameStrategy;
	@Autowired
	private StockCalendar calendar;
	@Autowired
	private Player player;
	@Autowired
	private BrokerOfficeManager brokerOfficeManager;
	@Autowired
	private ExchangeOfficeManager exchangeOfficeManager;

	public void play() throws DateFromTheFutureForStockExchangeException {
		while (!calendar.passedLastDay()) {
			List<MoneyTransactionTo> moneyFromWallet = player.getMoneyFromWallet();
			currentPlayerMoneyInOneCurrency = exchangeOfficeManager.changeMoneyToCurrency(moneyFromWallet, defaultCurrency);

			OfferListTo plannedTransactions = gameStrategy.calculate();

			List<ShareEvaluatedTo> evaluatedShares = brokerOfficeManager.evaluateTransaction(plannedTransactions.getSharesToBuy());
			currentBuyCost = brokerOfficeManager.calculateEvaluatedCost();
			// possible addition: modify strategy if cost is too much or not enough shares
			
			if (isEnoughMoneyToBuy()) {
				
				// buy shares => get money from wallet and add shares to share wallet
			}
			
			//at the end change half money into Euros

			calendar.goToNextDay();
		}
	}

	private boolean isEnoughMoneyToBuy() {
		return BigDecimalUtils.isGreaterOrEqual(currentPlayerMoneyInOneCurrency, currentBuyCost);
	}
}

package pl.capgemini.stockexchange.gamestrategy.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import pl.capgemini.stockexchange.brokeroffice.informator.BrokerOfficeInformator;
import pl.capgemini.stockexchange.calendar.StockCalendar;
import pl.capgemini.stockexchange.gamestrategy.GameStrategy;
import pl.capgemini.stockexchange.stockmarket.DateFromTheFutureForStockExchangeException;
import pl.capgemini.stockexchange.to.ShareTo;
import pl.capgemini.stockexchange.to.ShareOfferTo;
import pl.capgemini.stockexchange.to.OfferListTo;

@Component
public class GameStrategyImpl implements GameStrategy {

	@Autowired
	private BrokerOfficeInformator brokerInformator;
	@Autowired
	private StockCalendar calendar;

	@Override
	public OfferListTo calculate()
			throws DateFromTheFutureForStockExchangeException {

		return prepareStrategy();
	}

	private OfferListTo prepareStrategy()
			throws DateFromTheFutureForStockExchangeException {
		// TODO RSmolka strategy stub; prepare proper strategy in the future
		List<ShareTo> todayShares = brokerInformator.getSharesForDate(calendar.getCurrentDate());
		
		OfferListTo transactionsToReturn = new OfferListTo(chooseSharesToBuy(todayShares), chooseSharesToSell(todayShares));

		return transactionsToReturn;
	}

	private List<ShareOfferTo> chooseSharesToBuy(List<ShareTo> todayShares) {
		List<ShareTo> sharesToBuy = new ArrayList<ShareTo>();
		sharesToBuy.add(todayShares.get(0));

		return prepareShareOfferList(sharesToBuy);

	}

	private List<ShareOfferTo> chooseSharesToSell(List<ShareTo> todayShares) {
		List<ShareTo> sharesToSell = new ArrayList<ShareTo>();
		sharesToSell.add(todayShares.get(0));

		return prepareShareOfferList(sharesToSell);
	}

	private List<ShareOfferTo> prepareShareOfferList(List<ShareTo> shareToTransact) {
		Integer sharesAmmount = 5;
		List<ShareOfferTo> sharesForTransaction = new ArrayList<ShareOfferTo>();

		for (ShareTo singleShare : shareToTransact) {
			sharesForTransaction.add(new ShareOfferTo(singleShare.getCompany(), sharesAmmount));
		}
		return sharesForTransaction;
	}

}

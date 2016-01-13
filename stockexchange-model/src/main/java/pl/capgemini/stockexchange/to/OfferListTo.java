package pl.capgemini.stockexchange.to;

import java.util.List;

public class OfferListTo {
	List<ShareOfferTo> sharesToBuy;
	List<ShareOfferTo> sharesToSell;
	
	public OfferListTo(List<ShareOfferTo> sharesToBuy, List<ShareOfferTo> sharesToSell) {
		this.sharesToBuy = sharesToBuy;
		this.sharesToSell = sharesToSell;
	}
	
	public List<ShareOfferTo> getSharesToBuy() {
		return sharesToBuy;
	}
	public List<ShareOfferTo> getSharesToSell() {
		return sharesToSell;
	}
}

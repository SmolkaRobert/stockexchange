package pl.capgemini.stockexchange.sharewallet;

import java.util.List;
import java.util.Map;

import pl.capgemini.stockexchange.to.CompanyTo;
import pl.capgemini.stockexchange.to.ShareOfferTo;

public interface ShareWallet {
	
	public boolean isEmpty();

	public void updateContent();

	public List<ShareOfferTo> getShares();
}
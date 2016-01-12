package pl.capgemini.stockexchange.sharewallet;

import java.util.Map;

import pl.capgemini.stockexchange.to.CompanyTo;

public interface ShareWallet {
	public Map<CompanyTo, Integer> getContent();
	
	public boolean isEmpty();

	public void updateContent();
}
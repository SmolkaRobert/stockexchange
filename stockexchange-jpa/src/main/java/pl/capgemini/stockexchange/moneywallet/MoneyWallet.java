package pl.capgemini.stockexchange.moneywallet;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.Map;

public interface MoneyWallet {
	public Map<Currency, BigDecimal> getContent();
	
	public boolean isEmpty();

	public void updateContent(Map<Currency, BigDecimal> contentToAdd);
	
	public void clear();
}

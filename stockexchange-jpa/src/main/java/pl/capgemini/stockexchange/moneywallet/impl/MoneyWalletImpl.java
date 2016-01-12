package pl.capgemini.stockexchange.moneywallet.impl;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import pl.capgemini.stockexchange.moneywallet.MoneyWallet;

@Component
public class MoneyWalletImpl implements MoneyWallet {
	
	private Map<Currency, BigDecimal> content = new HashMap<Currency, BigDecimal>();

	@Override
	public Map<Currency, BigDecimal> getContent() {
		return this.content;
	}

	@Override
	public boolean isEmpty() {
		return this.content.isEmpty();
	}

	@Override
	public void updateContent(Map<Currency, BigDecimal> moneyToChange) {
		for(Map.Entry<Currency, BigDecimal> moneyOfOneCurrency : moneyToChange.entrySet()){
		    Currency currency = moneyOfOneCurrency.getKey();
		    
			BigDecimal previousMoney = content.get(currency);
			if(previousMoney == null){
				previousMoney = new BigDecimal(0.0);
			}
			
		    content.put(currency, previousMoney.add(moneyOfOneCurrency.getValue()));
		}
	}
	
	public void clear(){
		content.clear();
	}
	
}

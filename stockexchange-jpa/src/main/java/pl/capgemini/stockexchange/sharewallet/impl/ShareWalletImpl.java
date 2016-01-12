package pl.capgemini.stockexchange.sharewallet.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import pl.capgemini.stockexchange.sharewallet.ShareWallet;
import pl.capgemini.stockexchange.to.CompanyTo;

@Component
public class ShareWalletImpl implements ShareWallet {
	
	private HashMap<CompanyTo, Integer> content = new HashMap<CompanyTo, Integer>();

	@Override
	public Map<CompanyTo, Integer> getContent() {
		return content;
	}
	
	public boolean isEmpty(){
		return content.isEmpty();
	}
	
	@Override
	public void updateContent() {
		
	}
	
}

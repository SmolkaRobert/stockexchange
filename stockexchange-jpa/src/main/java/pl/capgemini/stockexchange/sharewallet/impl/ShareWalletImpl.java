package pl.capgemini.stockexchange.sharewallet.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import pl.capgemini.stockexchange.sharewallet.ShareWallet;
import pl.capgemini.stockexchange.to.ShareOfferTo;

@Component
public class ShareWalletImpl implements ShareWallet {
	
	private List<ShareOfferTo> shares = new ArrayList<ShareOfferTo>();

	@Override
	public List<ShareOfferTo> getShares() {
		return shares;
	}
	
	public boolean isEmpty(){
		return shares.isEmpty();
	}
	
	@Override
	public void updateContent() {
		
	}
	
}

package pl.capgemini.stockexchange.gamestrategy.impl;

import org.springframework.beans.factory.annotation.Autowired;

import pl.capgemini.stockexchange.gamestrategy.GameStrategy;
import pl.capgemini.stockexchange.sharewallet.ShareWallet;

public class GameStrategyImpl implements GameStrategy {
	
	@Autowired
	private ShareWallet shareWallet;

	@Override
	public void execute() {
	}

}

package pl.capgemini.stockexchange.player;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.capgemini.stockexchange.moneywallet.MoneyWallet;
import pl.capgemini.stockexchange.sharewallet.ShareWallet;
import pl.capgemini.stockexchange.to.MoneyTransactionTo;

@Service
public class Player{
	
	@Autowired
	private MoneyWallet moneyWallet;
	@Autowired
	private ShareWallet shareWallet;
	
	public List<MoneyTransactionTo> getMoneyFromWallet() {
		return moneyWallet.getAllMoney();
	}

}

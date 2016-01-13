package pl.capgemini.stockexchange.main;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import pl.capgemini.stockexchange.game.Game;
import pl.capgemini.stockexchange.stockmarket.DateFromTheFutureForStockExchangeException;

public class StockGame {

	private static final String CONTEXT_PATH = "/stockexchange-jpa/src/test/resources/pl/capgemini/stockexchange/common_configuration/CommonDatabase-context.xml";

	public static void main(String[] args) throws DateFromTheFutureForStockExchangeException {
		StockGame stockGame = new StockGame();
		stockGame.play(args);
	}
	
	private void play(String[] args) throws DateFromTheFutureForStockExchangeException{
		try(ClassPathXmlApplicationContext gameContext = new ClassPathXmlApplicationContext(CONTEXT_PATH)){
			Game singleGame = (Game) gameContext.getBean("Game");
			singleGame.play();
		}
	}

}

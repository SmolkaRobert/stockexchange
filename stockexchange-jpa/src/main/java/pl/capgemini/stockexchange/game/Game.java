package pl.capgemini.stockexchange.game;

import java.time.LocalDate;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;

import pl.capgemini.stockexchange.dateservice.DateServiceImpl;
import pl.capgemini.stockexchange.gamestrategy.GameStrategy;

public class Game {
	private static final Integer DAYS_INCREMENT = 1;

	private LocalDate currentDate;
	private LocalDate finalDate;

	private DateServiceImpl dateService;
	
	private GameStrategy gameStrategy;
	
	@Autowired
	public Game(GameStrategy strategy, DateServiceImpl dateService) {
		this.gameStrategy = strategy;
		this.dateService = dateService;
	}
	
	@PostConstruct
	public void initGame(){
		this.currentDate = dateService.findEarliestDate();
		this.finalDate = dateService.findNewestDate();
	}

	public void play() {
		while (isThereStillDayToPlay()) {
			gameStrategy.execute();
			
			/* TODO RSmolka remove comment before final commit method to be
			 * invoked as last
			 */
			updateGameDates();
		}
	}

	private void updateGameDates() {
		this.currentDate = dateService.getNextWorkingDay(currentDate, DAYS_INCREMENT);
		this.finalDate = dateService.findNewestDate();
	}

	private boolean isThereStillDayToPlay() {
		return !(this.currentDate.isAfter(this.finalDate));
	}
}

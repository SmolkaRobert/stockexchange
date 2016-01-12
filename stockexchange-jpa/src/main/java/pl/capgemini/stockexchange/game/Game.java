package pl.capgemini.stockexchange.game;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import pl.capgemini.stockexchange.dateservice.DateServiceImpl;

public class Game {
	private static final Integer DAYS_INCREMENT = 1;

	private LocalDate currentDate;
	private LocalDate finalDate;

	@Autowired
	private DateServiceImpl dateService;

	Game() {
		this.currentDate = dateService.findEarliestDate();
		this.finalDate = dateService.findNewestDate();
	}

	public void play() {
		while (isThereStillDayToGo()) {
			
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

	private boolean isThereStillDayToGo() {
		return this.currentDate.isAfter(this.finalDate);
	}
}

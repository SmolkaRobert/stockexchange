package pl.capgemini.stockexchange.calendar;

import java.time.LocalDate;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.capgemini.stockexchange.dateservice.DateService;

@Service
public class StockCalendar {
	private static final Integer DAYS_INCREMENT = 1;
	
	@Autowired
	private DateService dateService;
	
	private LocalDate currentDate;
	private LocalDate lastDate;
	
	@PostConstruct
	public void initialzieCalendar(){
		this.currentDate = dateService.findEarliestDate();
		this.lastDate = dateService.findLastDate();
	}
	
	public void goToNextDay(){
		this.currentDate = dateService.getNextWorkingDate(currentDate, DAYS_INCREMENT);
	}
	
	public LocalDate getCurrentDate(){
		return this.currentDate;
	}

	public boolean passedLastDay() {
		return this.currentDate.isAfter(this.lastDate);
	}
	
	
	public LocalDate getLastDate() {
		return this.lastDate;
	}
	
}

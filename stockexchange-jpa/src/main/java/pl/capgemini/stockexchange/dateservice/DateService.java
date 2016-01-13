package pl.capgemini.stockexchange.dateservice;

import java.time.LocalDate;

public interface DateService {
	public LocalDate findLastDate();

	public LocalDate findEarliestDate();
	
	public LocalDate getNextWorkingDate(LocalDate currentDate, Integer daysIncrement);
}

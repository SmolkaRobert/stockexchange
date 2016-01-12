package pl.capgemini.stockexchange.dateservice;

import java.time.LocalDate;

public interface DateService {
	LocalDate findNewestDate();

	LocalDate findEarliestDate();
}

package pl.capgemini.stockexchange.dateservice;

import java.time.DayOfWeek;
import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.capgemini.stockexchange.mapper.DateMapper;
import pl.capgemini.stockexchange.repository.ShareRepositoryImpl;

@Service
public class DateServiceImpl implements DateService {
	@Autowired
	private ShareRepositoryImpl shareRepository;
	@Autowired
	private DateMapper dateMapper;

	@Override
	public LocalDate findEarliestDate() {
		return dateMapper.convertToEntityAttribute(shareRepository.findEarliestDate());
	}
	
	@Override
	public LocalDate findNewestDate() {
		return dateMapper.convertToEntityAttribute(shareRepository.findNewestDate());
	}

	public LocalDate getNextWorkingDay(LocalDate currentDate, Integer daysIncrement) {
	    final Integer daysToAdd = 1;
	    
		if (daysIncrement < daysToAdd) {
	        return currentDate;
	    }

	    LocalDate nextDate = currentDate;
	    Integer addedDays = 0;
	    while (addedDays < daysIncrement) {
	        nextDate = nextDate.plusDays(daysToAdd);
	        if (!(nextDate.getDayOfWeek() == DayOfWeek.SATURDAY || nextDate.getDayOfWeek() == DayOfWeek.SUNDAY)) {
	            ++addedDays;
	        }
	    }

	    return nextDate;
	}
}

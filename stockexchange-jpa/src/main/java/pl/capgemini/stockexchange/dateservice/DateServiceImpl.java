package pl.capgemini.stockexchange.dateservice;

import java.time.DayOfWeek;
import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.capgemini.stockexchange.mapper.DateMapper;
import pl.capgemini.stockexchange.repository.ShareRepositoryImpl;

@Service
public class DateServiceImpl implements DateService {
	private static final int DAYS_TO_ADD = 1;
	@Autowired
	private ShareRepositoryImpl shareRepository;
	@Autowired
	private DateMapper dateMapper;

	@Override
	public LocalDate findEarliestDate() {
		return dateMapper.convertToEntityAttribute(shareRepository.findEarliestDate());
	}
	
	@Override
	public LocalDate findLastDate() {
		return dateMapper.convertToEntityAttribute(shareRepository.findLastDate());
	}

	public LocalDate getNextWorkingDate(LocalDate currentDate, Integer daysIncrement) {
	    final Integer daysToAdd = DAYS_TO_ADD;
	    
		if (daysIncrement < daysToAdd) {
	        return currentDate;
	    }

	    LocalDate nextWorkingDate = currentDate;
	    Integer addedDays = 0;
	    while (addedDays < daysIncrement) {
	        nextWorkingDate = nextWorkingDate.plusDays(daysToAdd);
	        if (!(nextWorkingDate.getDayOfWeek() == DayOfWeek.SATURDAY || nextWorkingDate.getDayOfWeek() == DayOfWeek.SUNDAY)) {
	            ++addedDays;
	        }
	    }

	    return nextWorkingDate;
	}
}

package pl.capgemini.stockexchange.mapper;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.springframework.stereotype.Service;

@Service
public class LocalDateMapper {
    
	private static final DateTimeFormatter dateFormatter = DateTimeFormatter.BASIC_ISO_DATE;
	
    public LocalDate mapDate(Integer issueDate) {
		return LocalDate.parse(issueDate.toString(), dateFormatter);
    }
    
    public Integer mapDate(LocalDate issueDate) {
    	return Integer.parseInt(issueDate.format(dateFormatter));
    }
}

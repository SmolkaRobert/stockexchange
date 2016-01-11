package pl.capgemini.stockexchange.mapper;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import org.springframework.stereotype.Service;

@Converter(autoApply = true)
@Service
public class DateMapper implements AttributeConverter<LocalDate, Date>{
    
	private final DateTimeFormatter dateFormatter = DateTimeFormatter.BASIC_ISO_DATE;
	
	@Override
	public Date convertToDatabaseColumn(LocalDate issueDate) {
    	return (issueDate == null ? null : Date.from(issueDate.atStartOfDay(ZoneId.systemDefault()).toInstant()));
	}

	@Override
	public LocalDate convertToEntityAttribute(Date issueDate) {
		return (issueDate == null ? null : Instant.ofEpochMilli(issueDate.getTime()).atZone(ZoneId.systemDefault()).toLocalDate());
	}
	
	public Date convertIntegerToDate(Integer issueDate) {
		return (issueDate == null ? null : convertToDatabaseColumn(convertIntegerToLocalDate(issueDate)));
	}
	
	public LocalDate convertIntegerToLocalDate(Integer issueDate) {
		return (issueDate == null ? null : LocalDate.from(dateFormatter.parse(issueDate.toString())));
	}
	
}

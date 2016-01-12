package pl.capgemini.stockexchange.dateservice;

import java.time.LocalDate;
import java.time.temporal.TemporalAmount;
import java.time.temporal.TemporalField;
import java.time.temporal.WeekFields;
import java.util.List;
import java.util.Locale;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import pl.capgemini.stockexchange.entity.CompanyEntity;
import pl.capgemini.stockexchange.mapper.DateMapper;
import pl.capgemini.stockexchange.to.CompanyTo;
import pl.capgemini.stockexchange.to.ShareTo;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "CommonDateServiceTest-context.xml")
public class DateServiceImplTest {
	
	private static final int FIRST_ISSUE_DAY = 20011024;
	private static final int LAST_ISSUE_DAY = 20011026;
	private static final int THURSDAY_NUMBER_IN_WEEK = 4;
	private static final int FRIDAY_NUMBER_IN_WEEK = 5;

	@Autowired
	private DateServiceImpl dateService;
	
	@Autowired
	private DateMapper dateMapper;
		
	private LocalDate maxDate;
	private LocalDate minDate;
	private LocalDate thursdayDate;
	private LocalDate fridayDate;
	
	@Before
	public void testSetUp(){
		maxDate = dateMapper.convertIntegerToLocalDate(LAST_ISSUE_DAY);
		minDate = dateMapper.convertIntegerToLocalDate(FIRST_ISSUE_DAY);
		
		TemporalField fieldISO = WeekFields.of(Locale.GERMANY).dayOfWeek();
		thursdayDate = minDate.with(fieldISO, THURSDAY_NUMBER_IN_WEEK);
		fridayDate = minDate.with(fieldISO, FRIDAY_NUMBER_IN_WEEK);
	}
	
	@Test
	public void shouldFindNewsetDate(){
		//given
		LocalDate searchedDate = maxDate;
		//when
		LocalDate returnedDate = dateService.findNewestDate();
		//then
		Assertions.assertThat(returnedDate).isNotNull().isEqualTo(searchedDate);
	}
	
	@Test
	public void shouldFindEarliestDate(){
		//given
		LocalDate searchedDate = minDate;
		//when
		LocalDate returnedDate = dateService.findEarliestDate();
		//then
		Assertions.assertThat(returnedDate).isNotNull().isEqualTo(searchedDate);
	}
	
	@Test
	public void shouldReturnNextDayWhenBegunOnThursday(){
		//given
		final Integer daysToAdd = 1;
		LocalDate startDate = thursdayDate;
		LocalDate searchedDate = startDate.plusDays(daysToAdd);
		//when
		LocalDate returnedDate = dateService.getNextWorkingDay(startDate, daysToAdd);
		//then
		Assertions.assertThat(returnedDate).isEqualTo(searchedDate);
	}
	
	@Test
	public void shouldReturnThirdDayWhenBegunOnFriday(){
		//given
		final Integer daysToAdd = 1;
		final Integer daysToSkip = 2;
		final Integer numberOfDaysPassed = daysToAdd + daysToSkip;
		
		LocalDate startDate = fridayDate;
		LocalDate searchedDate = startDate.plusDays(numberOfDaysPassed);
		//when
		LocalDate returnedDate = dateService.getNextWorkingDay(startDate, daysToAdd);
		//then
		Assertions.assertThat(returnedDate).isEqualTo(searchedDate);
	}
}

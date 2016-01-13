package pl.capgemini.stockexchange.calendar;

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
@ContextConfiguration(locations = "CommonCalendarTest-context.xml")
public class StockCalendarTest {

	private static final int FIRST_ISSUE_DAY = 20011024;
	private static final int SECOND_ISSUE_DAY = 20011025;
	private static final int LAST_ISSUE_DAY = 20011026;

	@Autowired
	private StockCalendar calendar;

	@Autowired
	private DateMapper dateMapper;

	private LocalDate firstDate;
	private LocalDate secondDate;
	private LocalDate lastDate;

	@Before
	public void testSetUp() {
		firstDate = dateMapper.convertIntegerToLocalDate(FIRST_ISSUE_DAY);
		secondDate = dateMapper.convertIntegerToLocalDate(SECOND_ISSUE_DAY);
		lastDate = dateMapper.convertIntegerToLocalDate(LAST_ISSUE_DAY);
	}

	@Test
	public void shouldInitializeCalendarWithProperDates() {
		// given
		LocalDate currentDate = firstDate;
		LocalDate finalDate = lastDate;
		// when
		LocalDate returnedCurrentDate = calendar.getCurrentDate();
		LocalDate returnedLastDate = calendar.getLastDate();
		// then
		Assertions.assertThat(returnedCurrentDate).isNotNull().isEqualTo(currentDate);
		Assertions.assertThat(returnedLastDate).isNotNull().isEqualTo(finalDate);
	}

	@Test
	public void shouldGoToTheSecondDay() {
		// given
		calendar.initialzieCalendar();
		LocalDate searchedDate = secondDate;
		// when
		calendar.goToNextDay();
		LocalDate returnedDate = calendar.getCurrentDate();
		// then
		Assertions.assertThat(returnedDate).isNotNull().isEqualTo(searchedDate);
	}

	@Test
	public void shouldReturnFalseForGoingPastLastDay() {
		// given
		int dayToPass = 3;
		boolean isPastLastDay;
		calendar.initialzieCalendar();
		// when
		for (int i = 0; i <= dayToPass ; i++) {
			calendar.goToNextDay();
		}
		
		isPastLastDay = calendar.passedLastDay();
		
		// then
		Assertions.assertThat(isPastLastDay).isTrue();
	}
}

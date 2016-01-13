package pl.capgemini.stockexchange.repository;

import java.util.Date;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import pl.capgemini.stockexchange.entity.CompanyEntity;
import pl.capgemini.stockexchange.entity.ShareEntity;
import pl.capgemini.stockexchange.mapper.DateMapper;
import pl.capgemini.stockexchange.stockmarket.StockMarketImpl;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "CommonRepositoryTest-context.xml")
public class ShareRepositoryImplTest {
	
	@Autowired
	private ShareRepositoryImpl shareRepository;
	
	@Autowired
	private DateMapper dateMapper;
	
	private String firstListedCompanyName;
	private String secondListedCompanyName;
	private String notListedCompanyName;
	
	private Date dateListed;
	private Date minDate;
	private Date dateNotListed;
	
	private CompanyEntity firstListedCompany;
	private CompanyEntity secondListedCompany;
	private CompanyEntity notListedCompany;
	
	@Before
	public void testSetUp(){
		firstListedCompanyName =  "MICROSOFT";
		secondListedCompanyName = "INTEL";
		notListedCompanyName = "NOTLISTEDCOMPANY";
		
		dateListed = dateMapper.convertIntegerToDate(20011024);
		dateNotListed = dateMapper.convertIntegerToDate(20011020);
		
		firstListedCompany = new CompanyEntity(firstListedCompanyName);
		secondListedCompany = new CompanyEntity(secondListedCompanyName);
		notListedCompany = new CompanyEntity(notListedCompanyName);
	}
	
	@Test
	public void shouldReturnEmptyListForDateNotListed(){
		//given
		Date searchedDate = dateNotListed;
		//when
		List<ShareEntity> shares = shareRepository.findSharesByDate(searchedDate);
		//then
		Assertions.assertThat(shares).isNotNull().isEmpty();
	}
	
	@Test
	public void shouldFindAllSharesByDate(){
		//given
		CompanyEntity notSearchedCompany = notListedCompany;
		CompanyEntity searchedCompany1 = firstListedCompany;
		CompanyEntity searchedCompany2 = secondListedCompany;
		
		Date searchedDate = dateListed;
		//when
		List<ShareEntity> shares = shareRepository.findSharesByDate(searchedDate);
		//then
		Assertions.assertThat(shares).isNotNull().isNotEmpty()
			.extracting("company").extracting("name").contains(searchedCompany1.getName(), searchedCompany2.getName()).doesNotContain(notSearchedCompany.getName());
	}
	
	@Test
	public void shouldFindNewestDate(){
		//given
		minDate = dateMapper.convertIntegerToDate(20011026);
		//when
		Date searchedDate = shareRepository.findLastDate();
		//then
		Assertions.assertThat(searchedDate).isNotNull().isEqualTo(minDate);
	}
	
	@Test
	public void shouldFindEaliestDate(){
		//given
		minDate = dateMapper.convertIntegerToDate(20011024);
		//when
		Date searchedDate = shareRepository.findEarliestDate();
		//then
		Assertions.assertThat(searchedDate).isNotNull().isEqualTo(minDate);
	}
}

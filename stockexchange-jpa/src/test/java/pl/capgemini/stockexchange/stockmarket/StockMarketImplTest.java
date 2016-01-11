package pl.capgemini.stockexchange.stockmarket;

import java.time.LocalDate;
import java.util.List;

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
@ContextConfiguration(locations = "CommonStockMarketTest-context.xml")
public class StockMarketImplTest {
	
	@Autowired
	private StockMarketImpl stockMarket;
	
	@Autowired
	private DateMapper dateMapper;
	
	private String firstListedCompanyName;
	private String secondListedCompanyName;
	private String notListedCompanyName;
	
	private LocalDate dateListed;
	private LocalDate dateNotListed;
	
	private CompanyTo firstListedCompany;
	private CompanyTo secondListedCompany;
	private CompanyTo notListedCompany;
	
	@Before
	public void testSetUp(){
		firstListedCompanyName =  "MICROSOFT";
		secondListedCompanyName = "INTEL";
		notListedCompanyName = "NOTLISTEDCOMPANY";
		
		dateListed = dateMapper.convertIntegerToLocalDate(20011024);
		dateNotListed = dateMapper.convertIntegerToLocalDate(20011001);
		
		firstListedCompany = new CompanyTo(firstListedCompanyName);
		secondListedCompany = new CompanyTo(secondListedCompanyName);
		notListedCompany = new CompanyTo(notListedCompanyName);
	}
	
	@Test
	public void shouldReturnEmptyListForDateNotListed(){
		//given
		LocalDate searchedDate = dateNotListed;
		//when
		List<ShareTo> shares = stockMarket.findSharesByDate(searchedDate);
		//then
		Assertions.assertThat(shares).isNotNull().isEmpty();
	}
	
	@Test
	public void shouldFindAllSharesByDate(){
		//given
		CompanyTo notSearchedCompany = notListedCompany;
		CompanyTo searchedCompany1 = firstListedCompany;
		CompanyTo searchedCompany2 = secondListedCompany;
		
		LocalDate searchedDate = dateListed;
		//when
		List<ShareTo> shares = stockMarket.findSharesByDate(searchedDate);
		//then
		Assertions.assertThat(shares).isNotNull().isNotEmpty()
		.extracting("company").extracting("name").contains(searchedCompany1.getName(), searchedCompany2.getName()).doesNotContain(notSearchedCompany.getName());
		
		for(ShareTo singleShare : shares){
			Assertions.assertThat(singleShare.getIssueDate()).isEqualTo(searchedDate);
		}
	}
	
	@Test
	public void shouldReturnAllListedCompanies(){
		//given
		CompanyTo notSearchedCompany = notListedCompany;
		CompanyTo searchedCompany1 = firstListedCompany;
		CompanyTo searchedCompany2 = secondListedCompany;
		
		LocalDate searchedDate = dateListed;
		//when
		List<CompanyTo> companies = stockMarket.findAllCompanies();
		//then
		Assertions.assertThat(companies).isNotNull().isNotEmpty()
		.extracting("name").contains(searchedCompany1.getName(), searchedCompany2.getName()).doesNotContain(notSearchedCompany.getName());
	}
}

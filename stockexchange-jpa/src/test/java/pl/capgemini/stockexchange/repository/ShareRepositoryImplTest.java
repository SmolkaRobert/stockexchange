package pl.capgemini.stockexchange.repository;

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

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "CommonRepositoryTest-context.xml")
public class ShareRepositoryImplTest {
	
	@Autowired
	private ShareRepositoryImpl shareRepository;
	
	private String firstListedCompanyName;
	private String secondListedCompanyName;
	private String notListedCompanyName;
	
	private Integer dateListed;
	private Integer dateNotListed;
	
	private CompanyEntity firstListedCompany;
	private CompanyEntity secondListedCompany;
	private CompanyEntity notListedCompany;
	
	@Before
	public void testSetUp(){
		firstListedCompanyName =  "MICROSOFT";
		secondListedCompanyName = "INTEL";
		notListedCompanyName = "NOTLISTEDCOMPANY";
		
		dateListed = 20011024;
		dateNotListed = 20011020;
		
		firstListedCompany = new CompanyEntity(firstListedCompanyName);
		secondListedCompany = new CompanyEntity(secondListedCompanyName);
		notListedCompany = new CompanyEntity(notListedCompanyName);
	}
	
	@Test
	public void shouldReturnEmptyListForDateNotListed(){
		//given
		Integer searchedDate = dateNotListed;
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
		
		Integer searchedDate = dateListed;
		//when
		List<ShareEntity> shares = shareRepository.findSharesByDate(searchedDate);
		//then
		Assertions.assertThat(shares).isNotNull().isNotEmpty()
			.extracting("company").extracting("name").contains(searchedCompany1.getName(), searchedCompany2.getName()).doesNotContain(notSearchedCompany.getName());
	}
}

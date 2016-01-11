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
import pl.capgemini.stockexchange.to.CompanyTo;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "CommonRepositoryTest-context.xml")
public class CompanyRepositoryTest {
	@Autowired
	private CompanyRepository companyRepository;
	
	private String firstListedCompanyName;
	private String secondListedCompanyName;
	private String notListedCompanyName;
	
	private CompanyEntity firstListedCompany;
	private CompanyEntity secondListedCompany;
	private CompanyEntity notListedCompany;
	
	@Before
	public void testSetUp(){
		firstListedCompanyName =  "MICROSOFT";
		secondListedCompanyName = "INTEL";
		notListedCompanyName = "NOTLISTEDCOMPANY";
		
		firstListedCompany = new CompanyEntity(firstListedCompanyName);
		secondListedCompany = new CompanyEntity(secondListedCompanyName);
		notListedCompany = new CompanyEntity(notListedCompanyName);
	}
	
	@Test
	public void shouldReturnEmptyCompanyList(){
		//given
		CompanyEntity searchedCompany = notListedCompany;
		//when
		List<CompanyEntity> companies = companyRepository.findCompanyByName(searchedCompany.getName());
		//then
		Assertions.assertThat(companies).isNotNull().isEmpty();;
	}
	
	@Test
	public void shouldReturnAllCompanies(){
		//given
		//when
		List<CompanyEntity> companies = companyRepository.findAll();
		//then
		Assertions.assertThat(companies).isNotNull().isNotEmpty()
			.extracting("name").contains(firstListedCompany.getName(), secondListedCompany.getName()).doesNotContain(notListedCompany.getName());
	}
	
	@Test
	public void shouldReturnCompanyWithGivenPartialName(){
		//given
		CompanyEntity searchedCompany = firstListedCompany;
		String searchedCompanyName = searchedCompany.getName();
		final String searchedName = searchedCompanyName.substring(0, 3);
		//when
		List<CompanyEntity> companies = companyRepository.findCompanyByName(searchedName);
		//then
		Assertions.assertThat(companies).isNotNull().isNotEmpty();
		for(CompanyEntity singleCompany: companies){
			Assertions.assertThat(singleCompany.getName().toLowerCase()).contains(searchedCompanyName.toLowerCase());
		}
	}
}

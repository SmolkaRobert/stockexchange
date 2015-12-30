package pl.capgemini.stockexchange.repository;

import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import pl.capgemini.stockexchange.entity.CompanyEntity;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "CommonRepositoryTest-context.xml")
public class CompanyRepositoryTest {
	@Autowired
	private CompanyRepository companyRepository;
	
	@Test
	public void shouldReturnEmptyCompanyList(){
		//given
		final String companyName = "companyNotListedOnStockExchange";
		//when
		List<CompanyEntity> companies = companyRepository.findCompanyByName(companyName);
		//then
		Assertions.assertThat(companies).isNotNull().isEmpty();;
	}
	
	@Test
	public void shouldReturnCompanyWithGivenPartialName(){
		//given
		final String companyName = "micro";
		//when
		List<CompanyEntity> companies = companyRepository.findCompanyByName(companyName);
		//then
		Assertions.assertThat(companies).isNotNull().isNotEmpty();
		for(CompanyEntity singleCompany: companies){
			Assertions.assertThat(singleCompany.getName().toLowerCase()).contains(companyName);
		}
	}
}

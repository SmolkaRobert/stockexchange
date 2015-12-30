package pl.capgemini.stockexchange.repository;

import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import pl.capgemini.stockexchange.entity.ShareEntity;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "CommonRepositoryTest-context.xml")
public class ShareRepositoryTest {
	@Autowired
	private ShareRepository shareRepository;
	private final String firstListedCompany = "MICROSOFT";
	private final String secondListedCompany = "INTEL";
	private final String notListedCompany = "NOTLISTEDCOMPANY";
	
	
	@Before
	public void testSetUp(){
		
	}
	
	@Test
	public void shouldReturnEmptyListForCompanyNotListedOnStock(){
		//given
		//when
		List<ShareEntity> shares = shareRepository.findShareByCompany(notListedCompany);
		//then
		Assertions.assertThat(shares).isNotNull().isEmpty();
	}
	
	@Test
	public void shouldFindSharesByCompanyPartialName(){
		//given
		//when
		List<ShareEntity> shares = shareRepository.findShareByCompany(firstListedCompany);
		//then
		Assertions.assertThat(shares).isNotNull().isNotEmpty();
		for(ShareEntity singleShare : shares){
			Assertions.assertThat(singleShare.getCompany().getName().toLowerCase()).contains(firstListedCompany.toLowerCase());
		}
	}
	
	@Test
	public void shouldFindAllSharesByDate(){
		//given
		final Integer date = 20011025;
		//when
		List<ShareEntity> shares = shareRepository.findShareByDay(date);
		Assertions.assertThat(shares).isNotNull().isNotEmpty()
			.extracting("company").extracting("name").contains(firstListedCompany, secondListedCompany).doesNotContain(notListedCompany);
	}
}

package pl.capgemini.stockexchange.mapper;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import pl.capgemini.stockexchange.embeddablekey.SharePrimaryKey;
import pl.capgemini.stockexchange.entity.CompanyEntity;
import pl.capgemini.stockexchange.entity.ShareEntity;
import pl.capgemini.stockexchange.to.CompanyTo;
import pl.capgemini.stockexchange.to.ShareTo;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "CommonMapperTest-context.xml")
public class ShareMapperTest {
	private static final double VALUE_IN_DOUBLE = 10.23;

	@Autowired
	private ShareMapper shareMapper;
	
	@Autowired
	private DateMapper dateMapper;
	
	private ShareTo shareTo;
	private CompanyTo companyTo;
	private ShareEntity shareEntity;
	private SharePrimaryKey sharePK;
	
	private String companyName;
	private LocalDate issueDate;
	private Date issueDateDB;
	private BigDecimal value;

	
	@Before
	public void testSetUp(){
		companyName = "Microsoft";
		issueDate = LocalDate.now();
		issueDateDB = dateMapper.convertToDatabaseColumn(issueDate);
		value = BigDecimal.valueOf(VALUE_IN_DOUBLE);
		
		companyTo = new CompanyTo(companyName); 
		shareTo = new ShareTo(companyTo, issueDate, value);
		
		sharePK = new SharePrimaryKey(companyName, issueDateDB);
		CompanyEntity companyEntity = new CompanyEntity(companyName);
		shareEntity = new ShareEntity(sharePK, companyEntity, value);
	}
	
	@Test
	public void shouldMapShareTo2ShareEntity(){
		//given
		ShareEntity testedEntity;
		//when
		testedEntity = shareMapper.map(shareTo);
		//then
		Assertions.assertThat(testedEntity).isEqualTo(shareEntity);
	}
	@Test
	public void shouldMapShareEntity2ShareTo(){
		//given
		ShareTo testedTo;
		//when
		testedTo = shareMapper.map(shareEntity);
		//then
		Assertions.assertThat(testedTo).isEqualTo(shareTo);
	}
}

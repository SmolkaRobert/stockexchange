package pl.capgemini.stockexchange.moneywallet;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "CommonMoneyWalletTest-context.xml")
public class MoneyWalletImplTest {
	
	private static final double MONEY_CHANGE_VALUE = 20.32;

	private static final double EUR_INITIAL_VALUE = 400.80;

	private static final double PLN_INITIAL_VALUE = 100.50;

	@Autowired
	private MoneyWallet moneyWallet;
	
	private Map<Currency, BigDecimal> moneyInitialCollection;
	private Map<Currency, BigDecimal> moneyChangeCollection;
	
	private Currency plnCurrency;
	private Currency eurCurrency;
	
	private BigDecimal plnMoney;
	private BigDecimal eurMoney;
	
	private BigDecimal moneyToChange;
	
	@Before
	public void testSetUp(){
		plnCurrency = Currency.getInstance(new Locale("pl","PL"));
		eurCurrency = Currency.getInstance(new Locale("de","DE"));
		
		plnMoney = BigDecimal.valueOf(PLN_INITIAL_VALUE);
		eurMoney = BigDecimal.valueOf(EUR_INITIAL_VALUE);
		moneyToChange = BigDecimal.valueOf(MONEY_CHANGE_VALUE);
		
		moneyInitialCollection = new HashMap<Currency, BigDecimal>();
		moneyChangeCollection = new HashMap<Currency, BigDecimal>();
	}
	
	@Test
	public void shouldCheckIfOnlyAddedCurrenciesAreAvailable(){
		//given
		moneyInitialCollection.clear();
		moneyWallet.clear();
		
		moneyInitialCollection.put(plnCurrency, plnMoney);
		//when
		moneyWallet.updateContent(moneyInitialCollection);
		//then
		Assertions.assertThat(moneyWallet.getContent().containsKey(plnCurrency)).isTrue();
		Assertions.assertThat(moneyWallet.getContent().containsKey(eurCurrency)).isFalse();
	}
	
	@Test
	public void shouldAddNewCurrenciesAndMoneyToWallet(){
		//given
		moneyInitialCollection.clear();
		moneyWallet.clear();
		
		moneyInitialCollection.put(plnCurrency, plnMoney);
		moneyInitialCollection.put(eurCurrency, eurMoney);
		//when
		moneyWallet.updateContent(moneyInitialCollection);
		//then
		Assertions.assertThat(moneyWallet.getContent().containsKey(plnCurrency)).isTrue();
		Assertions.assertThat(moneyWallet.getContent().get(plnCurrency)).isEqualTo(plnMoney);
		
		Assertions.assertThat(moneyWallet.getContent().containsKey(eurCurrency)).isTrue();
		Assertions.assertThat(moneyWallet.getContent().get(eurCurrency)).isEqualTo(eurMoney);
	}
	
	@Test
	public void shouldAddMoneyInWallet(){
		//given
		moneyInitialCollection.clear();
		moneyWallet.clear();
		
		BigDecimal moneyPositiveChange = moneyToChange;
		
		BigDecimal expectedMoneyPositiveChange = plnMoney.add(moneyPositiveChange);
		
		moneyInitialCollection.put(plnCurrency, plnMoney);
		moneyInitialCollection.put(eurCurrency, eurMoney);
		
		moneyChangeCollection.put(plnCurrency, moneyPositiveChange);
		
		//when
		moneyWallet.updateContent(moneyInitialCollection);
		moneyWallet.updateContent(moneyChangeCollection);
		//then
		Assertions.assertThat(moneyWallet.getContent().get(plnCurrency)).isEqualTo(expectedMoneyPositiveChange);
		
		Assertions.assertThat(moneyWallet.getContent().get(eurCurrency)).isEqualTo(eurMoney);
	}
	
	@Test
	public void shouldSubstractMoneyInWallet(){
		//given
		moneyInitialCollection.clear();
		moneyWallet.clear();
		
		BigDecimal moneyNegativeChange = moneyToChange.negate();
		
		BigDecimal expectedMoneyNegativeChange = eurMoney.add(moneyNegativeChange);
		
		moneyInitialCollection.put(plnCurrency, plnMoney);
		moneyInitialCollection.put(eurCurrency, eurMoney);
		
		moneyChangeCollection.put(eurCurrency, moneyNegativeChange);
		
		//when
		moneyWallet.updateContent(moneyInitialCollection);
		moneyWallet.updateContent(moneyChangeCollection);
		//then
		Assertions.assertThat(moneyWallet.getContent().get(plnCurrency)).isEqualTo(plnMoney);
		
		Assertions.assertThat(moneyWallet.getContent().get(eurCurrency)).isEqualTo(expectedMoneyNegativeChange);
	}
}

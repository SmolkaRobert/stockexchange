package pl.capgemini.stockexchange.moneywallet;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Currency;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import pl.capgemini.stockexchange.to.MoneyTransactionTo;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "CommonMoneyWalletTest-context.xml")
public class MoneyWalletImplTest {

	private static final double MONEY_CHANGE_VALUE = 20.32;

	private static final double EUR_INITIAL_VALUE = 400.80;
	private static final double PLN_INITIAL_VALUE = 100.50;
	private static final double GBP_INITIAL_VALUE = 20.13;

	@Autowired
	private MoneyWallet moneyWallet;

	private List<MoneyTransactionTo> moneyInitialCollection;
	private List<MoneyTransactionTo> moneyChangeCollection;

	private MoneyTransactionTo plnMoneyTransaction;
	private MoneyTransactionTo eurMoneyTransaction;
	private MoneyTransactionTo gbpMoneyTransaction;
	
	private Currency plnCurrency;
	private Currency eurCurrency;
	private Currency gbpCurrency;

	private BigDecimal plnMoney;
	private BigDecimal eurMoney;
	private BigDecimal gbpMoney;

	private BigDecimal moneyToChange;

	@Before
	public void testSetUp() {
		plnCurrency = Currency.getInstance(new Locale("pl", "PL"));
		eurCurrency = Currency.getInstance(new Locale("de", "DE"));
		gbpCurrency = Currency.getInstance(new Locale("us", "US"));

		plnMoney = BigDecimal.valueOf(PLN_INITIAL_VALUE);
		eurMoney = BigDecimal.valueOf(EUR_INITIAL_VALUE);
		gbpMoney = BigDecimal.valueOf(GBP_INITIAL_VALUE);
		
		moneyToChange = BigDecimal.valueOf(MONEY_CHANGE_VALUE);
		
		plnMoneyTransaction = new MoneyTransactionTo(plnCurrency, plnMoney);
		eurMoneyTransaction = new MoneyTransactionTo(eurCurrency, eurMoney);
		gbpMoneyTransaction = new MoneyTransactionTo(gbpCurrency, gbpMoney);

		moneyInitialCollection = new ArrayList<MoneyTransactionTo>();
		moneyInitialCollection.add(plnMoneyTransaction);
		moneyInitialCollection.add(eurMoneyTransaction);
		
		moneyChangeCollection = new ArrayList<MoneyTransactionTo>();
	}

	@Test
	public void shouldCheckIfOnlyAddedCurrenciesAreAvailable() throws NegativeMoneyForCurrencyInWalletException {
		// given
		moneyWallet.clear();

		// when
		moneyWallet.updateContent(moneyInitialCollection);
		// then
		Assertions.assertThat(moneyWallet.getAllMoney()).contains(plnMoneyTransaction);
		Assertions.assertThat(moneyWallet.getAllMoney()).contains(eurMoneyTransaction);
		Assertions.assertThat(moneyWallet.getAllMoney()).doesNotContain(gbpMoneyTransaction);
	}


	@Test
	public void shouldAddMoneyInWallet() throws NegativeMoneyForCurrencyInWalletException {
		// given
		moneyWallet.clear();
		moneyWallet.updateContent(moneyInitialCollection);
		
		Currency currencyToChange = plnCurrency;
		BigDecimal moneyPositiveChange = moneyToChange;
		BigDecimal expectedMoneyPositiveChange = plnMoney.add(moneyPositiveChange);
		
		moneyChangeCollection.clear();
		moneyChangeCollection.add(new MoneyTransactionTo(currencyToChange, moneyPositiveChange));
		
		List<Currency> currenciesToGet = new ArrayList<Currency>();
		currenciesToGet.add(currencyToChange);

		// when
		moneyWallet.updateContent(moneyChangeCollection);
		MoneyTransactionTo returnedMoneyTransactionTo = moneyWallet.getMoneyByCurrencies(currenciesToGet).get(0);
		// then
		Assertions.assertThat(returnedMoneyTransactionTo.getCurrency()).isEqualTo(currencyToChange);
		Assertions.assertThat(returnedMoneyTransactionTo.getAmmount()).isEqualTo(expectedMoneyPositiveChange);
	}

	@Test
	public void shouldSubstractMoneyInWallet() throws NegativeMoneyForCurrencyInWalletException {
		// given
		moneyWallet.clear();
		moneyWallet.updateContent(moneyInitialCollection);

		Currency currencyToChange = eurCurrency;
		BigDecimal moneyNegativeChange = moneyToChange.negate();
		BigDecimal expectedMoneyNegativeChange = eurMoney.add(moneyNegativeChange);

		moneyChangeCollection.clear();
		moneyChangeCollection.add(new MoneyTransactionTo(currencyToChange, moneyNegativeChange));

		List<Currency> currenciesToGet = new ArrayList<Currency>();
		currenciesToGet.add(currencyToChange);
		// when
		moneyWallet.updateContent(moneyChangeCollection);
		MoneyTransactionTo returnedMoneyTransactionTo = moneyWallet.getMoneyByCurrencies(currenciesToGet).get(0);
		// then
		Assertions.assertThat(returnedMoneyTransactionTo.getCurrency()).isEqualTo(currencyToChange);
		Assertions.assertThat(returnedMoneyTransactionTo.getAmmount()).isEqualTo(expectedMoneyNegativeChange);
	}

	@Test
	public void shouldGetOnlyChosenCurrencyFromWallet() throws NegativeMoneyForCurrencyInWalletException {
		// given
		moneyWallet.clear();
		moneyWallet.updateContent(moneyInitialCollection);

		Currency currencyNotPresent = gbpCurrency;
		
		List<Currency> currenciesToGet = new ArrayList<Currency>();
		currenciesToGet.add(plnCurrency);
		currenciesToGet.add(eurCurrency);

		// when
		List<MoneyTransactionTo> returnedCurrencies = moneyWallet.getMoneyByCurrencies(currenciesToGet);
		// then
		for(MoneyTransactionTo singleMoneyTo : returnedCurrencies){
			Assertions.assertThat(currenciesToGet).contains(singleMoneyTo.getCurrency());
		}
	}
	
	@Test
	public void shouldReturnEmptyWhenAskedAboutCurrencyNotPresentInWallet() throws NegativeMoneyForCurrencyInWalletException {
		// given
		moneyWallet.clear();
		moneyWallet.updateContent(moneyInitialCollection);
		
		Currency currencyNotPresent = gbpCurrency;
		
		List<Currency> currenciesToGet = new ArrayList<Currency>();
		currenciesToGet.add(currencyNotPresent);
		
		// when
		List<MoneyTransactionTo> returnedCurrencies = moneyWallet.getMoneyByCurrencies(currenciesToGet);
		// then
		Assertions.assertThat(returnedCurrencies).isNotNull().isEmpty();
	}

}

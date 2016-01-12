import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Currency;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;

import pl.capgemini.stockexchange.to.CompanyTo;

public class TemporaryTest {

	
	//TODO RSmolka delete before commit
	public static void main(String[] args) {
//		Currency gerCurr = Currency.getInstance(new Locale("pl","PL"));
//		Currency japCurr = Currency.getInstance(new Locale("ja","JP"));
//		HashMap<Currency, BigDecimal> waluty = new HashMap<Currency, BigDecimal>();
//		waluty.put(japCurr, BigDecimal.valueOf(100.25));
//		waluty.put(gerCurr, BigDecimal.valueOf(25.34));
//		
//		System.out.println(waluty.get(gerCurr));
//		System.out.println(waluty.keySet());
//		
//		System.out.println(waluty.get(japCurr));
		
		BigDecimal first = BigDecimal.valueOf(10.25);
		System.out.println(first);
		
		BigDecimal second = first.negate();
		System.out.println(first);
		System.out.println(second);
	}

}

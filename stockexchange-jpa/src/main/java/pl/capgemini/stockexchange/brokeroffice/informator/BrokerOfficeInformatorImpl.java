package pl.capgemini.stockexchange.brokeroffice.informator;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Map;

import pl.capgemini.stockexchange.to.CompanyTo;

public class BrokerOfficeInformatorImpl {
	public Map<CompanyTo, BigDecimal> getSharesForDate(LocalDate date){
		//TODO RSmolka add getting from stockmarketimpl
		return null;
	}
}

package pl.capgemini.stockexchange.brokeroffice.informator;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.capgemini.stockexchange.stockmarket.DateFromTheFutureForStockExchangeException;
import pl.capgemini.stockexchange.stockmarket.StockMarket;
import pl.capgemini.stockexchange.stockmarket.StockMarketImpl;
import pl.capgemini.stockexchange.to.CompanyTo;
import pl.capgemini.stockexchange.to.ShareTo;

@Service
public class BrokerOfficeInformator {
	@Autowired
	StockMarket stockMarket;
	
	public List<ShareTo> getSharesForDate(LocalDate date) throws DateFromTheFutureForStockExchangeException{
		return stockMarket.findSharesByDate(date);
	}
}

package pl.capgemini.stockexchange.stockmarket;

import java.time.LocalDate;
import java.util.List;

import pl.capgemini.stockexchange.to.CompanyTo;
import pl.capgemini.stockexchange.to.ShareTo;

public interface StockMarket {
	public List<ShareTo> findSharesByDate(LocalDate date) throws StockMarketException;
	
	public List<CompanyTo> findAllCompanies();
}

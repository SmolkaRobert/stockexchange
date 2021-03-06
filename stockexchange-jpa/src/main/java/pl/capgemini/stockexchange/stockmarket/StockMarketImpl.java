package pl.capgemini.stockexchange.stockmarket;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.capgemini.stockexchange.calendar.StockCalendar;
import pl.capgemini.stockexchange.mapper.CompanyMapper;
import pl.capgemini.stockexchange.mapper.DateMapper;
import pl.capgemini.stockexchange.mapper.ShareMapper;
import pl.capgemini.stockexchange.repository.CompanyRepository;
import pl.capgemini.stockexchange.repository.ShareRepositoryImpl;
import pl.capgemini.stockexchange.to.CompanyTo;
import pl.capgemini.stockexchange.to.ShareTo;

@Service
public class StockMarketImpl implements StockMarket {
	@Autowired
	private ShareRepositoryImpl shareRepository;
	@Autowired
	private CompanyRepository companyRepository;
	@Autowired
	private DateMapper dateMapper;
	@Autowired
	private ShareMapper shareMapper;
	@Autowired
	private CompanyMapper companyMapper;
	@Autowired
	private StockCalendar calendar;

	public List<ShareTo> findSharesByDate(LocalDate issueDate) throws DateFromTheFutureForStockExchangeException {
		if(issueDate.isAfter(calendar.getCurrentDate())) {
			throw new DateFromTheFutureForStockExchangeException();
		}
		return shareMapper.mapList2To(shareRepository.findSharesByDate(dateMapper.convertToDatabaseColumn(issueDate)));
	}

	public List<CompanyTo> findAllCompanies() {
		return companyMapper.mapList2To(companyRepository.findAll());
	}
}

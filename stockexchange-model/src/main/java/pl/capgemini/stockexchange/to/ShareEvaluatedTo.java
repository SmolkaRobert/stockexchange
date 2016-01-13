package pl.capgemini.stockexchange.to;

import java.math.BigDecimal;

public class ShareEvaluatedTo {
	private CompanyTo company;
	private Integer ammount;
	private BigDecimal value;
	
	
	public ShareEvaluatedTo(CompanyTo company, Integer ammount, BigDecimal value) {
		super();
		this.company = company;
		this.ammount = ammount;
		this.value = value;
	}
	public CompanyTo getCompany() {
		return company;
	}
	public Integer getAmmount() {
		return ammount;
	}
	public BigDecimal getValue() {
		return value;
	}
	
}

package pl.capgemini.stockexchange.to;

import java.time.LocalDate;

public class ShareTo{
	private CompanyTo company;
	private LocalDate issueDate;
	private Float value;
	
	public ShareTo() {
	}

	public ShareTo(CompanyTo companyName, LocalDate issueDate, Float value) {
		this.company = companyName;
		this.issueDate = issueDate;
		this.value = value;
	}

	public CompanyTo getCompany() {
		return company;
	}

	public void setCompany(CompanyTo company) {
		this.company = company;
	}

	public LocalDate getIssueDate() {
		return issueDate;
	}

	public void setIssueDate(LocalDate issueDate) {
		this.issueDate = issueDate;
	}

	public Float getValue() {
		return value;
	}

	public void setValue(Float value) {
		this.value = value;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((company == null) ? 0 : company.hashCode());
		result = prime * result + ((issueDate == null) ? 0 : issueDate.hashCode());
		result = prime * result + ((value == null) ? 0 : value.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ShareTo other = (ShareTo) obj;
		if (company == null) {
			if (other.company != null)
				return false;
		} else if (!company.equals(other.company))
			return false;
		if (issueDate == null) {
			if (other.issueDate != null)
				return false;
		} else if (!issueDate.equals(other.issueDate))
			return false;
		if (value == null) {
			if (other.value != null)
				return false;
		} else if (!value.equals(other.value))
			return false;
		return true;
	}
	
	
}

package pl.capgemini.stockexchange.embeddablekey;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Embeddable
public class SharePrimaryKey implements Serializable {
	
	private String company;
	
	@Column(name = "issue_date", length = 8, nullable = false)
	@Temporal(TemporalType.DATE)
	private Date issueDate;
	
	
	public SharePrimaryKey() {
	}
	
	public SharePrimaryKey(String company, Date issueDate) {
		this.company = company;
		this.issueDate = issueDate;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public Date getIssueDate() {
		return issueDate;
	}

	public void setIssueDate(Date issueDate) {
		this.issueDate = issueDate;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((company == null) ? 0 : company.hashCode());
		result = prime * result + ((issueDate == null) ? 0 : issueDate.hashCode());
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
		SharePrimaryKey other = (SharePrimaryKey) obj;
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
		return true;
	}
	
	
}

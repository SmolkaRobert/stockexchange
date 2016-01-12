package pl.capgemini.stockexchange.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

import pl.capgemini.stockexchange.embeddablekey.SharePrimaryKey;

@Entity
@Table(name = "SHARES")
public class ShareEntity implements Serializable {
	@EmbeddedId
	private SharePrimaryKey sharePK;
	
	@ManyToOne
	@JoinColumn(name="company_name")
	@MapsId("company")
	private CompanyEntity company;
	
	@Column(name = "share_value", nullable = false)
	private BigDecimal value;

	
	public ShareEntity() {
		
	}
	
	public ShareEntity(SharePrimaryKey sharePK, CompanyEntity company, BigDecimal value) {
		this.sharePK = sharePK;
		this.company = company;
		this.value = value;
	}
	
	public SharePrimaryKey getSharePK() {
		return sharePK;
	}

	public void setSharePK(SharePrimaryKey sharePK) {
		this.sharePK = sharePK;
	}

	public CompanyEntity getCompany() {
		return company;
	}

	public void setCompany(CompanyEntity company) {
		this.company = company;
	}

	public BigDecimal getValue() {
		return value;
	}

	public void setValue(BigDecimal value) {
		this.value = value;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((company == null) ? 0 : company.hashCode());
		result = prime * result + ((sharePK == null) ? 0 : sharePK.hashCode());
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
		ShareEntity other = (ShareEntity) obj;
		if (company == null) {
			if (other.company != null)
				return false;
		} else if (!company.equals(other.company))
			return false;
		if (sharePK == null) {
			if (other.sharePK != null)
				return false;
		} else if (!sharePK.equals(other.sharePK))
			return false;
		if (value == null) {
			if (other.value != null)
				return false;
		} else if (!value.equals(other.value))
			return false;
		return true;
	}
}

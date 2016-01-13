package pl.capgemini.stockexchange.to;

public class ShareOfferTo {
	private CompanyTo company;
	private Integer ammount;
	
	public ShareOfferTo(CompanyTo company, Integer ammount) {
		this.company = company;
		this.ammount = ammount;
	}
	
	public CompanyTo getCompany() {
		return company;
	}
	public Integer getAmmount() {
		return ammount;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((ammount == null) ? 0 : ammount.hashCode());
		result = prime * result + ((company == null) ? 0 : company.hashCode());
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
		ShareOfferTo other = (ShareOfferTo) obj;
		if (ammount == null) {
			if (other.ammount != null)
				return false;
		} else if (!ammount.equals(other.ammount))
			return false;
		if (company == null) {
			if (other.company != null)
				return false;
		} else if (!company.equals(other.company))
			return false;
		return true;
	}
}

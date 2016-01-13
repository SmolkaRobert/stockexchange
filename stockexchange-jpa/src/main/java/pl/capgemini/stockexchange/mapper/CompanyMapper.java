package pl.capgemini.stockexchange.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import pl.capgemini.stockexchange.entity.CompanyEntity;
import pl.capgemini.stockexchange.entity.ShareEntity;
import pl.capgemini.stockexchange.to.CompanyTo;
import pl.capgemini.stockexchange.to.ShareTo;

@Service
public class CompanyMapper {

	//TODO RSmolka add CompanyMapper tests
	public CompanyTo map(CompanyEntity companyEntity) {
		if(companyEntity != null){
			return new CompanyTo(companyEntity.getName());
		}
		return null;
	}
	
	public List<CompanyTo> mapList2To(List<CompanyEntity> companyEntities) {
		List<CompanyTo> companiesList = new ArrayList<CompanyTo>();
		for(CompanyEntity singleCompanyEntity : companyEntities){
			companiesList.add(map(singleCompanyEntity));
		}
		return companiesList;
    }
}

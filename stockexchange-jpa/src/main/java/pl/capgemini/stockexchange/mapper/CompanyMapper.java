package pl.capgemini.stockexchange.mapper;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import pl.capgemini.stockexchange.embeddablekey.SharePrimaryKey;
import pl.capgemini.stockexchange.entity.CompanyEntity;
import pl.capgemini.stockexchange.entity.ShareEntity;
import pl.capgemini.stockexchange.to.CompanyTo;
import pl.capgemini.stockexchange.to.ShareTo;

@Service
public class CompanyMapper {

	public CompanyTo map(CompanyEntity companyEntity) {
		if(companyEntity != null){
			return new CompanyTo(companyEntity.getName());
		}
		return null;
	}
	
}

package pl.capgemini.stockexchange.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import pl.capgemini.stockexchange.entity.CompanyEntity;


public interface CompanyRepository extends JpaRepository<CompanyEntity, String> {
	
  @Query("FROM CompanyEntity company WHERE upper(company.name) like concat('%', upper(:companyName), '%')")
  public List<CompanyEntity> findCompanyByName(@Param("companyName") String name);
}

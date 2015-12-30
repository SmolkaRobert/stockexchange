package pl.capgemini.stockexchange.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import pl.capgemini.stockexchange.embeddablekey.SharePrimaryKey;
import pl.capgemini.stockexchange.entity.ShareEntity;


public interface ShareRepository extends JpaRepository<ShareEntity, SharePrimaryKey> {
	
  @Query("SELECT share FROM ShareEntity share WHERE UPPER(share.company.name) LIKE CONCAT('%', UPPER(:companyName), '%')")
  public List<ShareEntity> findShareByCompany(@Param("companyName") String name);
  
  @Query("SELECT share FROM ShareEntity share WHERE share.sharePK.issueDate = :singleDate")
  public List<ShareEntity> findShareByDay(@Param("singleDate") Integer date);
}

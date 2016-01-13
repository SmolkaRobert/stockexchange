package pl.capgemini.stockexchange.repository;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import pl.capgemini.stockexchange.entity.ShareEntity;

@Repository
public class ShareRepositoryImpl{

	@PersistenceContext
	private EntityManager entityManager;

	@Transactional(readOnly = true)
	public List<ShareEntity> findSharesByDate(Date date) {
		Query query = entityManager.createQuery("FROM ShareEntity share WHERE share.sharePK.issueDate = :issueDate");
		query.setParameter("issueDate", date);
		return query.getResultList();
	}
	
	@Transactional(readOnly = true)
	public Date findLastDate() {
		Query query = entityManager.createQuery("SELECT MAX(share.sharePK.issueDate) FROM ShareEntity share");
		return (Date) query.getSingleResult();
	}

	@Transactional(readOnly = true)
	public Date findEarliestDate() {
		Query query = entityManager.createQuery("SELECT MIN(share.sharePK.issueDate) FROM ShareEntity share");
		return (Date) query.getSingleResult();
	}
}

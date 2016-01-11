package pl.capgemini.stockexchange.repository;

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
	public List<ShareEntity> findSharesByDate(Integer date) {
		Query query = entityManager.createQuery("FROM ShareEntity share WHERE share.sharePK.issueDate = :issueDate");
		query.setParameter("issueDate", date);
		return query.getResultList();
	}
}

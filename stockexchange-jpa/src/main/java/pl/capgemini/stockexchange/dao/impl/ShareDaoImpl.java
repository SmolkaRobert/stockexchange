package pl.capgemini.stockexchange.dao.impl;

import javax.persistence.TypedQuery;

import pl.capgemini.stockexchange.dao.ShareDao;
import pl.capgemini.stockexchange.embeddablekey.SharePrimaryKey;
import pl.capgemini.stockexchange.entity.ShareEntity;

import java.util.List;

public class ShareDaoImpl extends AbstractDao<ShareEntity, SharePrimaryKey> implements ShareDao {

    @Override
    public List<ShareEntity> findShareByCompany(String companyName) {
        TypedQuery<ShareEntity> query = entityManager.createQuery(
                "SELECT share FROM ShareEntity share JOIN share.companyAndDate company where upper(company.name) like concat('%', upper(:companyName), '%')", ShareEntity.class);
        query.setParameter("companyName", companyName);
        return query.getResultList();
    }
}

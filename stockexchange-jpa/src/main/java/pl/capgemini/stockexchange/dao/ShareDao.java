package pl.capgemini.stockexchange.dao;

import java.util.List;

import pl.capgemini.stockexchange.embeddablekey.SharePrimaryKey;
import pl.capgemini.stockexchange.entity.ShareEntity;

public interface ShareDao extends Dao<ShareEntity, SharePrimaryKey> {

    List<ShareEntity> findShareByCompany(String companyName);
}

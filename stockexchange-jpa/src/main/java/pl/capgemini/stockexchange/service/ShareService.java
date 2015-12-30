package pl.capgemini.stockexchange.service;

import java.util.List;

import pl.capgemini.stockexchange.to.ShareTo;

public interface ShareService {
	List<ShareTo> findSharesByCompany();
	List<ShareTo> findSharesByDate();
}

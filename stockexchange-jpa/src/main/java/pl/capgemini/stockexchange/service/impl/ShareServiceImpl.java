package pl.capgemini.stockexchange.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pl.capgemini.stockexchange.repository.ShareRepository;
import pl.capgemini.stockexchange.service.ShareService;
import pl.capgemini.stockexchange.to.ShareTo;

@Service
@Transactional(readOnly = true)
public class ShareServiceImpl implements ShareService {
	
	@Autowired
	ShareRepository shareRepository;

	@Override
	public List<ShareTo> findSharesByCompany() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ShareTo> findSharesByDate() {
		// TODO Auto-generated method stub
		return null;
	}

}

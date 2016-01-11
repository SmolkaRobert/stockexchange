package pl.capgemini.stockexchange.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.capgemini.stockexchange.embeddablekey.SharePrimaryKey;
import pl.capgemini.stockexchange.entity.CompanyEntity;
import pl.capgemini.stockexchange.entity.ShareEntity;
import pl.capgemini.stockexchange.to.ShareTo;

@Service
public class ShareMapper {
	
	@Autowired
	private DateMapper dateMapper;
	@Autowired
	private CompanyMapper companyMapper;
	
    public ShareTo map(ShareEntity shareEntity) {
        if (shareEntity != null) {
            return new ShareTo(companyMapper.map(shareEntity.getCompany()), dateMapper.convertToEntityAttribute(shareEntity.getSharePK().getIssueDate()), shareEntity.getValue());
        }
        return null;
    }

	public ShareEntity map(ShareTo shareTo) {
        if (shareTo != null) {
            String comapnyName = shareTo.getCompany().getName();
			return new ShareEntity(new SharePrimaryKey(comapnyName, dateMapper.convertToDatabaseColumn(shareTo.getIssueDate())), new CompanyEntity(comapnyName), shareTo.getValue());
        }
        return null;
    }

	public List<ShareTo> mapList2To(List<ShareEntity> shareEntities) {
		List<ShareTo> sharesList = new ArrayList<ShareTo>();
		for(ShareEntity singleShareEntity : shareEntities){
			sharesList.add(map(singleShareEntity));
		}
		return sharesList;
    }
//
//    public List<ShareEntity> mapList2Entity(List<ShareTo> shareEntities) {
//        return shareEntities.stream().map(ShareMapper::map).collect(Collectors.toList());
//    }
}
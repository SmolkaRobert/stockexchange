package pl.capgemini.stockexchange.dataloader;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import pl.capgemini.stockexchange.mapper.DateMapper;

@Component
public class CsvStockFileDataParser {
	
	@Autowired
	private DateMapper dateMapper;

	private static final String COMMA = ",";
	private static final int NUMBER_OF_ELEMENTS = 3;
	private static final int COMPANY_NAME = 1;
	private static final int ISSUE_DATE = 2;
	private static final int VALUE = 3;

	public void prepareQuery(String oneLine) {
		String[] singleShareData = oneLine.split(COMMA);
		
		if (isDataCorrect(singleShareData)) {
			String companyName = singleShareData[COMPANY_NAME];
			Integer issueDateInteger = Integer.getInteger(singleShareData[ISSUE_DATE]);
			Date issueDate = dateMapper.convertIntegerToDate(issueDateInteger);
			BigDecimal value = new BigDecimal(singleShareData[VALUE]);
			
			//TODO RSmolka send data into DB
		}
	}

	private boolean isDataCorrect(String[] singleShareData) {
		boolean dataCorrect = false;
		
		if(NUMBER_OF_ELEMENTS == singleShareData.length && isStringAnInteger(singleShareData[ISSUE_DATE]) && isStringABigDecimal(singleShareData[VALUE])){
			dataCorrect = true;
		}
		
		return dataCorrect;
	}

	private boolean isStringABigDecimal(String numberString) {
		boolean isBigDecimal = false;
		if(numberString.matches("^[0-9]*\\.?[0-9]*$")){
			isBigDecimal = true;
		}
		return isBigDecimal;
	}

	private boolean isStringAnInteger(String numberString) {
		boolean isInteger = false;
		if(numberString.matches("[0-9]+")){
			isInteger = true;
		}
		return isInteger;
	}
	
	
}

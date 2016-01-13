package pl.capgemini.stockexchange.brokeroffice.manager;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Service;

import pl.capgemini.stockexchange.to.ShareEvaluatedTo;
import pl.capgemini.stockexchange.to.ShareOfferTo;

@Service
public class BrokerOfficeManager {
	
	List<ShareEvaluatedTo> currentEvaluation;
	
	public List<ShareEvaluatedTo> evaluateTransaction(List<ShareOfferTo> transactionShareList) {
		//update currentEvaluation
		return null;
	}

	public BigDecimal calculateEvaluatedCost() {
		BigDecimal overallCost = BigDecimal.valueOf(0.00);
		
		for(ShareEvaluatedTo singleShareEvaluation : currentEvaluation){
			BigDecimal singleShareValue = singleShareEvaluation.getValue().multiply(BigDecimal.valueOf(singleShareEvaluation.getAmmount()));
			overallCost.add(singleShareValue);
		}
		return calculateCommission(overallCost);
	}

	//TODO RSmolka prepare commision calculation
	private BigDecimal calculateCommission(BigDecimal overallCost) {
		return (overallCost);
	}
}

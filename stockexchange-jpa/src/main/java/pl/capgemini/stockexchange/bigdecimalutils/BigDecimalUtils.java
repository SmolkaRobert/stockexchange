package pl.capgemini.stockexchange.bigdecimalutils;

import java.math.BigDecimal;

public class BigDecimalUtils {
	public static boolean isGreaterOrEqual(BigDecimal number, BigDecimal numberToCompare){
		return number.floatValue() >= numberToCompare.floatValue();
	}
	
	public static boolean isLesserOrEqual(BigDecimal number, BigDecimal numberToCompare){
		return number.floatValue() <= numberToCompare.floatValue();
	}
}

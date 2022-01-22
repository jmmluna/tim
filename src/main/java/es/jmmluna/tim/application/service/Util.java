package es.jmmluna.tim.application.service;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Util {

	public static Double to2Decimal(Double value) {
		if (value == null)
			return value;
		else {
			BigDecimal bd = new BigDecimal(value).setScale(2, RoundingMode.HALF_UP);
			return bd.doubleValue();
		}
	}

}

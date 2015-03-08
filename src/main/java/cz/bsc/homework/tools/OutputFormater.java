package cz.bsc.homework.tools;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import cz.bsc.homework.domain.Payment;
import cz.bsc.homework.service.ExchangeRatesService;

/**
 * Format payments to table
 * 
 * @author dolezelt
 *
 */
public class OutputFormater {

	private static final String USD_CURRENCY = "USD";
	private int maxLen = 0;
	private ExchangeRatesService exchangeRatesService;

	public OutputFormater(ExchangeRatesService exchangeRatesService) {
		this.exchangeRatesService = exchangeRatesService;
	}

	/**
	 * simple string format 
	 * @param list
	 * @return
	 */
	public String format(List<Payment> list) {
		if (list == null || list.size() == 0)
			return "No data found!";
		Payment usd = new Payment(USD_CURRENCY);
		StringBuilder sb = new StringBuilder();

		for (Payment p : list) {
			if (p.getCurrency().equalsIgnoreCase(USD_CURRENCY))
				usd.addAmount(p);
			String amountToString = p.getAmount().toString();
			maxLen = Math.max(amountToString.length(), maxLen);

		}

		sb.append(format(usd));
		for (Payment p : list) {
			if (!p.getCurrency().equalsIgnoreCase(USD_CURRENCY)) {
				sb.append(format(p));
			}
		}

		return sb.toString();
	}

	private String format(Payment p) {
		Double rate = null;
		if (exchangeRatesService != null) {
			rate = exchangeRatesService.getRate(p);
		}
		if (p.getAmount().doubleValue() != 0d) {
			String stringAmount = p
					.getAmount().toString();
			return String.format(p.getCurrency() + " %" +maxLen + "s %s \n", stringAmount, rate == null
					|| p.getCurrency().equals("USD") ? "" : "(USD "
					+ p.getAmount().multiply(new BigDecimal(rate)).setScale(2,RoundingMode.CEILING) + ")");
		}
		return "";
	}

}

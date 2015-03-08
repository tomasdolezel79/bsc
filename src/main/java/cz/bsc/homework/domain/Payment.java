package cz.bsc.homework.domain;

import java.math.BigDecimal;

/**
 * Payment domain object / entity
 * 
 * @author dolezelt
 *
 */
public class Payment {
	private String currency;
	private BigDecimal amount;
 

	public Payment(String currency) {
		this(currency, 0);
	}

	public Payment(String currency, double amount) {
		this(currency, new BigDecimal(amount));
	}

	public Payment(String currency, BigDecimal amount) {
		this.currency = currency;
		this.amount = amount;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = new BigDecimal(amount);
	}

	public void addAmount(Payment p) {
		amount = this.amount.add(p.getAmount());

	}

	public String toString() {
		return String.format("%s %s", currency, amount);
	}
 
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

}

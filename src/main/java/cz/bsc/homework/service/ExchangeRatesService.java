package cz.bsc.homework.service;

import cz.bsc.homework.domain.Payment;

/**
 * Service layer for exchange rates.
 * 
 * @author dolezelt
 *
 */
public interface ExchangeRatesService {

	/**
	 * return current rate 
	 * @param payment
	 * @return null if currency not found
	 */
	public Double getRate(Payment payment);

}

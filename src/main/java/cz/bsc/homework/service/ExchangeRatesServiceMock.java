package cz.bsc.homework.service;

import java.util.Random;

import cz.bsc.homework.domain.Payment;

/**
 * Mock returns radom number as exchange rate
 * @author dolezelt
 *
 */
public class ExchangeRatesServiceMock implements ExchangeRatesService{

	
	@Override
	public Double getRate(Payment payment) {
		 
		return new Double( new Random().nextInt(20));
	}

}

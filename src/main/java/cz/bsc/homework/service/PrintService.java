package cz.bsc.homework.service;

import cz.bsc.homework.console.Console;
import cz.bsc.homework.tools.OutputFormater;

/**
 * Prints a table to console
 * 
 * @author dolezelt
 *
 */
public class PrintService implements Runnable {
 
	private Console console = Console.getInstance();
	private PaymentService paymentService;
	private ExchangeRatesService exchangeRatesService;
	
	public ExchangeRatesService getExchangeRatesService() {
		return exchangeRatesService;
	}

	public void setExchangeRatesService(ExchangeRatesService exchangeRatesService) {
		this.exchangeRatesService = exchangeRatesService;
	}

	public PaymentService getPaymentService() {
		return paymentService;
	}

	public void setPaymentService(PaymentService paymentService) {
		this.paymentService = paymentService;
	}

	@Override
	public void run() {
		console.print(new OutputFormater(exchangeRatesService).format(paymentService.list()));
	}

}

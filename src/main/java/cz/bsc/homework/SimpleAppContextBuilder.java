package cz.bsc.homework;

import cz.bsc.homework.dao.SimpleMemoryPaymentDao;
import cz.bsc.homework.service.CNBExchangeRatesService;
import cz.bsc.homework.service.DefaultPaymentService;
import cz.bsc.homework.service.ExchangeRatesService;
import cz.bsc.homework.service.ExchangeRatesServiceMock;
import cz.bsc.homework.service.PrintService;

/**
 * Build app context
 * (this usually doing Spring)
 * 
 * @author dolezelt
 *
 */
public class SimpleAppContextBuilder {
	private SimpleMemoryPaymentDao dao;
	private ExchangeRatesService exchangeRatesService;
	private DefaultPaymentService service;
	private CommandDispatcher dispatcher;
	private PrintService printService ;

	public SimpleAppContextBuilder() {
		dao = new SimpleMemoryPaymentDao();
		service = new DefaultPaymentService();
		dispatcher = new CommandDispatcher();
		dispatcher.setPaymentService(service);
		printService = new PrintService();
		printService.setPaymentService(service);
		dispatcher.setPrintService(printService);
		exchangeRatesService=new CNBExchangeRatesService();
	//	exchangeRatesService=new ExchangeRatesServiceMock();
		printService.setExchangeRatesService(exchangeRatesService);
		service.setDao(dao);
	}

	
	
	
	public PrintService getPrintService() {
		return printService;
	}




	public void setPrintService(PrintService printService) {
		this.printService = printService;
	}




	public ExchangeRatesService getExchangeRatesService() {
		return exchangeRatesService;
	}

	public void setExchangeRatesService(ExchangeRatesService exchangeRatesService) {
		this.exchangeRatesService = exchangeRatesService;
	}

	public DefaultPaymentService getService() {
		return service;
	}

	public void setService(DefaultPaymentService service) {
		this.service = service;
	}

	public CommandDispatcher getDispatcher() {
		return dispatcher;
	}

	public void setDispatcher(CommandDispatcher dispatcher) {
		this.dispatcher = dispatcher;
	}

	
	
}

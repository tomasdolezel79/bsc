package cz.bsc.homework.service;

import java.util.List;

import cz.bsc.homework.console.Console;
import cz.bsc.homework.dao.PaymentDao;
import cz.bsc.homework.domain.Payment;
/**
 * Simple interface implementation
 * @author dolezelt
 *
 */
public class DefaultPaymentService implements PaymentService {
	private Console console = Console.getInstance();

	private PaymentDao dao;
	
	public Console getConsole() {
		return console;
	}

	public void setConsole(Console console) {
		this.console = console;
	}

	public PaymentDao getDao() {
		return dao;
	}

	public void setDao(PaymentDao dao) {
		this.dao = dao;
	}

	@Override
	public List<Payment> list() {
		return dao.getList();
	}

	@Override
	public void addPayment(Payment payment) {
		dao.store(payment);
		console.print("Payment stored " + payment);
	}

}

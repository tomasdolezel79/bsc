package cz.bsc.homework.service;

import java.util.List;

import cz.bsc.homework.domain.Payment;
/**
 * Service layer of this simple applicatio
 * 
 * @author dolezelt
 *
 */
public interface PaymentService {

	public List<Payment> list();

	public void addPayment(Payment payment);

}

package cz.bsc.homework.dao;

import java.util.List;

import cz.bsc.homework.domain.Payment;

/**
 * Simple dao layer interface.
 * 
 * @author dolezelt
 *
 */
public interface PaymentDao {

	/**
	 * Save payment somewhere
	 * @param payment
	 */
	public void store(Payment payment);

	/**
	 * A list of what we want
	 * @return
	 */
	public List<Payment> getList();

}

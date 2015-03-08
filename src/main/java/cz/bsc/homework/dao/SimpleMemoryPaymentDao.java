package cz.bsc.homework.dao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cz.bsc.homework.domain.Payment;
import cz.bsc.homework.tools.AlphabeticalComparator;

/**
 * Simple implementation of dao layer.
 * 
 * @author dolezelt
 *
 */
public class SimpleMemoryPaymentDao implements PaymentDao {
	private Map<String, Payment> paymentSumByCurrency = new HashMap<String, Payment>();

	/**
	 * Save payment
	 */
	@Override
	public void store(Payment payment) {

		if (payment != null)
			synchronized (paymentSumByCurrency) {
				if (paymentSumByCurrency.get(payment.getCurrency()) == null) {
					paymentSumByCurrency.put(
							payment.getCurrency(),
							new Payment(payment.getCurrency(), payment
									.getAmount()));
				} else {
					paymentSumByCurrency.get(payment.getCurrency()).addAmount(
							payment);
				}
			}
	}

	/**
	 * List of payments, sorted in the default order.
	 */
	@Override
	public List<Payment> getList() {
		ArrayList<Payment> list = null;
		synchronized (paymentSumByCurrency) {
			list = new ArrayList<Payment>(paymentSumByCurrency.values());
		}
		Collections.sort(list, new AlphabeticalComparator());
		return list;
	}

}

package cz.bsc.homework.tools;

import java.util.Comparator;

import cz.bsc.homework.domain.Payment;

/**
 * Compares Payments by currency by alphabet
 * @author dolezelt
 *
 */
public class AlphabeticalComparator implements Comparator<Payment> {


	@Override
	public int compare(Payment o1, Payment o2) {
 
		return o1.getCurrency().compareTo(o2.getCurrency());
	}

}

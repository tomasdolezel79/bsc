package cz.bsc.homework.domain;

import static org.junit.Assert.*;
import org.junit.Test;

import cz.bsc.homework.domain.Payment;

public class PaymentTest {

	@Test
	public void test() {
		Payment p = new Payment("USD", 100);
		p.addAmount(p);
		assertEquals(200d, p.getAmount().doubleValue(), 0d);
	}

	@Test
	public void testToString() {
		Payment p = new Payment("USD", 100);
	 
		assertEquals("USD 100", p.toString());
	}
}

package cz.bsc.homework.lex;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.io.InputStream;

import org.junit.Test;

import cz.bsc.homework.domain.Payment;

public class PaymentParserTest {

	@Test
	public void testOnePayment() throws IOException {
		PaymentParser parser = new PaymentParser("USD100");
		parser.hasNext();
		Payment p = parser.next();

		assertEquals(100d, p.getAmount().doubleValue(), 0d);
		assertEquals("USD", p.getCurrency());

	}

	@Test
	public void testTwoPayments() throws IOException {
		PaymentParser parser = new PaymentParser("USD100USD100");
		parser.hasNext();
		Payment p = parser.next();

		assertEquals(100d, p.getAmount().doubleValue(), 0d);
		assertEquals("USD", p.getCurrency());

		p = parser.next();

		assertEquals(100d, p.getAmount().doubleValue(), 0d);
		assertEquals("USD", p.getCurrency());

	}

	@Test
	public void testTwoPaymentsSpace() throws IOException {
		PaymentParser parser = new PaymentParser("USD100 USD200 2000");
		parser.hasNext();
		Payment p = parser.next();

		assertEquals(100d, p.getAmount().doubleValue(), 0d);
		assertEquals("USD", p.getCurrency());
		parser.hasNext();
		p = parser.next();

		assertEquals(200d, p.getAmount().doubleValue(), 0d);
		assertEquals("USD", p.getCurrency());

	}

	@Test(expected = LexException.class)
	public void testTwoPaymentsAndError() throws IOException {
		PaymentParser parser = new PaymentParser("USD100 USD200 US");
		parser.hasNext();
		Payment p = parser.next();

		assertEquals(100d, p.getAmount().doubleValue(), 0d);
		assertEquals("USD", p.getCurrency());
		parser.hasNext();
		p = parser.next();

		assertEquals(200d, p.getAmount().doubleValue(), 0d);
		assertEquals("USD", p.getCurrency());
		try {
			assertFalse(parser.hasNext());
		} catch (LexException e) {
			System.out.println("Exception at position " + e.getPosition());
			throw e;
		}
	}

	@Test 
	public void testParseStreamFromFile() throws IOException {
		try (InputStream stream = this.getClass().getClassLoader()
				.getResourceAsStream("testData.txt")) {

			PaymentParser parser = new PaymentParser(stream);
			int i = 0;
			while (parser.hasNext()) {
				Payment p = parser.next();
				i++;
				assertNotNull(p.getAmount());
				assertNotNull(p.getCurrency());
			}
			assertTrue(i > 0);
		}

	}
}

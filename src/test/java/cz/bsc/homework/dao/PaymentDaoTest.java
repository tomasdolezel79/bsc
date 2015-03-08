package cz.bsc.homework.dao;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import cz.bsc.homework.dao.PaymentDao;
import cz.bsc.homework.dao.SimpleMemoryPaymentDao;
import cz.bsc.homework.domain.Payment;

public class PaymentDaoTest {

	@Test
	public void testStoreData() {
		PaymentDao dao = new SimpleMemoryPaymentDao();
		
		dao.store(new Payment("USD"));
		dao.store(new Payment("USD", 10));
		dao.store(new Payment("USD", -5));
		dao.store(new Payment("USD", 16.1d));
		//cekame, ze to bude scitat
		assertEquals(1, dao.getList().size());
		assertEquals(21.1d,dao.getList().get(0).getAmount().doubleValue(),0);
		
	}

}

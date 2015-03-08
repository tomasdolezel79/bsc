package cz.bsc.homework;

import java.io.IOException;

import org.easymock.EasyMock;
import org.junit.Test;

import cz.bsc.homework.domain.Payment;
import cz.bsc.homework.service.PaymentService;
import cz.bsc.homework.service.PrintService;

public class CommandDispatcherTest {

	
	
	@Test
	public void testCommandPayment() throws IOException {
		PaymentService mock = EasyMock.createMock(PaymentService.class);
		mock.addPayment(EasyMock.anyObject(Payment.class));
		EasyMock.expectLastCall();
		EasyMock.replay(mock);

		CommandDispatcher dispatcher = new CommandDispatcher();
		dispatcher.setPaymentService(mock);

		dispatcher.processCommand("CZK -100.50");
		EasyMock.verify(mock);
	}

	@Test
	public void testCommandPrint() throws IOException {
		PrintService mock = EasyMock.createMock(PrintService.class);
		mock.run();
		EasyMock.expectLastCall();
		EasyMock.replay(mock);

		CommandDispatcher dispatcher = new CommandDispatcher();
		dispatcher.setPrintService(mock);

		dispatcher.processCommand("print");
		EasyMock.verify(mock);
	}
	
	
}

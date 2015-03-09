package cz.bsc.homework;

import java.io.IOException;

import cz.bsc.homework.domain.Payment;
import cz.bsc.homework.lex.Command;
import cz.bsc.homework.lex.PaymentParser;
import cz.bsc.homework.service.DefaultPaymentService;
import cz.bsc.homework.service.PaymentService;
import cz.bsc.homework.service.PrintService;

/**
 * Dispatcher for commands from input
 * 
 * 
 * @author dolezelt
 *
 */
public class CommandDispatcher {

	private PaymentService paymentService;
	private PrintService printService;
	
	public PrintService getPrintService() {
		return printService;
	}

	public void setPrintService(PrintService printService) {
		this.printService = printService;
	}

	public PaymentService getPaymentService() {
		return paymentService;
	}

	public void setPaymentService(PaymentService paymentService) {
		this.paymentService = paymentService;
	}

	public void processCommand(String command) throws IOException {
		if (command != null) {
			Command knownCommand = getValue(command);
			if (knownCommand != null) {
				switch (knownCommand) {
				case PRINT:
					printService.run();
					break;
				case QUIT:
				case EXIT:
					System.exit(1);
				}
			} else
				processPayment(command);
		}
	}

	private Command getValue(String command) {
		try {
			return Command.valueOf(command.toUpperCase());
		} catch (IllegalArgumentException e) {
			return null;
		}
	}

	private void processPayment(String command) throws IOException {
		PaymentParser parser = new PaymentParser(command);
		while (parser.hasNext())
		paymentService.addPayment(parser.next());
	}

}

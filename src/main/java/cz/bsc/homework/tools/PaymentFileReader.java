package cz.bsc.homework.tools;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import cz.bsc.homework.console.Console;
import cz.bsc.homework.lex.PaymentParser;
import cz.bsc.homework.service.DefaultPaymentService;
import cz.bsc.homework.service.PaymentService;

/**
 * Read file and put result into  PaymentService
 * 
 * @author dolezelt
 *
 */
public class PaymentFileReader {
	private PaymentService paymentService;
	private Console console = Console.getInstance();

	public PaymentService getPaymentService() {
		return paymentService;
	}

	public void setPaymentService(PaymentService paymentService) {
		this.paymentService = paymentService;
	}

	public void read(File file) {
		console.print("Reading file " + file.getAbsolutePath());
		try (InputStream stream = new FileInputStream(file)) {
			PaymentParser parser = new PaymentParser(stream);
			while(parser.hasNext())
				paymentService.addPayment(parser.next());
		} catch (Exception e) {
			console.print(e);
		}

	}

}

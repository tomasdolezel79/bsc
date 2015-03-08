package cz.bsc.homework;

import java.io.File;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import cz.bsc.homework.console.Console;
import cz.bsc.homework.dao.SimpleMemoryPaymentDao;
import cz.bsc.homework.service.DefaultPaymentService;
import cz.bsc.homework.service.PrintService;
import cz.bsc.homework.tools.CommandLineReader;
import cz.bsc.homework.tools.PaymentFileReader;

/**
 * Main class of my application.
 * 
 * 
 * @author dolezelt
 *
 */
public class Application {

	private static final int DELAY = 1;

	public static void main(String[] args) {
		addShutdownHook();
		
		// I have no Spring, so I must create instances of my services manually (order of startup does not matter)...
		SimpleAppContextBuilder factory = new SimpleAppContextBuilder();
		
		
		// ... start scheduled services ...
		runScheduledServices(factory);
 
		// ... read app. aruments
		runArgsReading(args, factory);
		
		// ... and start command line reading
		runCommandLineReading(factory);
		
	
	}

	private static void runArgsReading(String[] args,
			SimpleAppContextBuilder factory) {
		if (args.length>0){
			PaymentFileReader paymentReader = new PaymentFileReader();
			paymentReader.setPaymentService(factory.getService());
			paymentReader.read(new File(args[0]));
		}
	}

	private static void runCommandLineReading(SimpleAppContextBuilder factory) {
		CommandLineReader commandLineReader = new CommandLineReader();
		commandLineReader.setCommandDispatcher(factory.getDispatcher());
		commandLineReader.doInput();
	}

	private static void runScheduledServices(SimpleAppContextBuilder factory) {
		PrintService printOutJob = factory.getPrintService();
		ScheduledExecutorService ex = Executors.newSingleThreadScheduledExecutor();
		ex.scheduleAtFixedRate(printOutJob, DELAY, 5, TimeUnit.MINUTES);
	}

	private static void addShutdownHook() {
		Runtime.getRuntime().addShutdownHook(new Thread(){
			public void run(){
				// say Bye!
				System.out.println ("Bye!");
			}
			
		});
	}

}

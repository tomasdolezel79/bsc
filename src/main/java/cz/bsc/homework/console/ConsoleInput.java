package cz.bsc.homework.console;

import java.util.concurrent.*;


/**
 * wraps ExecutorService
 * 
 * @author dolezelt
 *
 */
public class ConsoleInput {
 
	private Future<String> result;
	

	public void terminate(){
		if (result!=null)
		result.cancel(true);
		
	}
	
	public String readLine() throws InterruptedException {
		ExecutorService ex = Executors.newSingleThreadExecutor();
		String input = null;
		try {
			// start working
			 result = ex.submit(new ConsoleInputReadTask());
			try {
				input = result.get();
			} catch (ExecutionException e) {
				e.getCause().printStackTrace();
			} catch(CancellationException e){
				System.out.println("\nThread cancelled.");
			}

		} finally {
			ex.shutdownNow();
		}
		return input;
	}
}
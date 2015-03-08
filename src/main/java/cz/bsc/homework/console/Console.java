package cz.bsc.homework.console;

import java.io.IOException;
import java.util.concurrent.locks.ReentrantLock;


/**
 * Because standard console may not be available in every runtime environment and this is example for school and life...
 * 
 * @author dolezelt
 *
 */
public class Console {
	private ConsoleInput con;
	//console is shared for input and output, so I use some lock from java.util.concurrent
	private ReentrantLock reading;
	private ReentrantLock writing;

	//instance holder, because this is thread safe singleton
	private static class Holder {
		static final Console INSTANCE = new Console();
	}

	public static Console getInstance() {
		return Holder.INSTANCE;
	}

	private Console() {
		con = new ConsoleInput();
		reading = new ReentrantLock();
		writing  = new ReentrantLock();
	}

	/**
	 * Method is waiting for input from ConsoleInput
	 * 
	 * @param prompt Some text to be shown
	 * @return
	 * @throws IOException
	 */
	public String prompt(String prompt) throws IOException {
		//wait for printing job
		writing.lock();
		writing.unlock();
		reading.lock();
		String input = null;

		try {
			System.out.print(prompt);
			input = con.readLine();
		} catch (InterruptedException e) {
			print(e);
		} finally {
			reading.unlock();
		}
		return input;

	}

	/**
	 * Terminate input and print something on the screen
	 * 
	 * @param line
	 */
	public void print(String line) {
		writing.lock();
		if (con != null)
			con.terminate();
		//wait for termination command line listening
		reading.lock();
		try {
			System.out.println(line);
		} finally {
			reading.unlock();
			writing.unlock();
		}

	}

	/**
	 * Print exception on the screen
	 * 
	 * @param e
	 */
	public void print(Exception e) {
		StringBuilder sb = new StringBuilder(e.toString());
		for (StackTraceElement stack : e.getStackTrace()) {
			sb.append("\n" + stack.toString());
		}
		print(sb.toString());
	}

}

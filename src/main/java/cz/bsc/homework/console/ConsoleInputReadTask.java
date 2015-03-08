package cz.bsc.homework.console;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.Callable;

/**
 * ConsoleInputReadTask
 * @author dolezelt
 *
 */
public class ConsoleInputReadTask implements Callable<String> {
  public String call() throws IOException {
	  BufferedReader br = new BufferedReader(
        new InputStreamReader(System.in));
    String input;
    do {
      try {
        // wait until we have data to complete readLine()
        while (!br.ready()) {
          Thread.sleep(200);
        }
        input = br.readLine();
      } catch (InterruptedException e) {

        return null;
      }
    } while ("".equals(input));
    return input;
  }
}
  
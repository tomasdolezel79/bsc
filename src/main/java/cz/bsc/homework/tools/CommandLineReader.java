package cz.bsc.homework.tools;

import cz.bsc.homework.CommandDispatcher;
import cz.bsc.homework.console.Console;
import cz.bsc.homework.lex.LexException;
/**
 * reads input from console and send it to dispatcher
 * @author dolezelt
 *
 */
public class CommandLineReader  {
	private static final String PROMPT = "Enter new payment or command:";
	private CommandDispatcher commandDispatcher;
	private Console console = Console.getInstance();
	
 
	public CommandDispatcher getCommandDispatcher() {
		return commandDispatcher;
	}



	public void setCommandDispatcher(CommandDispatcher commandDispatcher) {
		this.commandDispatcher = commandDispatcher;
	}



	public void doInput() {
		while (true) {
			try {
				String input = console.prompt(PROMPT);
				commandDispatcher.processCommand(input);
			}catch (LexException e){
				console.print("Incorrect input at position "+e.getPosition());
			} catch (Exception e) {
				console.print(e);
			}
		}

	}

 

}

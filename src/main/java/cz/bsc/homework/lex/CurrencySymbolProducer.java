package cz.bsc.homework.lex;

/**
 * finite automaton reading currency
 * @author dolezelt
 *
 */

public class CurrencySymbolProducer implements SymborProducer<String> {
	private static final int CURRENCY_LENGTH = 3;
	private StringBuilder symbolBuilder = new StringBuilder();
 

	@Override
	public boolean isInFinalState() {
		return symbolBuilder.length()==CURRENCY_LENGTH;
	}
 
	@Override
	public String getValue() {
		return symbolBuilder.toString();
	}

	@Override
	public boolean isAcceptSymbo(char symbol) {
		if (Character.isAlphabetic(symbol))
			if (symbolBuilder.length() < 3) {
				return true;
			}
		return false;
	}

	@Override
	public void addSymbol(char symbol) {
		symbolBuilder.append(Character.toUpperCase(symbol));
		
	}

 
 

}

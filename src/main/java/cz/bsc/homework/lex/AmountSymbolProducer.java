package cz.bsc.homework.lex;

/**
 * finite automaton reading amount
 * @author dolezelt
 *
 */
public class AmountSymbolProducer implements SymborProducer<Double> {
	private StringBuilder symbolBuilder = new StringBuilder();
	private double amount;
	private boolean terminated = false;

	@Override
	public void addSymbol(char symbol) {
		symbolBuilder.append(symbol);
		try {
			if (symbol != '-')
				amount = Double.parseDouble(symbolBuilder.toString());
		} catch (NumberFormatException parseExceprion) {
			parseExceprion.printStackTrace();
		}
		
	}

	@Override
	public boolean isInFinalState() {
		if (symbolBuilder.length()==0||(symbolBuilder.length()==1&&  symbolBuilder.charAt(0)=='-'))
			return false;
		return true;
	}

	@Override
	public Double getValue() {
		return amount;
	}

	@Override
	public boolean isAcceptSymbo(char symbol) {
		if (terminated)
			return false;
		try {
			if (symbol != '-' || symbolBuilder.length() != 0)
				 Double.parseDouble(symbolBuilder.toString() + symbol);
		} catch (NumberFormatException parseExceprion) {
			terminated = symbolBuilder.length() > 0;
			return false;
		}
		return true;
	}

}

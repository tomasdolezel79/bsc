package cz.bsc.homework.lex;

import cz.bsc.homework.domain.Payment;

/**
 * finite automaton accepting Payments
 * 
 * @author dolezelt
 *
 */
public class PaymentDFA implements SymborProducer<Payment> {
	private AmountSymbolProducer amount = new AmountSymbolProducer();
	private CurrencySymbolProducer currency = new CurrencySymbolProducer();

	public boolean isInFinalState() {
		return amount.isInFinalState() && currency.isInFinalState();
	}

	@Override
	public Payment getValue() {
		return new Payment(currency.getValue(), amount.getValue());
	}

	private boolean isWhitespace(Character character) {
		if (character != null && (Character.isWhitespace(character)))
			return true;
		return false;
	}

	@Override
	public boolean isAcceptSymbo(char character) {
		if (isWhitespace(character)) {
			if ((currency.isInFinalState() && !amount.isInFinalState())
					|| (!currency.isInFinalState() && amount.isInFinalState())) {

				return true;
			}

			return false;
		}

		if (amount.isAcceptSymbo(character)
				|| currency.isAcceptSymbo(character))
			return true;
		return false;
	}

	@Override
	public void addSymbol(char character) {
		if (isWhitespace(character))
			return;
		if (amount.isAcceptSymbo(character))
			amount.addSymbol(character);
		else if (currency.isAcceptSymbo(character))
			currency.addSymbol(character);

	}

}

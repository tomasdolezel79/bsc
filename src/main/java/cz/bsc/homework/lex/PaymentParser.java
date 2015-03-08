package cz.bsc.homework.lex;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;

import cz.bsc.homework.domain.Payment;

/**
 * Simple Lexical parser for payment
 * 
 * @author dolezelt
 *
 */
public class PaymentParser implements Iterator<Payment> {
	private PaymentDFA dfa;

	private InputStream charSupplier;
	private Character character;

	private int position = 0;
	private Payment prepared;
	private boolean eof = false;

	public PaymentParser(String string) throws IOException {
		this(new ByteArrayInputStream(string.getBytes()));
	}

	public PaymentParser(InputStream inputStream) throws IOException {
		this.charSupplier = inputStream;
		dfa = new PaymentDFA();
		lex();
	}

	@Override
	public boolean hasNext() {
		try {
			return (prepared = nextSymbol()) != null;
		} catch (LexException e) {
			throw e;
		} catch (IOException e) {
			throw new IllegalStateException(e.getMessage());
		}
	}

	@Override
	public Payment next() {
		return prepared;
	}

	private void lex() throws IOException {
		int n;
		if ((n = charSupplier.read()) >= 0) {
			character = (char) n;
			position++;
		} else {
			eof = true;
			character = null;
		}
	}

	private boolean isWhitespace() {
		if (character != null
				&& (Character.isWhitespace(character) || character == '\n'))
			return true;
		return false;
	}

	private Payment nextSymbol() throws LexException, IOException {
		dfa = new PaymentDFA();
		while (character != null
				&&isWhitespace()){
			lex();
		}
		if (character != null) {
			while (character != null && dfa.isAcceptSymbo(character)) {
				dfa.addSymbol(character);
				lex();
			}
			if (dfa.isInFinalState()) {
				return dfa.getValue();
			}
			throw new LexException(position);
		}
		if (!eof)
			throw new LexException(position);
		return null;
	}
}

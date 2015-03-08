package cz.bsc.homework.lex;

/**
 * Iterface for finite automaton
 * 
 * @author dolezelt
 *
 * @param <T>
 */
public interface SymborProducer<T>{

	public boolean isAcceptSymbo(char symbol);
	
	public void addSymbol(char symbol);

	public boolean isInFinalState();
	
	public T getValue();
	
}

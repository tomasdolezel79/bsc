package cz.bsc.homework.lex;

/**
 * parser error
 * 
 * @author dolezelt
 *
 */
public class LexException extends IllegalArgumentException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int position;

	public LexException(int position) {
		this.position = position;
	}

	public int getPosition() {
		return position;
	}

	
	
	
}

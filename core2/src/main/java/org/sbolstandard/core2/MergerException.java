package org.sbolstandard.core2;

/**
 * Indicates that there was a problem merging entities.
 *
 * @author Matthew Pocock
 * @version 2.0
 */
public class MergerException extends Exception {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MergerException() {
    }

    public MergerException(String s) {
        super(s);
    }

    public MergerException(String message, Throwable cause) {
        super(message, cause);
    }

    public MergerException(Throwable cause) {
        super(cause);
    }
}

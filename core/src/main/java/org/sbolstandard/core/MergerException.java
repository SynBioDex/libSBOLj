package org.sbolstandard.core;

/**
 * Indicates that there was a problem merging entities.
 *
 * @author Matthew Pocock
 */
@Deprecated
public class MergerException extends Exception {
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

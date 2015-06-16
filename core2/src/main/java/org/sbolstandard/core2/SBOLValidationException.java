package org.sbolstandard.core2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author Zhen Zhang
 * @author Tramy Nguyen
 * @author Nicholas Roehner
 * @author Matthew Pocock
 * @author Goksel Misirli
 * @author Chris Myers
 * @version 2.0-beta
 */

public class SBOLValidationException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private final List<Identified> objects;
	
	/**
	 * Creates a new exception instance with the given message and objects causing the problem.
	 * @param message
	 * @param objects
	 */
	SBOLValidationException(String message, Identified ... objects) {
		this(message, Arrays.asList(objects));
	}
	
	/**
	 * Creates a new exception instance with the given message and objects causing the problem.
	 * 
	 * @param message
	 * @param objects
	 */
	SBOLValidationException(String message, java.util.Collection<? extends Identified> objects) {
		super(formatMessage(message, objects));

		this.objects = Collections.unmodifiableList(new ArrayList<>(objects));
	}

    /**
     * Creates a new exception instance with the given message and objects causing the problem.
     * 
     * @param message
     * @param cause
     * @param objects
     */
    SBOLValidationException(String message, Throwable cause, Identified ... objects) {
        super(message, cause);
        this.objects = Collections.unmodifiableList(Arrays.asList(objects));
    }

    /**
	 * Creates a new exception instance with the given cause but no specific objects for the problem.
	 * 
	 * @param cause
	 */	
	SBOLValidationException(Throwable cause) {
		super(cause);

		this.objects = Collections.emptyList();
	}

	/**
	 * Returns the list of objects relevant for the validation exception. This list may be empty if the exact object
	 * for the validation exception is not known. In those cases, the {@link #getCause() cause} of the exception can 
	 * provide more information.
	 * 
	 * @return a collection of Identified instances
	 */
	java.util.Collection<Identified> getObjects() {
		return objects;
	}

	private static String formatMessage(String message, java.util.Collection<? extends Identified> objects) {
		final StringBuilder sb = new StringBuilder(message);
		if (!objects.isEmpty()) {
			sb.append(": ");
			boolean first = true;
			for (Identified obj : objects) {
				if (first) {
					first = false;
				}
				else {
					sb.append(", ");
				}
				if (obj.getIdentity() != null) {
					sb.append(obj.getIdentity());
				}
			}
		}
		return sb.toString();
	}
}


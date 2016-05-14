package org.sbolstandard.core2;

import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Signals that an exception related to conversion between SBOL and other file formats has occurred.
 * 
 * @author Chris Myers
 * @author Zhen Zhang
 * @version 2.1
 */

public class SBOLConversionException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final List<Identified> objects;
	private static String exceptionMessage;

	/**
	 * Creates a new exception instance with the given message and objects causing the problem.
	 * @param message
	 * @param objects
	 */
	SBOLConversionException(String message, Identified ... objects) {
		this(message, Arrays.asList(objects));
	}

	/**
	 * Creates a new exception instance with the given message and objects causing the problem.
	 * @param message
	 * @param objects
	 */
	SBOLConversionException(String message, URI identity) {
		super(exceptionMessage = formatMessage(message, identity));
		this.objects = null;
	}

	/**
	 * Creates a new exception instance with the given message and objects causing the problem.
	 * 
	 * @param message
	 * @param objects
	 */
	SBOLConversionException(String message, java.util.Collection<? extends Identified> objects) {
		super(exceptionMessage = formatMessage(message, objects));
		this.objects = Collections.unmodifiableList(new ArrayList<>(objects));
	}

	/**
	 * Creates a new exception instance with the given message and objects causing the problem.
	 * 
	 * @param message
	 * @param cause
	 * @param objects
	 */
	SBOLConversionException(String message, Throwable cause, Identified ... objects) {
		super(message, cause);
		this.objects = Collections.unmodifiableList(Arrays.asList(objects));
	}

	/**
	 * Creates a new exception instance with the given cause but no specific objects for the problem.
	 * 
	 * @param cause
	 */	
	SBOLConversionException(Throwable cause) {
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
	
	private static String formatMessage(String message, URI identity) {
		final StringBuilder sb = new StringBuilder(message);
		sb.append(": " + identity.toString());
		return sb.toString();
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
	
	String getExceptionMessage() {
		return exceptionMessage;
	}

}


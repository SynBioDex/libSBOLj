package org.oboparser.obo;

public class OBOException extends RuntimeException {

	public OBOException(String err) { 
		super(err);
	}
	
	public OBOException(Throwable t) { 
		super(t);
	}
	
	public OBOException(String err, Throwable t) { 
		super(err, t);
	}
}

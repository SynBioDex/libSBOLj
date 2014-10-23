package org.sbolstandard.core2;

import java.net.URI;

import org.sbolstandard.core2.abstract_classes.Location;

public class Range extends Location{
	
	protected Integer start;
	protected Integer end;
	
	public Range(URI identity, Integer start, Integer end) {
		super(identity);
		setStart(start);
		setEnd(end);
	}
	
	
	/**
	 * Sets field variable <code>start</code> to the specified element.
	 * @param value
	 */
	public void setStart(Integer value) {
		start = value;		
	}
	
	/**
	 * Returns field variable <code>start</code>.
	 * @return field variable <code>start</code>
	 */
	public int getStart() {		
		return start;
	}

	/**
	 * Returns field variable <code>end</code>.
	 * @return field variable <code>end</code>
	 */
	public int getEnd() {
		return end;
	}

	/**
	 * Sets field variable <code>end</code> to the specified element.
	 * @param value
	 */
	public void setEnd(Integer value) {
		end = value;
	}

}

package org.sbolstandard.core2;

import java.net.URI;

import org.sbolstandard.core2.abstract_classes.Location;

public class Range extends Location{
	
	protected Integer start;
	
	protected Integer end;
	
	public Range(URI identity, Integer start, Integer end) {
		super(identity);
		this.start = start;
		this.end = end;
		
	}
	
	public void setStart(Integer value) {
		start = value;		
	}

	public int getStart() {		
		return start;
	}

	public int getEnd() {
		return end;
	}

	public void setEnd(Integer value) {
		end = value;
		
	}

}

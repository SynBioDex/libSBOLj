package org.sbolstandard.core2;

import java.net.URI;

import org.sbolstandard.core2.abstract_classes.ComponentInstance;

public class FunctionalComponent extends ComponentInstance{
		
	private DirectionType direction;
	
	public FunctionalComponent(URI identity, AccessType access, 
			URI instantiatedComponent, DirectionType direction) {
		super(identity,access, instantiatedComponent);
		setDirection(direction);
	}

	/**
	 * Returns field variable <code>direction</code> to the specified element.
	 * @return field variable <code>direction</code> to the specified element
	 */
	public DirectionType getDirection() {
		return direction;
	}

	/**
	 * Sets field variable <code>direction</code> to the specified element.
	 * @param direction
	 */
	public void setDirection(DirectionType direction) {
		this.direction = direction;
	}
}

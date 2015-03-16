package org.sbolstandard.core2;

import java.net.URI;

import org.sbolstandard.core2.FunctionalComponent.DirectionType;
import org.sbolstandard.core2.abstract_classes.ComponentInstance;
import org.sbolstandard.core2.abstract_classes.Documented;

public class FunctionalComponent extends ComponentInstance {

	private DirectionType direction;

	public enum DirectionType {
		INPUT("input"), OUTPUT("output"), INOUT("inout"), NONE("none");
		private final String	directionType;

		private DirectionType(String directionType) {
			this.directionType = directionType;
		}

		/**
		 * Returns the direction type.
		 * 
		 * @return direction type.
		 */
		public String getDirectionType() {
			return directionType;
		}

		@Override
		public String toString() {
			return directionType;
		}

		/**
		 * Convert the specified URI to its corresponding DirectionType instance.
		 * @param direction
		 * @return the corresponding DirectionType instance
		 */
		public static DirectionType convertToDirectionType(URI direction) {
			if (direction != null) {
				if (direction.equals(DirectionType.INOUT)) {
					return DirectionType.INOUT;
				} else if (direction.equals(DirectionType.INPUT)) {
					return DirectionType.INPUT;
				} else if (direction.equals(DirectionType.OUTPUT)) {
					return DirectionType.OUTPUT;
				} else if (direction.equals(DirectionType.INOUT)) {
					return DirectionType.INOUT;
				} else {
					return null;
				}
			} else {
				return null;
			}
		}
		
		/**
		 * Returns the direction type in URI.
		 * @return direction type in URI
		 */
		public static URI convertToURI(DirectionType direction) {
			if (direction != null) {
				if (direction.equals(DirectionType.INOUT)) {
					return Direction.inout;
				}
				else if (direction.equals(DirectionType.INPUT)) {
					return Direction.input;
				}
				else if (direction.equals(DirectionType.OUTPUT)) {
					return Direction.output;
				}
				else if (direction.equals(DirectionType.NONE)) {
					return Direction.none;
				}
				else {
					return null;
				}
			}
			else {
				return null;
			}
		}
	}
	

	public FunctionalComponent(URI identity, AccessType access, URI instantiatedComponent,
			DirectionType direction) {
		super(identity, access, instantiatedComponent);
		setDirection(direction);
	}
	
	private FunctionalComponent(FunctionalComponent functionalComponent) {
		super(functionalComponent);
		this.setDirection(functionalComponent.getDirection());
	}
	
	

	/**
	 * Returns field variable <code>direction</code> to the specified element.
	 * 
	 * @return field variable <code>direction</code> to the specified element
	 */
	public DirectionType getDirection() {
		return direction;
	}

	/**
	 * Returns the direction type in URI.
	 * 
	 * @return direction type in URI
	 */
	public URI getDirectionURI() {
		if (direction != null) {
			if (direction.equals(DirectionType.INOUT)) {
				return Direction.inout;
			} else if (direction.equals(DirectionType.INPUT)) {
				return Direction.input;
			} else if (direction.equals(DirectionType.OUTPUT)) {
				return Direction.output;
			} else if (direction.equals(DirectionType.INOUT)) {
				return Direction.inout;
			} else {
				return null;
			}
		} else {
			return null;
		}
	}

	/**
	 * Sets field variable <code>direction</code> to the specified element.
	 * 
	 * @param direction
	 */
	public void setDirection(DirectionType direction) {
		this.direction = direction;
	}

	/**
	 * Sets field variable <code>direction</code> to the element corresponding
	 * to the specified URI.
	 * 
	 * @param direction
	 */
	public void setDirection(URI direction) {
		if (direction.equals(Direction.input)) {
			this.direction = DirectionType.INPUT;
		} else if (direction.equals(Direction.output)) {
			this.direction = DirectionType.OUTPUT;
		} else if (direction.equals(Direction.inout)) {
			this.direction = DirectionType.INOUT;
		} else if (direction.equals(Direction.none)) {
			this.direction = DirectionType.NONE;
		} else {
			// TODO: Validation?
			this.direction = null;
		}
	}

	public static final class Direction {
		public static final URI	input	= URI.create(Sbol2Terms.sbol2.getNamespaceURI() + "input");
		public static final URI	output	= URI.create(Sbol2Terms.sbol2.getNamespaceURI() + "output");
		public static final URI	inout	= URI.create(Sbol2Terms.sbol2.getNamespaceURI() + "inout");
		public static final URI	none	= URI.create(Sbol2Terms.sbol2.getNamespaceURI() + "none");
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((direction == null) ? 0 : direction.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		FunctionalComponent other = (FunctionalComponent) obj;
		if (direction != other.direction)
			return false;
		return true;
	}

	@Override
	protected FunctionalComponent deepCopy() {
		return new FunctionalComponent(this);
	}
}

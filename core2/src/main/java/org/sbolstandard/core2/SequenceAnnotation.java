package org.sbolstandard.core2;

import java.net.URI;
import java.net.URISyntaxException;

import org.sbolstandard.core2.abstract_classes.ComponentInstance;
import org.sbolstandard.core2.abstract_classes.Documented;
import org.sbolstandard.core2.abstract_classes.Location;

/**
 * 
 * @author Ernst Oberortner
 * @author Nicholas Roehner
 * @version 2.0
 */

public class SequenceAnnotation extends Documented {

	private Location location;
	private Component structuralInstantiation;
//	private int start;
//	private int end;
//	private Orientation orientation;	
//	private Collection<StructuralAnnotation> precededAnnotations;
	
	public SequenceAnnotation(URI identity, Location location) {
		super(identity);
		setLocation(location);		
	}
	

	/**
	 * Returns field variable <code>location</code>.
	 * @return field variable <code>location</code>
	 */
	public Location getLocation() {
		return location;
	}

	/**
	 * Sets field variable <code>location</code> to the specified element.
	 * @param location
	 */
	public void setLocation(Location location) {
		this.location = location;
	}	
	
	/**
	 * Test if optional field variable <code>structuralInstantiation</code> is set.
	 * @return <code>true</code> if it is not null.
	 */
	public boolean isSetStructuralInstantiation() {
		if (structuralInstantiation == null)
			return false;
		else
			return true;
	}
	
	/**
	 * Returns field variable <code>structuralInstantiation</code>.
	 * @return field variable <code>structuralInstantiation</code>
	 */
	public Component getStructuralInstantiation() {
		return structuralInstantiation;
	}

	/**
	 * Sets field variable <code>structuralInstantiation</code> to the specified element.
	 * @param structuralInstantiation
	 */
	public void setStructuralInstantiation(Component structuralInstantiation) {
		this.structuralInstantiation = structuralInstantiation;
	}
	
	/**
	 * Set optional field variable <code>structuralInstantiation</code> to <code>null</code>.
	 */
	public void unsetStructuralInstantiation() {
		structuralInstantiation = null;
	}

//	/**
//	 * 
//	 * @param identity an identity for the sequence annotation
//	 * @param displayId a display ID for the sequence annotation
//	 * @param start a starting position for the sequence annotation
//	 * @param end an ending position for the sequence annotation
//	 */
//	public StructuralAnnotation(URI identity, String displayId, int start, int end) {
//		this(identity, displayId);
//		//this.start = start;
//		//this.end = end;
//	}

//	/**
//	 * @deprecated Creating an empty Sequence object is not recommended. 
//	 * 
//	 */	 
//	public StructuralAnnotation() {
//		
//	}

//	/**
//	 * 
//	 * @return the sequence annotation's starting position
//	 */
//	public int getStart() {
//		return start;
//	}
	
	/**
	 * 
     * First position of the Sequence Feature being annotated.
     * Start coordinate is in terms of the Sequence of the SequenceComponent
     * annotated.
     * @return positive integer coordinate of first base of the SequenceFeature.
     * @deprecated As of release 2.0, replaced by {@link #getStart}.      
     */
	public Integer getBioStart() {
		if (location instanceof Range) {
			return ((Range) location).getStart();
		}		
		return null;		
	}

//	/**
//	 * 
//	 * @return the sequence annotation's orientation
//	 */
//	public Orientation getOrientation() {
//		return orientation;
//	}
	
	/**
     * Orientation of feature is the + or - strand.
     * 
     * Sequences used are by convention assumed 5' to 3', therefore the 
     * <code>+</code> strand is 5' to 3' and the <code>-</code> strand 
     * is 3' to 5'.
     *
     * @return <code>+</code> if feature aligns in same direction as DnaComponent,
     *         <code>-</code> if feature aligns in opposite direction as DnaComponent.
     * @deprecated As of release 2.0, replaced by {@link #getOrientation()}
     */
	public Orientation getStrand() {
		Location loc = getLocation();
		if (loc instanceof OrientedRange) {			
			Orientation ori = ((OrientedRange) loc).getOrientation();
			if (ori.equals(Orientation.inline)) {				
				return Orientation.POSITIVE;
			}
			else {
				return Orientation.NEGATIVE;
			}
		}
		return null;
	}

//	/**
//	 * 
//	 * @param orientation an orientation for the sequence annotation
//	 */
//	public void setOrientation(Orientation orientation) {
//		this.orientation = orientation;
//	}
	
	/**
	 * @param value
	 * @deprecated As of release 2.0, replaced by {@link #setOrientation()}
	 */
	public void setStrand(Orientation value) {
//		if (value.equals(Orientation.POSITIVE)) {
//			this.orientation = Orientation.inline;
//		}
//		else if (value.equals(Orientation.NEGATIVE)) {
//			this.orientation = Orientation.reverseComplement;
//		}
		Location loc = getLocation();		
		if (loc instanceof OrientedRange) {
			if (value.equals(Orientation.POSITIVE)) {
				((OrientedRange) loc).setOrientation(Orientation.inline);
			}
			else if (value.equals(Orientation.NEGATIVE)) {
				((OrientedRange) loc).setOrientation(Orientation.reverseComplement);
			}
			
			// TODO: strand should be + or -. 
		}
		// TODO: Error message. 
	}
	
//	/**
//	 * 
//	 * @return the sequence annotation's subcomponent instantiation
//	 */
//	public ComponentInstantiation getSubComponentInstantiation() {
//		return structuralInstantiation;
//	}
	
	// TRAMY - PUT BACK
	/**
	 * @return
	 * @deprecated As of release 2.0, replaced by {@link #getSubComponentInstantiation()}
	 */
//	public SequenceComponent getSubComponent() {
////		if (structuralInstantiation != null) {
////			Component tmp = structuralInstantiation.getInstantiatedComponent();
////			if (tmp instanceof SequenceComponent) {
////				return (SequenceComponent) tmp;
////			}
////			else {
////				// TODO Throw proper exception.
////				return null;				
////			}
////		}
//		return null;
//	}
	
//	/**
//	 * 
//	 * @param subComponentInstantiation a subcomponent instantiation for the sequence annotation
//	 */
//	public void setSubComponentInstantiation(ComponentInstantiation subComponentInstantiation) {
//		this.structuralInstantiation = subComponentInstantiation;
//	}
	
	/**
	 * Warning: Default URI and displayId are generated for a new component instantiation.
	 * Make sure they do not conflict with existing ones.
	 * @throws URISyntaxException 
	 * @deprecated As of release 2.0, replaced by {@link #setSubComponentInstantiation(ComponentInstantiation)}
	 * // TRAMY - PUT BACK
	 */
//	public void setSubComponent(SequenceComponent subComponent) {
////		String identityStr = getIdentity().toString() + "/" + subComponent.getDisplayId();
////		URI identity = URI.create(identityStr);
////		String displayId = getDisplayId() + "_" + subComponent.getDisplayId();				
////		this.structuralInstantiation = new ComponentInstantiation(identity, displayId, subComponent);
//	}

//	/**
//	 * 
//	 * @return a collection of sequence annotations preceded by this sequence annotation
//	 */
//	public Collection<StructuralAnnotation> getPrecededAnnotations() {
//		return precededAnnotations;
//	}
//
//	/**
//	 * 
//	 * @param precededAnnotation a preceded sequence annotation for this sequence annotation
//	 */
//	public void addPrecededAnnotation(StructuralAnnotation precededAnnotation) {
//		precededAnnotations.add(precededAnnotation);
//	}
		
//	public void setStart(Integer value) {
//		this.start = value;
//	}
	
	/**
	 * @param value
	 * @deprecated As of release 2.0, replaced by {@link #setStart(Integer)}
	 */
	public void setBioStart(Integer value) {
		//this.start = value;	
		if (location instanceof Range) {
			((Range) location).setStart(value);
		}
	}
	
//	/**
//     * Last position of the Sequence Feature on the DnaComponent.
//     * Coordinate in terms of the DnaSequence of the DnaComponent annotated.
//     *      
//	 * @return the sequence annotation's ending position
//	 */
//	public int getEnd() {
//		if (location instanceof Range) {
//			return ((Range) location).getEnd();
//		}
//		//return 0;
//		
//	}
	
	/**
	 * Last position of the Sequence Feature on the DnaComponent.
	 * Coordinate in terms of the DnaSequence of the DnaComponent annotated.
	 * @return positive integer coordinate of last base of the SequenceFeature
	 * @deprecated As of release 2.0, replaced by {@link #getEnd(Integer)}
	 */
	public Integer getBioEnd() {
		if (location instanceof Range) {
			return ((Range) location).getEnd();
		}		
		return null;
	}

//	public void setEnd(Integer value) {
//		this.end = value;
//	}
	
	/**
	 * @param value
	 * @deprecated As of release 2.0, replaced by {@link #setEnd(Integer)}
	 */
	public void setBioEnd(Integer value) {
		// this.end = value;
		if (location instanceof Range) {
			((Range) location).setEnd(value);
		}
	}





}

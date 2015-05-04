package org.sbolstandard.core2;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.sbolstandard.core2.URIcompliance.*;

/**
 * 
 * @author Ernst Oberortner
 * @author Nicholas Roehner
 * @version 2.0
 */

public class SequenceAnnotation extends Documented {

	private Location location;
	private URI component;
	private ComponentDefinition componentDefinition = null;
	
	SequenceAnnotation(URI identity, Location location) {
		super(identity);
		setLocation(location);		
	}
	
	private SequenceAnnotation(SequenceAnnotation sequenceAnnotation) {
		super(sequenceAnnotation.getIdentity());
		//this.setLocation(sequenceAnnotation.getLocation().deepCopy());
		Location originalLocation = sequenceAnnotation.getLocation();
		if(originalLocation instanceof MultiRange) {
			this.setLocation(((MultiRange) originalLocation).deepCopy());
		}
		else if(originalLocation instanceof Range) {
			this.setLocation(((Range) originalLocation).deepCopy());
		}
		else if(originalLocation instanceof Cut) {
			this.setLocation(((Cut) originalLocation).deepCopy());
		}
		else if(originalLocation instanceof GenericLocation) {
			this.setLocation(((GenericLocation) originalLocation).deepCopy());
		}
		if (sequenceAnnotation.isSetComponent()) {
			this.setComponent(sequenceAnnotation.getComponentURI());
		}
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
	void setLocation(Location location) {
		if (location==null) {
			throw new IllegalArgumentException("Sequence annotation "+this.getIdentity()+" must have a location.");
		}
		this.location = location;
	}	
	
	public void addRange(int start,int end) {
		addRange(start,end,null);
	}

	public void addRange(int start,int end,OrientationType orientation) {
		if (location instanceof MultiRange) {
			int numRanges = ((MultiRange)location).getRanges().size();
			Range range = new Range(URIcompliance.createCompliantURI(this.getPersistentIdentity().toString()+"/multiRange","range"+numRanges,this.getVersion()),start,end);
			range.setPersistentIdentity(URI.create(this.getPersistentIdentity().toString()+"/multiRange/range"+numRanges));
			range.setDisplayId("range"+numRanges);
			range.setVersion(this.getVersion());
			if (orientation!=null) range.setOrientation(orientation);
			((MultiRange)location).addRange(range);
		} else if (location instanceof Range) {
			List<Range> ranges = new ArrayList<>();
			location.setIdentity(URIcompliance.createCompliantURI(this.getPersistentIdentity().toString()+"/multiRange","range0",this.getVersion()));
			location.setPersistentIdentity(URI.create(this.getPersistentIdentity().toString()+"/multiRange/range0"));
			location.setDisplayId("range0");
			ranges.add((Range)location);
			Range range = new Range(URIcompliance.createCompliantURI(this.getPersistentIdentity().toString()+"/multiRange","range1",this.getVersion()),start,end);
			range.setPersistentIdentity(URI.create(this.getPersistentIdentity().toString()+"/multiRange/range1"));
			range.setDisplayId("range1");
			range.setVersion(this.getVersion());
			if (orientation!=null) range.setOrientation(orientation);
			ranges.add(range);
			MultiRange multiRange = new MultiRange(URIcompliance.createCompliantURI(this.getPersistentIdentity().toString(),"multiRange",this.getVersion()),ranges);
			multiRange.setPersistentIdentity(URI.create(this.getPersistentIdentity().toString()+"/multiRange"));
			multiRange.setDisplayId("multiRange");
			multiRange.setVersion(this.getVersion());
			location = multiRange;
		} else {
			location = new Range(URIcompliance.createCompliantURI(this.getPersistentIdentity().toString(),"range",this.getVersion()),start,end);
			location.setPersistentIdentity(URI.create(this.getPersistentIdentity().toString()+"/range"));
			location.setDisplayId("range");
			location.setVersion(this.getVersion());
			if (orientation!=null) ((Range)location).setOrientation(orientation);
		}
	}
	
	void removeRange(URI rangeURI) {
		if (location instanceof MultiRange) {
			try {
				((MultiRange)location).removeRange(rangeURI);
			} catch (Exception e) {
				Set<Range> ranges = ((MultiRange)location).getRanges();
				if (ranges.size()!=2) {
					throw new IllegalArgumentException("Sequence annotation " + this.getIdentity() + 
							" is required to have a location.");
				}
				for (Range range : ranges) {
					if (range.getIdentity().equals(rangeURI)) continue;
					location = new Range(URIcompliance.createCompliantURI(this.getPersistentIdentity().toString(), 
							"range", this.getVersion()),range.getStart(),range.getEnd());
					if (range.isSetOrientation()) {
						((Range)location).setOrientation(range.getOrientation());
					}
				}
			}
		}
	}
		
	/**
	 * Test if optional field variable <code>component</code> is set.
	 * @return <code>true</code> if it is not null.
	 */
	public boolean isSetComponent() {
		return component != null;
	}
	
	/**
	 * Returns field variable <code>component</code>.
	 * @return field variable <code>component</code>
	 */
	public URI getComponentURI() {
		return component;
	}

	public Component getComponent() {
		if (componentDefinition==null) return null;
		return componentDefinition.getComponent(component);
	}
	
	public void setComponent(String component) {
		URI componentURI = URIcompliance.createCompliantURI(componentDefinition.getPersistentIdentity().toString(), 
				component, componentDefinition.getVersion());
		setComponent(componentURI);
	}

	/**
	 * Sets field variable <code>component</code> to the specified element.
	 * @param componentURI
	 */
	public void setComponent(URI componentURI) {
		if (componentDefinition!=null) {
			if (componentDefinition.getComponent(componentURI)==null) {
				throw new IllegalArgumentException("Component '" + componentURI + "' does not exist.");
			}
		}
		this.component = componentURI;
	}
	
	/**
	 * Set optional field variable <code>component</code> to <code>null</code>.
	 */
	public void unsetComponent() {
		component = null;
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
	
//	/**
//	 * 
//     * First position of the Sequence Feature being annotated.
//     * Start coordinate is in terms of the Sequence of the SequenceComponent
//     * annotated.
//     * @return positive integer coordinate of first base of the SequenceFeature.
//     * @deprecated As of release 2.0, replaced by {@link #getStart}.      
//     */
//	public Integer getBioStart() {
//		if (location instanceof Range) {
//			return ((Range) location).getStart();
//		}		
//		return null;		
//	}

//	/**
//	 * 
//	 * @return the sequence annotation's orientation
//	 */
//	public Orientation getOrientation() {
//		return orientation;
//	}
	
//	/**
//     * Orientation of feature is the + or - strand.
//     * 
//     * Sequences used are by convention assumed 5' to 3', therefore the 
//     * <code>+</code> strand is 5' to 3' and the <code>-</code> strand 
//     * is 3' to 5'.
//     *
//     * @return <code>+</code> if feature aligns in same direction as DnaComponent,
//     *         <code>-</code> if feature aligns in opposite direction as DnaComponent.
//     * @deprecated As of release 2.0, replaced by {@link #getOrientation()}
//     */
//	public Orientation getStrand() {
//		Location loc = getLocation();
//		if (loc instanceof OrientedRange) {			
//			Orientation ori = ((OrientedRange) loc).getOrientation();
//			if (ori.equals(Orientation.inline)) {				
//				return Orientation.POSITIVE;
//			}
//			else {
//				return Orientation.NEGATIVE;
//			}
//		}
//		return null;
//	}
//	
//	/**
//	 * @param value
//	 * @deprecated As of release 2.0, replaced by {@link #setOrientation()}
//	 */
//	public void setStrand(Orientation value) {
////		if (value.equals(Orientation.POSITIVE)) {
////			this.orientation = Orientation.inline;
////		}
////		else if (value.equals(Orientation.NEGATIVE)) {
////			this.orientation = Orientation.reverseComplement;
////		}
//		Location loc = getLocation();		
//		if (loc instanceof OrientedRange) {
//			if (value.equals(Orientation.POSITIVE)) {
//				((OrientedRange) loc).setOrientation(Orientation.inline);
//			}
//			else if (value.equals(Orientation.NEGATIVE)) {
//				((OrientedRange) loc).setOrientation(Orientation.reverseComplement);
//			}
//			
//			// TODO: strand should be + or -. 
//		}
//		// TODO: Error message. 
//	}
	
//	/**
//	 * 
//	 * @return the sequence annotation's subcomponent instantiation
//	 */
//	public ComponentInstantiation getSubComponentInstantiation() {
//		return component;
//	}
	
//	// TRAMY - PUT BACK
//	/**
//	 * @return
//	 * @deprecated As of release 2.0, replaced by {@link #getSubComponentInstantiation()}
//	 */
//	public SequenceComponent getSubComponent() {
////		if (component != null) {
////			Component tmp = component.getInstantiatedComponent();
////			if (tmp instanceof SequenceComponent) {
////				return (SequenceComponent) tmp;
////			}
////			else {
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
//		this.component = subComponentInstantiation;
//	}
	
//	/**
//	 * Warning: Default URI and displayId are generated for a new component instantiation.
//	 * Make sure they do not conflict with existing ones.
//	 * @throws URISyntaxException 
//	 * @deprecated As of release 2.0, replaced by {@link #setSubComponentInstantiation(ComponentInstantiation)}
//	 * // TRAMY - PUT BACK
//	 */
//	public void setSubComponent(SequenceComponent subComponent) {
////		String identityStr = getIdentity().toString() + "/" + subComponent.getDisplayId();
////		URI identity = URI.create(identityStr);
////		String displayId = getDisplayId() + "_" + subComponent.getDisplayId();				
////		this.component = new ComponentInstantiation(identity, displayId, subComponent);
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
	
//	/**
//	 * @param value
//	 * @deprecated As of release 2.0, replaced by {@link #setStart(Integer)}
//	 */
//	public void setBioStart(Integer value) {
//		//this.start = value;	
//		if (location instanceof Range) {
//			((Range) location).setStart(value);
//		}
//	}
	
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
	
//	/**
//	 * Last position of the Sequence Feature on the DnaComponent.
//	 * Coordinate in terms of the DnaSequence of the DnaComponent annotated.
//	 * @return positive integer coordinate of last base of the SequenceFeature
//	 * @deprecated As of release 2.0, replaced by {@link #getEnd(Integer)}
//	 */
//	public Integer getBioEnd() {
//		if (location instanceof Range) {
//			return ((Range) location).getEnd();
//		}		
//		return null;
//	}

//	public void setEnd(Integer value) {
//		this.end = value;
//	}
	
//	/**
//	 * @param value
//	 * @deprecated As of release 2.0, replaced by {@link #setEnd(Integer)}
//	 */
//	public void setBioEnd(Integer value) {
//		// this.end = value;
//		if (location instanceof Range) {
//			((Range) location).setEnd(value);
//		}
//	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((component == null) ? 0 : component.hashCode());
		result = prime * result + ((location == null) ? 0 : location.hashCode());
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
		SequenceAnnotation other = (SequenceAnnotation) obj;
		if (component == null) {
			if (other.component != null)
				return false;
		} else if (!component.equals(other.component))
			return false;
		if (location == null) {
			if (other.location != null)
				return false;
		} else if (!location.equals(other.location))
			return false;
		return true;
	}

	@Override
	protected SequenceAnnotation deepCopy() {
		return new SequenceAnnotation(this);
	}

	/**
	 * Assume this SequenceAnnotation object has compliant URI, and all given parameters have compliant forms.
	 * This method is called by {@link ComponentDefinition#copy(String, String, String)}.
	 * @param URIprefix
	 * @param parentDisplayId
	 * @param version
	 */
	void updateCompliantURI(String URIprefix, String parentDisplayId, String version) {
		String thisObjDisplayId = extractDisplayId(this.getIdentity()); // 1 indicates that this object is a child of a top-level object.
		URI newIdentity = URI.create(URIprefix + '/' + parentDisplayId + '/' 
				+ thisObjDisplayId + '/' + version);
		Location location = this.getLocation();
		if (location instanceof Range) {
			((Range) location).updateCompliantURI(URIprefix, parentDisplayId, thisObjDisplayId, version);
		}
		if (location instanceof Cut) {
			((Cut) location).updateCompliantURI(URIprefix, parentDisplayId, thisObjDisplayId, version);
		}
		if (location instanceof GenericLocation) {
			((GenericLocation) location).updateCompliantURI(URIprefix, parentDisplayId, thisObjDisplayId, version);
		}
		if (location instanceof MultiRange) {
			((MultiRange) location).updateCompliantURI(URIprefix, parentDisplayId, thisObjDisplayId, version);
		}
		// TODO: need to set wasDerivedFrom here?
		this.setWasDerivedFrom(this.getIdentity());
		this.setIdentity(newIdentity);
	}

	/**
	 * @return the componentDefinition
	 */
	ComponentDefinition getComponentDefinition() {
		return componentDefinition;
	}

	/**
	 * @param componentDefinition the componentDefinition to set
	 */
	void setComponentDefinition(ComponentDefinition componentDefinition) {
		this.componentDefinition = componentDefinition;
	}
}

package org.sbolstandard.core2;

import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.sbolstandard.core2.URIcompliance.*;

/**
 * @author Zhen Zhang
 * @author Tramy Nguyen
 * @author Nicholas Roehner
 * @author Matthew Pocock
 * @author Goksel Misirli
 * @author Chris Myers
 * @version 2.0-beta
 */

public class SequenceAnnotation extends Identified {

	private HashMap<URI, Location> locations;
	private URI component;
	private ComponentDefinition componentDefinition = null;
	
	SequenceAnnotation(URI identity, List<Location> locations) {
		super(identity);
		this.locations = new HashMap<>();
		this.setLocations(locations);		
	}
	
	private SequenceAnnotation(SequenceAnnotation sequenceAnnotation) {
		super(sequenceAnnotation);
		this.locations = new HashMap<>();
		for (Location location : sequenceAnnotation.getLocations()) {
			addLocation(location.deepCopy());
		}
		if (sequenceAnnotation.isSetComponent()) {
			this.setComponent(sequenceAnnotation.getComponentURI());
		}
	}
	
	public void addGenericLocation(String displayId) {
		URI identity = createCompliantURI(this.getPersistentIdentity().toString(),displayId,this.getVersion());
		GenericLocation genericLocation = new GenericLocation(identity);
		addLocation(genericLocation);
	}
	
	public void addGenericLocation(String displayId,OrientationType orientation) {
		URI identity = createCompliantURI(this.getPersistentIdentity().toString(),displayId,this.getVersion());
		GenericLocation genericLocation = new GenericLocation(identity);
		genericLocation.setOrientation(orientation);
		addLocation(genericLocation);
	}

	/**
	 * @param displayId
	 * @param at
	 */
	public void addCut(String displayId,int at) {
		URI identity = createCompliantURI(this.getPersistentIdentity().toString(),displayId,this.getVersion());
		Cut cut = new Cut(identity,at);
		addLocation(cut);
	}
	
	/**
	 * @param displayId
	 * @param at
	 * @param orientation
	 */
	public void addCut(String displayId,int at,OrientationType orientation) {
		URI identity = createCompliantURI(this.getPersistentIdentity().toString(),displayId,this.getVersion());
		Cut cut = new Cut(identity,at);
		cut.setOrientation(orientation);
		addLocation(cut);
	}
	

	public void addRange(String displayId,int start,int end) {
		URI identity = createCompliantURI(this.getPersistentIdentity().toString(),displayId,this.getVersion());
		Range range = new Range(identity,start,end);
		addLocation(range);
	}
	
	public void addRange(String displayId,int start,int end,OrientationType orientation) {
		URI identity = createCompliantURI(this.getPersistentIdentity().toString(),displayId,this.getVersion());
		Range range = new Range(identity,start,end);
		range.setOrientation(orientation);
		addLocation(range);
	}
	
	void addLocation(Location location) {
		addChildSafely(location, locations, "location");
		location.setSBOLDocument(this.sbolDocument);
	}
	
	/**
	 * Removes the instance matching the specified URI from the list of locations if present.
	 * @return the matching instance if present, or <code>null</code> if not present.
	 */
	public boolean removeLocation(Location location) {
		if (sbolDocument!=null) sbolDocument.checkReadOnly();
		return removeChildSafely(location,locations);
	}
	
	/**
	 * Returns the instance matching the specified displayId from the list of locations, if present.
	 * @return the matching instance if present, or <code>null</code> if not present.
	 */
	public Location getLocation(String displayId) {
		return locations.get(createCompliantURI(this.getPersistentIdentity().toString(),displayId,this.getVersion()));
	}
	
	/**
	 * Returns the instance matching the specified URI from the list of locations, if present.
	 * @return the matching instance if present, or <code>null</code> if not present.
	 */
	public Location getLocation(URI locationURI) {
		return locations.get(locationURI);
	}
	
	/**
	 * Returns the list of location instances owned by this instance. 
	 * @return the list of location instances owned by this instance.
	 */
	public Set<Location> getLocations() {
		return new HashSet<>(locations.values());
	}
	
	/**
	 * Removes all entries of the list of locations owned by this instance. The list will be empty after this call returns.
	 */
	public void clearLocations() {
		if (sbolDocument!=null) sbolDocument.checkReadOnly();
		Object[] valueSetArray = locations.values().toArray();
		for (Object location : valueSetArray) {
			removeLocation((Location)location);
		}
	}
		
	/**
	 * Clears the existing list of location instances, then appends all of the elements in the specified collection to the end of this list.
	 */
	void setLocations(
			List<Location> locations) {
		clearLocations();	
		if (locations==null) return;
		for (Location location : locations) {
			addLocation(location);
		}
	}

	/*
	public void addRange(int start,int end,OrientationType orientation) {
		if (sbolDocument!=null) sbolDocument.checkReadOnly();
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
	
	void removeRange(Range range) {
		if (sbolDocument!=null) sbolDocument.checkReadOnly();
		if (location instanceof MultiRange) {
			try {
				((MultiRange)location).removeRange(range);
			} catch (Exception e) {
				Set<Range> ranges = ((MultiRange)location).getRanges();
				if (ranges.size()!=2) {
					throw new IllegalArgumentException("Sequence annotation " + this.getIdentity() + 
							" is required to have a location.");
				}
				for (Range otherRange : ranges) {
					if (otherRange.getIdentity().equals(range)) continue;
					location = new Range(URIcompliance.createCompliantURI(this.getPersistentIdentity().toString(), 
							"range", this.getVersion()),otherRange.getStart(),otherRange.getEnd());
					if (otherRange.isSetOrientation()) {
						((Range)location).setOrientation(otherRange.getOrientation());
					}
				}
			}
		}
	}
	*/
		
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
		if (sbolDocument!=null) sbolDocument.checkReadOnly();
		URI componentURI = URIcompliance.createCompliantURI(componentDefinition.getPersistentIdentity().toString(), 
				component, componentDefinition.getVersion());
		setComponent(componentURI);
	}

	/**
	 * Sets field variable <code>component</code> to the specified element.
	 * @param componentURI
	 */
	public void setComponent(URI componentURI) {
		if (sbolDocument!=null) sbolDocument.checkReadOnly();
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
		if (sbolDocument!=null) sbolDocument.checkReadOnly();
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
		result = prime * result + ((locations == null) ? 0 : locations.hashCode());
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
		if (locations == null) {
			if (other.locations != null)
				return false;
		} else if (!locations.equals(other.locations))
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
	void updateCompliantURI(String URIprefix, String displayId, String version) {
		this.setWasDerivedFrom(this.getIdentity());
		this.setIdentity(createCompliantURI(URIprefix,displayId,version));
		this.setPersistentIdentity(createCompliantURI(URIprefix,displayId,""));
		this.setDisplayId(displayId);
		this.setVersion(version);
		String componentId = extractDisplayId(component);
		this.setComponent(createCompliantURI(URIprefix,componentId,version));
		int count = 0;
		for (Location location : this.getLocations()) {
			if (!location.isSetDisplayId()) location.setDisplayId("location"+ ++count);
			location.updateCompliantURI(this.getPersistentIdentity().toString(),location.getDisplayId(),version);
			this.removeChildSafely(location, this.locations);
			this.addLocation(location);
		}
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

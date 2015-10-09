package org.sbolstandard.core2;

import static org.sbolstandard.core2.URIcompliance.createCompliantURI;
import static org.sbolstandard.core2.URIcompliance.extractDisplayId;

import java.net.URI;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
	
	/**
	 * Creates a GenericLocation instance with the given arguments, 
	 * and then adds to this SequenceAnnotation object's list of locations.
	 * <p>
	 * This method creates the GenericLocation instance's identity URI with the persistent identity of this
	 * SequenceAnnotation object, the given {@code displayId} of the GenericLocation instance. 
	 *  
	 * @param displayId
	 */
	public void addGenericLocation(String displayId) {
		URI identity = createCompliantURI(this.getPersistentIdentity().toString(),displayId,this.getVersion());
		GenericLocation genericLocation = new GenericLocation(identity);
		addLocation(genericLocation);
	}
	
	/**
	 * Creates a GenericLocation instance with the given arguments and then adds to this SequenceAnnotation object's
	 * list of locations.
	 * <p>
	 * This method creates the GenericLocation instance's identity URI with the persistent identity of this
	 * SequenceAnnotation object, the given {@code displayId} of the GenericLocation instance. 
	 * The orientation property
	 * of the created GenericLocation instance is set to the given {@code orientation}.
	 *  
	 * @param displayId
	 * @param orientation
 	 */
	public void addGenericLocation(String displayId,OrientationType orientation) {
		URI identity = createCompliantURI(this.getPersistentIdentity().toString(),displayId,this.getVersion());
		GenericLocation genericLocation = new GenericLocation(identity);
		genericLocation.setOrientation(orientation);
		addLocation(genericLocation);
	}
	
	/**
	 * Creates a Cut instance with the given arguments and then adds to this SequenceAnnotation object's
	 * list of locations.
	 * <p>
	 * This method creates the Cut instance's identity URI with the persistent identity of this
	 * SequenceAnnotation object, the given {@code displayId} of the Cut instance. 
	 *  
	 * @param displayId
	 * @param at
	 */
	public void addCut(String displayId,int at) {
		URI identity = createCompliantURI(this.getPersistentIdentity().toString(),displayId,this.getVersion());
		Cut cut = new Cut(identity,at);
		addLocation(cut);
	}
	
	/**
	 * Creates a Cut instance with the given arguments and then adds to this SequenceAnnotation object's
	 * list of locations.
	 * <p>
	 * This method creates the Cut instance's identity URI with the persistent identity of this
	 * SequenceAnnotation object, the given {@code displayId} of the Cut instance. The orientation property
	 * of the created Cut instance is set to the given {@code orientation}.
	 *  
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

	/**
	 * Creates a Range instance with the given arguments and then adds to this SequenceAnnotation object's
	 * list of locations.
	 * <p>
	 * This method creates the Range instance's identity URI with the persistent identity of this
	 * SequenceAnnotation object, the given {@code displayId} of the Range instance. 
	 * 
	 * @param displayId
	 * @param start
	 * @param end
	 */
	public void addRange(String displayId,int start,int end) {
		URI identity = createCompliantURI(this.getPersistentIdentity().toString(),displayId,this.getVersion());
		Range range = new Range(identity,start,end);
		addLocation(range);
	}
	
	/**
 	 * Creates a Range instance with the given arguments and then adds to this SequenceAnnotation object's
	 * list of locations.
	 * <p>
	 * This method creates the Range instance's identity URI with the persistent identity of this
	 * SequenceAnnotation object, the given {@code displayId} of the Range instance. The orientation property
	 * of the created Range instance is set to the given {@code orientation}.
	 * 
	 * @param displayId
	 * @param start
	 * @param end
	 * @param orientation
	 */
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
	 * Removes the given Location instance from the list of Location
	 * instances.
	 * <p>
	 * If this SequenceAnnotation object belongs to an SBOLDocument instance, then
	 * the SBOLDcouement instance is checked for compliance first. 
	 * Only a compliant SBOLDocument instance is allowed to be edited.
	 * 
	 * @param location
	 * @return {@code true} if the matching Location instance is removed successfully, {@code false} otherwise.
	 * @throws SBOLValidationException if the associated SBOLDocument is not compliant.
	 */	
	public boolean removeLocation(Location location) {
		if (sbolDocument!=null) sbolDocument.checkReadOnly();
		if (locations.size()==1 && locations.containsValue(location)) {
			throw new IllegalArgumentException("Sequence annotation " + this.getIdentity() + " must have at least one location.");
		}
		return removeChildSafely(location,locations);
	}
	
	/**
	 * Returns the Location instance owned by this SequenceAnnotation object 
	 * that matches the given {@code displayId}
	 * 
	 * @param displayId
	 * @return the matching Location instance
	 */
	public Location getLocation(String displayId) {
		return locations.get(createCompliantURI(this.getPersistentIdentity().toString(),displayId,this.getVersion()));
	}
	
	/**
	 * Returns the Location instance owned by this SequenceAnnotation object 
	 * that matches the given {@code displayId}
	 * 
	 * @param locationURI
	 * @return the matching Location instance URI
	 */
	public Location getLocation(URI locationURI) {
		return locations.get(locationURI);
	}
	
	/**
	 * Returns the set of Location instances referenced by this SequenceAnnotation object.
	 * 
	 * @return the set of Location instances referenced by this SequenceAnnotation object.
	 */
	public Set<Location> getLocations() {
		return new HashSet<>(locations.values());
	}

	/**
	 * Removes all entries of this SequenceAnnotation object's list of Location
	 * instances. The set will be empty after this call returns.
  	 * <p>
	 * If this SequenceAnnotation object belongs to an SBOLDocument instance,
	 * then the SBOLDcouement instance is checked for compliance first. 
	 * Only a compliant SBOLDocument instance is allowed to be edited.
	 * 
	 * @throws SBOLValidationException if the associated SBOLDocument is not compliant  
	 */
	void clearLocations() {
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
	 * Test if the reference Component instance is set.
	 *  
	 * @return {@code true} if it refers to a Component instance; {@code false} otherwise.
	 */
	public boolean isSetComponent() {
		return component != null;
	}

	/**
	 * Returns the Component URI that this SequenceAnnotation object refers to.
	 * 
	 * @return the Component URI that this SequenceAnnotation object refers to
	 */
	public URI getComponentURI() {
		return component;
	}

	/**
	 * Returns the Component instance this SequenceAnnotation object refers to.
	 * 
	 * @return the Component instance this SequenceAnnotation object refers to,
	 * if the associated ComponentDefinition instance is not {@code null}, 
	 * or {@code null} otherwise 
	 */
	public Component getComponent() {
		if (componentDefinition==null) return null;
		return componentDefinition.getComponent(component);
	}
	
	/**
	 * Sets the reference Component URI to the URI of the Component instance matching the 
	 * given {@code displayId}.
	 * <p>
	 * If this SequenceAnnotation object belongs to an SBOLDocument instance, then
	 * the SBOLDcouement instance is checked for compliance first. Only a compliant SBOLDocument instance
	 * is allowed to be edited.
	 * <p>
	 * This method creates a compliant URI for the reference Component instance using the
	 * persistent identity of this SequenceAnnotation object's parent ComponentDefinition instance,
	 * the given {@code displayId}, and the parent ComponentDefinition instance's version.
	 * It then calls {@link #setComponent(URI)} to set the reference.
	 * 
	 * @param displayId
 	 * @throws SBOLValidationException if the associated SBOLDocument is not compliant
	 * @throws IllegalArgumentException if the associated ComponentDefinition object is not {@code null},
	 * and the given {@code componentURI} does not exist in its associated ComponentDefinition object's
	 * list of Component instances.
	 */
	public void setComponent(String displayId) {
		if (sbolDocument!=null) sbolDocument.checkReadOnly();
		URI componentURI = URIcompliance.createCompliantURI(componentDefinition.getPersistentIdentity().toString(), 
				displayId, componentDefinition.getVersion());
		if (sbolDocument!=null && sbolDocument.isCreateDefaults() && componentDefinition!=null &&
				componentDefinition.getComponent(componentURI)==null) {
			componentDefinition.createComponent(displayId,AccessType.PUBLIC,displayId,"");
		}
		setComponent(componentURI);
	}

	/**
	 * Sets the reference component URI to the given {@code componentURI}.
	 * <p>
	 * If this SequenceAnnotation object belongs to an SBOLDocument instance, then
	 * the SBOLDcouement instance is checked for compliance first. Only a compliant SBOLDocument instance
	 * is allowed to be edited.
	 * 
	 * @param componentURI
 	 * @throws SBOLValidationException if the associated SBOLDocument is not compliant
	 * @throws IllegalArgumentException if the associated ComponentDefinition object is not {@code null},
	 * and the given {@code componentURI} does not exist in its associated ComponentDefinition object's
	 * list of Component instances.
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
	 * Dereference the component URI by setting it to {@code null}.
	 * <p>
	 * If this SequenceAnnotation object belongs to an SBOLDocument instance, then
	 * the SBOLDcouement instance is checked for compliance first. Only a compliant SBOLDocument instance
	 * is allowed to be edited.
	 * 
	 * @throws SBOLValidationException if the associated SBOLDocument is not compliant
	 */
	public void unsetComponent() {
		if (sbolDocument!=null) sbolDocument.checkReadOnly();
		component = null;
	}

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
		if (!this.getIdentity().equals(createCompliantURI(URIprefix,displayId,version))) {
			this.setWasDerivedFrom(this.getIdentity());
		}
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
	 * Get the component definition for the component annotated by this sequence annotation.
	 * @return the component definition annotated by this sequence annotation.
	 */
	public ComponentDefinition getComponentDefinition() {
		if (componentDefinition!=null) {
			return componentDefinition.getComponent(component).getDefinition();
		}
		return null;
	}

	/**
	 * @param componentDefinition
	 */
	void setComponentDefinition(ComponentDefinition componentDefinition) {
		this.componentDefinition = componentDefinition;
	}

	@Override
	public String toString() {
		return "SequenceAnnotation [locations=" + locations + ", component=" + component
				+ ", componentDefinition=" + componentDefinition + "]";
	}
}

package org.sbolstandard.core2;

import static org.sbolstandard.core2.URIcompliance.createCompliantURI;
import static org.sbolstandard.core2.URIcompliance.extractDisplayId;

import java.net.URI;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Represents the SBOL SequenceAnnotation data model.
 * 
 * @author Zhen Zhang
 * @author Nicholas Roehner
 * @author Chris Myers
 * @version 2.1
 */

public class SequenceAnnotation extends Identified implements Comparable<SequenceAnnotation> {

	private HashMap<URI, Location> locations;
	private URI component;
	private Set<URI> roles;
	private RoleIntegrationType roleIntegration;
	private ComponentDefinition componentDefinition = null;
	
	/**
	 * @param identity
	 * @param locations
	 * @throws SBOLValidationException if either of the following condition is satisfied:
	 * <li>an SBOL validation rule violation occurred in {@link Identified#Identified(URI)}</li>
	 * <li>an SBOL validation rule violation occurred in {@link #setLocations(Set)}</li>
	 */
	SequenceAnnotation(URI identity, Set<Location> locations) throws SBOLValidationException {
		super(identity);
		this.locations = new HashMap<>();
		this.setLocations(locations);	
		this.roles = new HashSet<>();
	}
	
	private SequenceAnnotation(SequenceAnnotation sequenceAnnotation) throws SBOLValidationException {
		super(sequenceAnnotation);
		this.locations = new HashMap<>();
		for (Location location : sequenceAnnotation.getLocations()) {
			addLocation(location.deepCopy());
		}
		if (sequenceAnnotation.isSetComponent()) {
			this.setComponent(sequenceAnnotation.getComponentURI());
		}
		this.roles = new HashSet<>();
	}
	
	/**
	 * Creates a GenericLocation instance with the given arguments, 
	 * and then adds to this SequenceAnnotation instance's list of locations.
	 * <p>
	 * This method creates the GenericLocation instance's identity URI with the persistent identity of this
	 * SequenceAnnotation instance, the given {@code displayId} of the GenericLocation instance. 
	 *  
	 * @param displayId the  display ID used to construct the identity of the generic location instance
	 * @return the created generic location instance
	 * @throws SBOLValidationException see {@link SBOLValidationException}
	 */
	public GenericLocation addGenericLocation(String displayId) throws SBOLValidationException {
		URI identity = createCompliantURI(this.getPersistentIdentity().toString(),displayId,this.getVersion());
		GenericLocation genericLocation = new GenericLocation(identity);
		genericLocation.setPersistentIdentity(createCompliantURI(this.getPersistentIdentity().toString(),displayId,""));
		genericLocation.setDisplayId(displayId);
		genericLocation.setVersion(this.getVersion());
		addLocation(genericLocation);
		return genericLocation;
	}
	
	/**
	 * Creates a GenericLocation instance with the given arguments and then adds to this SequenceAnnotation instance's
	 * list of locations.
	 * <p>
	 * This method creates the GenericLocation instance's identity URI with the persistent identity of this
	 * SequenceAnnotation instance, the given {@code displayId} of the GenericLocation instance. 
	 * The orientation property
	 * of the created GenericLocation instance is set to the given {@code orientation}.
	 *  
	 * @param displayId the  display ID used to construct the identity of the generic location instance 
	 * @param orientation the orientation property for the 
	 * @return the created generic location instance
	 * @throws SBOLValidationException see {@link SBOLValidationException} 
 	 */
	public GenericLocation addGenericLocation(String displayId,OrientationType orientation) throws SBOLValidationException {
		URI identity = createCompliantURI(this.getPersistentIdentity().toString(),displayId,this.getVersion());
		GenericLocation genericLocation = new GenericLocation(identity);
		genericLocation.setPersistentIdentity(createCompliantURI(this.getPersistentIdentity().toString(),displayId,""));
		genericLocation.setDisplayId(displayId);
		genericLocation.setVersion(this.getVersion());
		genericLocation.setOrientation(orientation);
		addLocation(genericLocation);
		return genericLocation;
	}
	
	/**
	 * Creates a Cut instance with the given arguments and then adds to this SequenceAnnotation instance's
	 * list of locations.
	 * <p>
	 * This method creates the Cut instance's identity URI with the persistent identity of this
	 * SequenceAnnotation instance, the given {@code displayId} of the Cut instance. 
	 *  
	 * @param displayId the display ID for the Cut instance 
	 * @param at the at property for the created Cut instance 
	 * @return the created cut instance
	 * @throws SBOLValidationException see {@link SBOLValidationException} 
	 */
	public Cut addCut(String displayId,int at) throws SBOLValidationException {
		URI identity = createCompliantURI(this.getPersistentIdentity().toString(),displayId,this.getVersion());
		Cut cut = new Cut(identity,at);
		cut.setPersistentIdentity(createCompliantURI(this.getPersistentIdentity().toString(),displayId,""));
		cut.setDisplayId(displayId);
		cut.setVersion(this.getVersion());
		addLocation(cut);
		return cut;
	}
	
	/**
	 * Creates a Cut instance with the given arguments and then adds to this SequenceAnnotation instance's
	 * list of locations.
	 * <p>
	 * This method creates the Cut instance's identity URI with the persistent identity of this
	 * SequenceAnnotation instance, the given {@code displayId} of the Cut instance. The orientation property
	 * of the created Cut instance is set to the given {@code orientation}.
	 *  
	 * @param displayId the display ID for the Cut instance 
	 * @param at the at property for the created Cut instance
	 * @param orientation the orientation type
	 * @return the created cut instance
	 * @throws SBOLValidationException see {@link SBOLValidationException} 
	 */
	public Cut addCut(String displayId,int at,OrientationType orientation) throws SBOLValidationException {
		URI identity = createCompliantURI(this.getPersistentIdentity().toString(),displayId,this.getVersion());
		Cut cut = new Cut(identity,at);
		cut.setPersistentIdentity(createCompliantURI(this.getPersistentIdentity().toString(),displayId,""));
		cut.setDisplayId(displayId);
		cut.setVersion(this.getVersion());
		cut.setOrientation(orientation);
		addLocation(cut);
		return cut;
	}

	/**
	 * Creates a Range instance with the given arguments and then adds to this SequenceAnnotation instance's
	 * list of locations.
	 * <p>
	 * This method creates the Range instance's identity URI with the persistent identity of this
	 * SequenceAnnotation instance, the given {@code displayId} of the Range instance. 
	 * 
  	 * @param displayId the display ID for the Range instance
	 * @param start the start index of the Range instance
	 * @param end the end index of the Range instance
	 * @return the created Range instance
	 * @throws SBOLValidationException see {@link SBOLValidationException} 
	 */
	public Range addRange(String displayId,int start,int end) throws SBOLValidationException {
		URI identity = createCompliantURI(this.getPersistentIdentity().toString(),displayId,this.getVersion());
		Range range = new Range(identity,start,end);
		range.setPersistentIdentity(createCompliantURI(this.getPersistentIdentity().toString(),displayId,""));
		range.setDisplayId(displayId);
		range.setVersion(this.getVersion());
		addLocation(range);
		return range;
	}
	
	/**
 	 * Creates a Range instance with the given arguments and then adds to this SequenceAnnotation instance's
	 * list of locations.
	 * <p>
	 * This method creates the Range instance's identity URI with the persistent identity of this
	 * SequenceAnnotation instance, the given {@code displayId} of the Range instance. The orientation property
	 * of the created Range instance is set to the given {@code orientation}.
	 * 
 	 * @param displayId the display ID for the Range instance
	 * @param start the start index of the Range instance
	 * @param end the end index of the Range instance
	 * @param orientation the oritentation type
 	 * @return the range created
	 * @throws SBOLValidationException see {@link SBOLValidationException} 
	 */
	public Range addRange(String displayId,int start,int end,OrientationType orientation) throws SBOLValidationException {
		URI identity = createCompliantURI(this.getPersistentIdentity().toString(),displayId,this.getVersion());
		Range range = new Range(identity,start,end);
		range.setPersistentIdentity(createCompliantURI(this.getPersistentIdentity().toString(),displayId,""));
		range.setDisplayId(displayId);
		range.setVersion(this.getVersion());
		range.setOrientation(orientation);
		addLocation(range);
		return range;
	}
	
	/**
	 * @param location
	 * @throws SBOLValidationException if an SBOL validation rule violation occurred in {@link Identified#addChildSafely(Identified, java.util.Map, String, java.util.Map...)}
	 */
	void addLocation(Location location) throws SBOLValidationException {
		addChildSafely(location, locations, "location");
		location.setSBOLDocument(this.sbolDocument);
	}
	
	/**
	 * Removes the given Location instance from the list of Location
	 * instances.
	 * <p>
	 * If this SequenceAnnotation instance belongs to an SBOLDocument instance, then
	 * the SBOLDcouement instance is checked for compliance first. 
	 * Only a compliant SBOLDocument instance is allowed to be edited.
	 * 
	 * @param location the Location instance
	 * @return {@code true} if the matching Location instance is removed successfully, {@code false} otherwise.
	 * @throws SBOLValidationException if the following SBOL validation rule was violated: 10902
	 */	
	public boolean removeLocation(Location location) throws SBOLValidationException {
		if (locations.size()==1 && locations.containsValue(location)) {
			throw new SBOLValidationException("sbol-10902", this);
		}
		return removeChildSafely(location,locations);
	}
	
	/**
	 * Returns the Location instance owned by this SequenceAnnotation instance 
	 * that matches the given {@code displayId}
	 * 
	 * @param displayId the display ID of the Location instance to be returned 
	 * @return the matching Location instance
	 */
	public Location getLocation(String displayId) {
		try {
			return locations.get(createCompliantURI(this.getPersistentIdentity().toString(),displayId,this.getVersion()));
		}
		catch (SBOLValidationException e) {
			return null;
		}
	}
	
	/**
	 * Returns the Location instance owned by this SequenceAnnotation instance 
	 * that matches the given location URI.
	 * 
	 * @param locationURI the URI identity of 
	 * @return the matching Location instance URI
	 */
	public Location getLocation(URI locationURI) {
		return locations.get(locationURI);
	}
	
	/**
	 * Returns the set of Location instances referenced by this SequenceAnnotation instance.
	 * 
	 * @return the set of Location instances referenced by this SequenceAnnotation instance.
	 */
	public Set<Location> getLocations() {
		return new HashSet<>(locations.values());
	}
	
	/**
	 * Returns a sorted list of Locations referenced by this SequenceAnnotation instance.
	 * 
	 * @return a sorted list of Locations referenced by this SequenceAnnotation instance.
	 */
	public List<Location> getSortedLocations() {
		List<Location> sortedLocations = new ArrayList<Location>();
		sortedLocations.addAll(this.getLocations());
		Collections.sort(sortedLocations);
		return sortedLocations;
	}

	/**
	 * Removes all entries of this SequenceAnnotation instance's list of Location
	 * instances. The set will be empty after this call returns.
  	 * <p>
	 * If this SequenceAnnotation instance belongs to an SBOLDocument instance,
	 * then the SBOLDcouement instance is checked for compliance first. 
	 * Only a compliant SBOLDocument instance is allowed to be edited.
	 * 
	 * @throws SBOLValidationException if an SBOL validation rule violation occurred in {@link #removeLocation(Location)}.
	 */
	void clearLocations() throws SBOLValidationException {
		Object[] valueSetArray = locations.values().toArray();
		for (Object location : valueSetArray) {
			removeLocation((Location)location);
		}
	}
		
	/**
	 * Clears the existing list of location instances first, then appends all of the elements in the specified collection to the end of this list.
	 * @throws SBOLValidationException if any of the following condition is satisfied:
	 * <li>the following SBOL validation rule was violated: 10902;</li>
	 * <li>an SBOL validation rule violation occurred in {@link #clearLocations()}; or </li>
	 * <li>an SBOL validation rule violation occurred in {@link #addLocation(Location)}.</li>
	 */
	void setLocations(Set<Location> locations) throws SBOLValidationException {
		clearLocations();	
		if (locations==null || locations.size()==0) {
			throw new SBOLValidationException("sbol-10902", this);
		}
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
					throw new SBOLValidationException("Sequence annotation " + this.getIdentity() + 
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
	 * Returns the Component URI that this SequenceAnnotation instance refers to.
	 * 
	 * @return the Component URI that this SequenceAnnotation instance refers to
	 */
	public URI getComponentURI() {
		return component;
	}

	/**
	 * Returns the Component instance this SequenceAnnotation instance refers to.
	 * 
	 * @return the Component instance this SequenceAnnotation instance refers to,
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
	 * If this SequenceAnnotation instance belongs to an SBOLDocument instance, then
	 * the SBOLDcouement instance is checked for compliance first. Only a compliant SBOLDocument instance
	 * is allowed to be edited.
	 * <p>
	 * This method creates a compliant URI for the reference Component instance using the
	 * persistent identity of this SequenceAnnotation instance's parent ComponentDefinition instance,
	 * the given {@code displayId}, and the parent ComponentDefinition instance's version.
	 * It then calls {@link #setComponent(URI)} to set the reference.
	 * 
	 * @param displayId the given display ID for the referenced component
 	 * @throws SBOLValidationException see {@link SBOLValidationException}
	 */
	public void setComponent(String displayId) throws SBOLValidationException {
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
	 * If this SequenceAnnotation instance belongs to an SBOLDocument instance, then
	 * the SBOLDcouement instance is checked for compliance first. Only a compliant SBOLDocument instance
	 * is allowed to be edited.
	 * 
	 * @param componentURI the given component identity URI
 	 * @throws SBOLValidationException see {@link SBOLValidationException}
	 */
	public void setComponent(URI componentURI) throws SBOLValidationException {
		if (componentDefinition!=null) {
			if (componentDefinition.getComponent(componentURI)==null) {
				throw new SBOLValidationException("sbol-10905",this);
				
			}
			for (SequenceAnnotation sa : componentDefinition.getSequenceAnnotations()) {
				if (sa.getIdentity().equals(this.getIdentity())) continue;
				if (sa.isSetComponent() && sa.getComponentURI().equals(componentURI)) {
					throw new SBOLValidationException("sbol-10522", this);
				}
			}
		}
		this.component = componentURI;
	}
	
	/**
	 * Dereference the component URI by setting it to {@code null}.
	 * <p>
	 * If this SequenceAnnotation instance belongs to an SBOLDocument instance, then
	 * the SBOLDcouement instance is checked for compliance first. Only a compliant SBOLDocument instance
	 * is allowed to be edited.
	 */
	public void unsetComponent() {
		component = null;
	}
	
	/**
	 * Adds the given role URI to this sequence annotation's set of role URIs.
	 *
	 * @param roleURI the role URI to be added
	 * @return {@code true} if this set did not already contain the specified role, {@code false} otherwise.
	 */
	public boolean addRole(URI roleURI) {
		return roles.add(roleURI);
	}

	/**
	 * Removes the given role URI from the set of roles.
	 *
	 * @param roleURI the given role URI to be removed
	 * @return {@code true} if the matching role reference was removed successfully, {@code false} otherwise.
	 */
	public boolean removeRole(URI roleURI) {
		return roles.remove(roleURI);
	}

	/**
	 * Clears the existing set of roles first, and then adds the given
	 * set of the roles to this sequence annotation.
	 *
	 * @param roles the set of roles to be set
	 */
	public void setRoles(Set<URI> roles) {
		clearRoles();
		if (roles==null) return;
		for (URI role : roles) {
			addRole(role);
		}
	}

	/**
	 * Returns the set of role URIs owned by this sequence annotation. 
	 *
	 * @return the set of role URIs owned by this sequence annotation.
	 */
	public Set<URI> getRoles() {
		Set<URI> result = new HashSet<>();
		result.addAll(roles);
		return result;
	}

	/**
	 * Checks if the given role URI is included in this sequence annotation's set of role URIs.
	 *
	 * @param roleURI the role URI to be checked
	 * @return {@code true} if this set contains the given role URI, {@code false} otherwise.
	 */
	public boolean containsRole(URI roleURI) {
		return roles.contains(roleURI);
	}

	/**
	 * Removes all entries of this sequence annotation's set of role URIs.
	 * The set will be empty after this call returns.	 
	 */
	public void clearRoles() {
		roles.clear();
	}
	
	/**
	 * Test if the roleIntegration property is set.
	 * @return {@code true} if it is not {@code null}
	 */
	public boolean isSetRoleIntegration() {
		return roleIntegration != null;
	}

	/**
	 * Returns the roleIntegration property of this object.
	 * @return the roleIntegration property of this object.
	 */
	public RoleIntegrationType getRoleIntegration() {
		return this.roleIntegration;
	}

	/**
	 * Sets the roleIntegration property of this object to the given one.
	 *
	 * @param roleIntegration indicates how role is to be integrated with related roles.
	 */
	public void setRoleIntegration(RoleIntegrationType roleIntegration) {
		this.roleIntegration = roleIntegration;
	}

	/**
	 * Sets the roleIntegration property of this object to {@code null}.
	 *
	 */
	public void unsetRoleIntegration() {
		roleIntegration = null;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((roles == null) ? 0 : roles.hashCode());
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
		if (roles == null) {
			if (other.roles != null)
				return false;
		} else if (!roles.equals(other.roles))
			return false;
		if (locations == null) {
			if (other.locations != null)
				return false;
		} else if (!locations.equals(other.locations))
			return false;
		return true;
	}

	@Override
	protected SequenceAnnotation deepCopy() throws SBOLValidationException {
		return new SequenceAnnotation(this);
	}

	/**
	 * Assume this SequenceAnnotation instance has compliant URI, and all given parameters have compliant forms.
	 * This method is called by {@link ComponentDefinition#copy(String, String, String)}.
	 * @param URIprefix
	 * @param parentDisplayId
	 * @param version
	 * @throws SBOLValidationException see {@link SBOLValidationException}
	 */
	void updateCompliantURI(String URIprefix, String displayId, String version) throws SBOLValidationException {
		if (!this.getIdentity().equals(createCompliantURI(URIprefix,displayId,version))) {
			this.setWasDerivedFrom(this.getIdentity());
		}
		this.setIdentity(createCompliantURI(URIprefix,displayId,version));
		this.setPersistentIdentity(createCompliantURI(URIprefix,displayId,""));
		this.setDisplayId(displayId);
		this.setVersion(version);
		if (component!=null) {
			String componentId = extractDisplayId(component);
			this.setComponent(createCompliantURI(URIprefix,componentId,version));
		}
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
		return "SequenceAnnotation ["
				+ "identity=" + identity 
				+ (this.isSetDisplayId()?", displayId=" + displayId:"") 
				+ (this.isSetName()?", name=" + name:"")
				+ (this.isSetDescription()?", description=" + description:"") 
				+ (roles.size()>0?", roles=" + roles:"")  
				+ ", locations=" + this.getLocations() 
				+ (this.isSetComponent()?", component=" + component:"")
				+ "]";
	}
	
	@Override
	public int compareTo(SequenceAnnotation sa) {
		List<Location> sortedLocations1 = this.getSortedLocations();
		List<Location> sortedLocations2 = sa.getSortedLocations();
		if (sortedLocations1.size()>0 && sortedLocations2.size()>0) {
			return sortedLocations1.get(0).compareTo(sortedLocations2.get(0));
		} 
		return 0;
	}
}

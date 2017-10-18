package org.sbolstandard.core2;

import static org.sbolstandard.core2.URIcompliance.createCompliantURI;

import java.net.URI;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import javax.xml.namespace.QName;

import org.joda.time.DateTime;

/**
 * Represents a Activity object in the SBOL data model.
 * 
 * @author Chris Myers
 * @version 2.2
 */

public class Activity extends TopLevel{

	private DateTime startedAtTime;
	private DateTime endedAtTime;
	private Set<URI> wasInformedBys;
	private HashMap<URI, Association> qualifiedAssociations;
	private HashMap<URI, Usage> qualifiedUsages;

	/**
	 * @param identity
	 * @throws SBOLValidationException if either of the following condition is satisfied: 
	 * <ul>
	 * <li>if an SBOL validation rule violation occurred in {@link TopLevel#TopLevel(URI)}, or</li>
	 * <li>the following SBOL validation rule was violated: XXXXX.</li>
	 * </ul>
	 */
	Activity(URI identity) throws SBOLValidationException {
		super(identity);
		startedAtTime = null;
		endedAtTime = null;
		wasInformedBys = new HashSet<>();
		qualifiedAssociations = new HashMap<>();
		qualifiedUsages = new HashMap<>();
	}

	/**
	 * @param genericTopLevel
	 * @throws SBOLValidationException if an SBOL validation rule violation occurred in
	 * the following constructor or method:
	 * <ul>
	 * <li>{@link TopLevel#TopLevel(TopLevel)}, or</li>
	 * <li>{@link #setRDFType(QName)}.</li>
	 * </ul>
	 */
	private Activity(Activity activity) throws SBOLValidationException {
		super(activity);
		//this.setRDFType(genericTopLevel.getRDFType());
	}
	
	void copy(Activity activity) throws SBOLValidationException {
		((TopLevel)this).copy((TopLevel)activity);
	}

	private Association createAssociation(URI identity, URI hadRole, URI agent) throws SBOLValidationException {
		Association association = new Association(identity, hadRole, agent);
		addAssociation(association);
		return association;
	}

	/**
	 * Creates a child association for this activity with the given arguments, 
	 * and then adds to this activity's list of associations.
	 * <p>
	 * This method first creates a compliant URI for the child association to be created. 
	 * This URI starts with this activity's persistent identity, 
	 * followed by the given display ID and ends with this activity's version. 
	 * 
	 * @param displayId the display ID for the association to be created
	 * @param hadRole 
	 * @param agent 
	 * @return the created association
	 * @throws SBOLValidationException if any of the following SBOL validation rules was violated:
	 * TODO
	 */
	public Association createAssociation(String displayId, URI hadRole, URI agent) throws SBOLValidationException {
		if (this.getSBOLDocument() != null && this.getSBOLDocument().isComplete()) {
//			if (this.getSBOLDocument().getAgent(agent)==null) {
//				// TODO
//				//throw new SBOLValidationException("sbol-10604",this);
//			}
		}
		String URIprefix = this.getPersistentIdentity().toString();
		String version = this.getVersion();
		Association a = createAssociation(createCompliantURI(URIprefix, displayId, version),hadRole,agent);
		a.setPersistentIdentity(createCompliantURI(URIprefix, displayId, ""));
		a.setDisplayId(displayId);
		a.setVersion(version);
		return a;
	}
	
	private void addAssociation(Association association) throws SBOLValidationException {
		association.setSBOLDocument(this.getSBOLDocument());
		//association.setActivity(this);
		// TODO: validation rules
		addChildSafely(association, qualifiedAssociations, "association", qualifiedUsages); 
	}

	private Usage createUsage(URI identity, URI entity, URI hadRole) throws SBOLValidationException {
		Usage usage = new Usage(identity, entity, hadRole);
		addUsage(usage);
		return usage;
	}

	/**
	 * Creates a child usage for this activity with the given arguments, 
	 * and then adds to this activity's list of usages.
	 * <p>
	 * This method first creates a compliant URI for the child usage to be created. 
	 * This URI starts with this activity's persistent identity, 
	 * followed by the given display ID and ends with this activity's version. 
	 * 
	 * @param displayId the display ID for the usage to be created
	 * @param entity 
	 * @param hadRole 
	 * @return the created usage
	 * @throws SBOLValidationException if any of the following SBOL validation rules was violated:
	 * TODO
	 */
	public Usage createUsage(String displayId, URI entity, URI hadRole) throws SBOLValidationException {
		if (this.getSBOLDocument() != null && this.getSBOLDocument().isComplete()) {
//			if (this.getSBOLDocument().getAgent(agent)==null) {
//				// TODO
//				//throw new SBOLValidationException("sbol-10604",this);
//			}
		}
		String URIprefix = this.getPersistentIdentity().toString();
		String version = this.getVersion();
		Usage u = createUsage(createCompliantURI(URIprefix, displayId, version),entity,hadRole);
		u.setPersistentIdentity(createCompliantURI(URIprefix, displayId, ""));
		u.setDisplayId(displayId);
		u.setVersion(version);
		return u;
	}
	
	private void addUsage(Usage usage) throws SBOLValidationException {
		usage.setSBOLDocument(this.getSBOLDocument());
		//association.setActivity(this);
		// TODO: validation rules
		addChildSafely(usage, qualifiedUsages, "usage", qualifiedAssociations); 
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((startedAtTime == null) ? 0 : startedAtTime.hashCode());
		result = prime * result + ((endedAtTime == null) ? 0 : endedAtTime.hashCode());
		result = prime * result + ((wasInformedBys == null) ? 0 : wasInformedBys.hashCode());
		result = prime * result + ((qualifiedAssociations == null) ? 0 : qualifiedAssociations.hashCode());
		result = prime * result + ((qualifiedUsages == null) ? 0 : qualifiedUsages.hashCode());
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
		Activity other = (Activity) obj;
		if (startedAtTime == null) {
			if (other.startedAtTime != null)
				return false;
		} else if (!startedAtTime.equals(other.startedAtTime))
			return false;
		if (endedAtTime == null) {
			if (other.endedAtTime != null)
				return false;
		} else if (!endedAtTime.equals(other.endedAtTime))
			return false;
		if (wasInformedBys == null) {
			if (other.wasInformedBys != null)
				return false;
		} else if (!wasInformedBys.equals(other.wasInformedBys))
			return false;
		if (qualifiedAssociations == null) {
			if (other.qualifiedAssociations != null)
				return false;
		} else if (!qualifiedAssociations.equals(other.qualifiedAssociations))
			return false;
		if (qualifiedUsages == null) {
			if (other.qualifiedUsages != null)
				return false;
		} else if (!qualifiedUsages.equals(other.qualifiedUsages))
			return false;
		return true;
	}
	
	

	/* (non-Javadoc)
	 * @see org.sbolstandard.core2.TopLevel#deepCopy()
	 */
	/**
	 * @throws SBOLValidationException if an SBOL validation rule was violated in 
	 * {@link #GenericTopLevel(Activity)}.
	 */
	@Override
	Activity deepCopy() throws SBOLValidationException {
		return new Activity(this);
	}

	/* (non-Javadoc)
	 * @see org.sbolstandard.core2.abstract_classes.TopLevel#copy(java.lang.String, java.lang.String, java.lang.String)
	 */
	/**
	 * @throws SBOLValidationException if an SBOL validation rule violation occurred in 
	 * any of the following methods:
	 * <ul>
	 * <li>{@link #deepCopy()},</li>
	 * <li>{@link URIcompliance#createCompliantURI(String, String, String)},</li>
	 * <li>{@link #setDisplayId(String)},</li>
	 * <li>{@link #setVersion(String)},</li>
	 * <li>{@link #setWasDerivedFrom(URI)}, or</li>
	 * <li>{@link #setIdentity(URI)}.</li>
	 * </ul>
	 */
	@Override
	Activity copy(String URIprefix, String displayId, String version) throws SBOLValidationException {
		Activity cloned = this.deepCopy();
		cloned.setPersistentIdentity(createCompliantURI(URIprefix,displayId,""));
		cloned.setDisplayId(displayId);
		cloned.setVersion(version);
		URI newIdentity = createCompliantURI(URIprefix,displayId,version);
		if (!this.getIdentity().equals(newIdentity)) {
			cloned.addWasDerivedFrom(this.getIdentity());
		} else {
			cloned.setWasDerivedFroms(this.getWasDerivedFroms());
		}
		cloned.setIdentity(newIdentity);
		return cloned;
	}

	/* (non-Javadoc)
	 * @see org.sbolstandard.core2.abstract_classes.TopLevel#checkDescendantsURIcompliance()
	 */
	@Override
	void checkDescendantsURIcompliance() {//throws SBOLValidationException {
		//URIcompliance.isTopLevelURIformCompliant(this.getIdentity());
	}

	/* (non-Javadoc)
	 * @see org.sbolstandard.core2.Identified#toString()
	 */
	@Override
	public String toString() {
		return "Activity ["
				+ super.toString()
				+ (this.isSetStartedAtTime()?", startedAtTime =" + startedAtTime:"")
				+ (this.isSetEndedAtTime()?", endedAtTime =" + endedAtTime:"")
				+ (qualifiedAssociations.size()>0?", qualifiedAssociations=" + qualifiedAssociations:"")	  
				+ (qualifiedUsages.size()>0?", qualifiedUsages=" + qualifiedUsages:"")	  
				+ (wasInformedBys.size()>0?", wasInformedBys=" + wasInformedBys:"")	  
				+ "]";
	}

	/**
	 * Test if the startedAtTime is set.
	 *
	 * @return {@code true} if it is not {@code null}, or {@code false} otherwise
	 */
	public boolean isSetStartedAtTime() {
		return startedAtTime != null;
	}

	/**
	 * @return the startedAtTime
	 */
	public DateTime getStartedAtTime() {
		return startedAtTime;
	}

	/**
	 * @param startedAtTime the startedAtTime to set
	 */
	public void setStartedAtTime(DateTime startedAtTime) {
		this.startedAtTime = startedAtTime;
	}
	
	/**
	 * Test if the endedAtTime is set.
	 *
	 * @return {@code true} if it is not {@code null}, or {@code false} otherwise
	 */
	public boolean isSetEndedAtTime() {
		return endedAtTime != null;
	}

	/**
	 * @return the endedAtTime
	 */
	public DateTime getEndedAtTime() {
		return endedAtTime;
	}

	/**
	 * @param endedAtTime the endedAtTime to set
	 */
	public void setEndedAtTime(DateTime endedAtTime) {
		this.endedAtTime = endedAtTime;
	}
	
	/**
	 * Adds the URI of the given Activity instance to this Activity's 
	 * set of wasInformdBy URIs. This method calls {@link #addWasInformedBy(URI)} with this Activity URI.
	 *
	 * @param activity the Activity instance whose identity URI to be added
	 * @return {@code true} if this set did not already contain the identity URI of the given Activity, {@code false} otherwise.
	 * @throws SBOLValidationException if the following SBOL validation rule was violated: ?????.
	 */
	public boolean addWasInformedBy(Activity activity) {
		if (this.getSBOLDocument() != null && this.getSBOLDocument().isComplete()) {
			//if (this.getSBOLDocument().getActivity(activity.getIdentity())==null) {
				// TODO
				//throw new SBOLValidationException("sbol-10513", sequence);
			//}
		}
		return this.addWasInformedBy(activity.getIdentity());
	}

	/**
	 * Adds the given activity URI to this Activity's set of wasInformedBy URIs.
	 *
	 * @param activityURI the identity URI of the Activity to be added
	 * @return {@code true} if this set did not already contain the given activity's URI, {@code false} otherwise.
	 * @throws SBOLValidationException if the following SBOL validation rule was violated: ?????. 
	 */
	public boolean addWasInformedBy(URI activityURI) {
		if (this.getSBOLDocument() != null && this.getSBOLDocument().isComplete()) {
			//if (this.getSBOLDocument().getActivity(activityURI)==null) {
				// TODO:
				//throw new SBOLValidationException("sbol-10513",this);
			//}
		}
		return wasInformedBys.add(activityURI);
	}

	/**
	 * Constructs a compliant activity URI with the given display ID and version, and then adds this URI
	 * to this activity's set of wasInformedBy URIs.
	 * <p>
	 * This method creates a compliant activity URI with the default
	 * URI prefix, which was set in the SBOLDocument instance hosting this activity, the given 
	 * display ID and version. It then calls {@link #addWasInformedBy(URI)} with this Activity URI.
	 *
	 * @param displayId the display ID of the activity whose identity URI is to be added
	 * @param version version of the activity whose identity URI is to be added
	 * @return {@code true} if this set did not already contain the given activity's URI, {@code false} otherwise. 
	 * @throws SBOLValidationException see {@link #addWasInformedBy(URI)} 
	 */
	public boolean addWasInformedBy(String displayId,String version) throws SBOLValidationException {
		URI activityURI = URIcompliance.createCompliantURI(this.getSBOLDocument().getDefaultURIprefix(),
				TopLevel.SEQUENCE, displayId, version, this.getSBOLDocument().isTypesInURIs());
		return addWasInformedBy(activityURI);
	}

	/**
	 * Constructs a compliant activity URI using the given activity display ID, and then adds this URI to 
	 * this activity's set of wasInformedBy URIs. This method calls {@link #addWasInformedBy(String, String)} with
	 * the given sequence display ID and an empty string as its version. 
	 *
	 * @param displayId the display ID of the activity whose identity URI is to be added
	 * @return {@code true} if this set did not already contain the given activity's URI, {@code false} otherwise.
	 * @throws SBOLValidationException see {@link #addWasInformedBy(String, String)}
	 */
	public boolean addWasInformedBy(String displayId) throws SBOLValidationException {
		return addWasInformedBy(displayId,"");
	}

	/**
	 * @return the wasInformedBy
	 */
	public Set<URI> getWasInformedBys() {
		return wasInformedBys;
	}

	/**
	 * @param wasInformedBys the wasInformedBys to set
	 */
	public void setWasInformedBys(Set<URI> wasInformedBys) {
		this.wasInformedBys = wasInformedBys;
	}
	
	/**
	 * Returns the qualifiedAssociation matching the given qualifiedAssociation's display ID.
	 * <p>
	 * This method first creates a compliant URI for the qualifiedAssociation to be retrieved. It starts with
	 * this activity's persistent identity, followed by the given qualifiedAssociation's display ID,
	 * and ends with this activity's version.
	 * 
	 * @param displayId the display ID of the qualifiedAssociation to be retrieved
	 * @return the matching qualifiedAssociation if present, or {@code null} otherwise.
	 */
	public Association getQualifiedAssociation(String displayId) {
		try {
			return qualifiedAssociations.get(createCompliantURI(this.getPersistentIdentity().toString(),displayId,this.getVersion()));
		}
		catch (SBOLValidationException e) {
			return null;
		}
	}

	/**
	 * Returns the instance matching the given qualifiedAssociation's identity URI.
	 *
	 * @param qualifiedAssociationURI the identity URI of the qualifiedAssociation to be retrieved
	 * @return the matching qualifiedAssociation if present, or {@code null} otherwise.
	 */
	public Association getQualifiedAssociation(URI qualifiedAssociationURI) {
		return qualifiedAssociations.get(qualifiedAssociationURI);
	}

	/**
	 * Returns the set of associations owned by this activity.
	 *
	 * @return the set of associations owned by this activity.
	 */
	public Set<Association> getQualifiedAssociations() {
		Set<Association> associations = new HashSet<>();
		associations.addAll(this.qualifiedAssociations.values());
		return associations;
	}
	
	/**
	 * Adds the given association to the list of qualifiedAssociations.
	 * @throws SBOLValidationException if either of the following condition is satisfied:
	 * <ul>
	 * <li>any of the following SBOL validation rules was violated: 10604, 10605, 10803</li>
	 * <li>an SBOL validation rule violation occurred in {@link Identified#addChildSafely(Identified, java.util.Map, String, java.util.Map...)}</li>
	 * </ul>
	 */
	private void addQualifiedAssociation(Association association) throws SBOLValidationException {
		association.setSBOLDocument(this.getSBOLDocument());
		addChildSafely(association, qualifiedAssociations, "qualifiedAssociation", qualifiedUsages);
	}
	
	/**
	 * Removes the given association from the list of qualifiedAssociations.
	 * 
	 * @param association the given association
	 * @return {@code true} if the matching association was removed successfully,
	 *         {@code false} otherwise.
	 */
	public boolean removeQualifiedAssociation(Association association) {
		return removeChildSafely(association, qualifiedAssociations);
	}

	/**
	 * Removes all entries of this activity's list of qualifiedAssociations.
	 * The list will be empty after this call returns.
	 * <p>
	 * This method calls {@link #removeQualifiedAssociation(Association association)} to iteratively remove
	 * each association.
	 *
	 */
	public void clearQualifiedAssociations() {
		Object[] valueSetArray = qualifiedAssociations.values().toArray();
		for (Object association : valueSetArray) {
			removeQualifiedAssociation((Association)association);
		}
	}
	
	/**
	 * @param associations the associations to set
	 */
	void setQualifiedAssociations(Set<Association> associations) throws SBOLValidationException {
		clearQualifiedAssociations();
		for (Association association : associations) {
			addQualifiedAssociation(association);
		}
	}

	/**
	 * Returns the qualifiedUsage matching the given qualifiedUsage's display ID.
	 * <p>
	 * This method first creates a compliant URI for the qualifiedUsage to be retrieved. It starts with
	 * this activity's persistent identity, followed by the given qualifiedUsage's display ID,
	 * and ends with this activity's version.
	 * 
	 * @param displayId the display ID of the qualifiedUsage to be retrieved
	 * @return the matching qualifiedUsage if present, or {@code null} otherwise.
	 */
	public Usage getQualifiedUsage(String displayId) {
		try {
			return qualifiedUsages.get(createCompliantURI(this.getPersistentIdentity().toString(),displayId,this.getVersion()));
		}
		catch (SBOLValidationException e) {
			return null;
		}
	}

	/**
	 * Returns the instance matching the given qualifiedUsage's identity URI.
	 *
	 * @param qualifiedUsageURI the identity URI of the qualifiedUsage to be retrieved
	 * @return the matching qualifiedUsage if present, or {@code null} otherwise.
	 */
	public Usage getQualifiedUsage(URI qualifiedUsageURI) {
		return qualifiedUsages.get(qualifiedUsageURI);
	}
	
	/**
	 * Returns the set of usages owned by this activity.
	 *
	 * @return the set of usages owned by this activity.
	 */
	public Set<Usage> getQualifiedUsages() {
		Set<Usage> usages = new HashSet<>();
		usages.addAll(this.qualifiedUsages.values());
		return usages;
	}
	
	/**
	 * Adds the given usage to the list of qualifiedUsages.
	 * @throws SBOLValidationException if either of the following condition is satisfied:
	 * <ul>
	 * <li>any of the following SBOL validation rules was violated: 10604, 10605, 10803</li>
	 * <li>an SBOL validation rule violation occurred in {@link Identified#addChildSafely(Identified, java.util.Map, String, java.util.Map...)}</li>
	 * </ul>
	 */
	private void addQualifiedUsage(Usage usage) throws SBOLValidationException {
		usage.setSBOLDocument(this.getSBOLDocument());
		addChildSafely(usage, qualifiedUsages, "qualifiedUsage", qualifiedAssociations);
	}
	
	/**
	 * Removes the given usage from the list of qualifiedUsages.
	 * 
	 * @param usage the given usage
	 * @return {@code true} if the matching usage was removed successfully,
	 *         {@code false} otherwise.
	 */
	public boolean removeQualifiedUsages(Usage usage) {
		return removeChildSafely(usage, qualifiedUsages);
	}
	
	/**
	 * Removes all entries of this activity's list of qualifiedUsages.
	 * The list will be empty after this call returns.
	 * <p>
	 * This method calls {@link #removeQualifiedUsage(Usage usage)} to iteratively remove
	 * each usage.
	 *
	 */
	public void clearQualifiedUsages() {
		Object[] valueSetArray = qualifiedUsages.values().toArray();
		for (Object usage : valueSetArray) {
			removeQualifiedUsages((Usage)usage);
		}
	}
	
	/**
	 * @param usages the usages to set
	 */
	void setQualifiedUsages(Set<Usage> usages) throws SBOLValidationException {
		clearQualifiedUsages();
		for (Usage usage : usages) {
			addQualifiedUsage(usage);
		}
	}

}

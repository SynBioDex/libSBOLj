package org.sbolstandard.core2.abstract_classes;

import java.net.URI;
import static org.sbolstandard.core2.util.UriCompliance.*;

public abstract class TopLevel extends Documented{
		
//	private static String persistentIdURI;
//	private static String URIprefix;
//	private static String displayIdURI;
//	private static String versionURI;

	public TopLevel(URI identity) {
		super(identity);
	}

//	public TopLevel(String authority, String id, String version) {
//		super(authority, id, version);
//	}

	protected TopLevel(TopLevel toplevel) {
		super(toplevel);
	}
		
	protected abstract TopLevel deepCopy();
	
	/**
	 * Make a copy of a top-level object whose URI and its descendants' URIs (children, grandchildren, etc) are all compliant. 
	 * It first makes a deep copy of this object, then updates its own identity URI and all of its descendants' identity URIs
	 * according to the given {@code URIprefix, displayId}, and {@code version}. This method also updates the {@code displayId}
	 * and {@code version} fields for each updated object.
	 * @param URIprefix
	 * @param displayId
	 * @param version
	 * @return the copied top-level object if this object and all of its descendants have compliant URIs, and {@code null} otherwise.
	 */
	public abstract TopLevel copy(String URIprefix, String displayId, String version);
	
	/**
	 * Check if this top-level object's and all of its descendants' URIs are all compliant. 
	 * @return {@code true} if they are all compliant, {@code false} otherwise.
	 */
	protected abstract boolean checkDescendantsURIcompliance();

//	/**
//	 * Get a deep copy of the object first, set its display ID to the specified value, and set the major version to "1" and minor version to "0".
//	 * @param displayId
//	 * @return the copied {@link Documented} instance.
//	 */
//	public TopLevel copy(String displayId) {
//		TopLevel cloned = this.deepCopy();
//		cloned.setWasDerivedFrom(this.getIdentity());
//		cloned.setDisplayId(displayId);		
//		return cloned;
//	}

//	/**
//	 * Get a deep copy the object first, increment the major version of the original object by 1,
//	 * and then set this value to the major version of the deepCopyd object. Set the minor version of the deepCopyd object to 0.
//	 * Update the URI of the deepCopyd object.
//	 * @param version
//	 * @return the copied object
//	 */
//
//	public TopLevel newVersion(String version) {
//		TopLevel cloned = this.deepCopy();
//		cloned.setWasDerivedFrom(this.getIdentity());
//		cloned.setVersion(version);
//		return cloned;
//	}
	
//	/**
//	 * @param newVersion
//	 */
//	protected void updateVersion(String newVersion) {
//		this.setVersion(newVersion);
//		if (isTopLevelURIcompliant(this.getIdentity())
//				&& isDisplayIdCompliant(newVersion)) {
//			String newURIStr = extractPersistentId(this.getIdentity()) + '/' + newVersion;			
//			URI newURI = URI.create(newURIStr);
//			this.setIdentity(newURI);			
//		}		
//	}
}

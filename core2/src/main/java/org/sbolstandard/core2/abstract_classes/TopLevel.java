package org.sbolstandard.core2.abstract_classes;

import java.net.URI;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.sbolstandard.core2.util.UriCompliance;

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
	 * Get a deep copy of the object first, set its display ID to the specified value, and set the major version to "1" and minor version to "0".
	 * @param displayId
	 * @return the copied {@link Documented} instance.
	 */
	public TopLevel copy(String displayId) {
		TopLevel cloned = this.deepCopy();
		cloned.setWasDerivedFrom(this.getIdentity());
		cloned.setDisplayId(displayId);		
		return cloned;
	}

	/**
	 * Get a deep copy the object first, increment the major version of the original object by 1,
	 * and then set this value to the major version of the deepCopyd object. Set the minor version of the deepCopyd object to 0.
	 * Update the URI of the deepCopyd object.
	 * @param version
	 * @return the copied object
	 */

	public TopLevel newVersion(String version) {
		TopLevel cloned = this.deepCopy();
		cloned.setWasDerivedFrom(this.getIdentity());
		cloned.setVersion(version);
		return cloned;
	}
	
	/**
	 * Replace the display ID in the object's URI with the specified one, and make the same replacement for all of its children objects.
	 * @param newDisplayId
	 */
	protected void updateDisplayId(String newDisplayId) {
		this.setDisplayId(newDisplayId);
		if (UriCompliance.isTopLevelURIcompliant(this.getIdentity())
				&& UriCompliance.isDisplayIdCompliant(newDisplayId)) {
			String newURIStr = UriCompliance.extractURIprefix(this.getIdentity())
								+ '/' + newDisplayId + '/' 
								+ UriCompliance.extractVersion(this.getIdentity());			
			URI newURI = URI.create(newURIStr);
			this.setIdentity(newURI);			
		}
	}
	
	/**
	 * @param newVersion
	 */
	protected void updateVersion(String newVersion) {
		this.setVersion(newVersion);
		if (UriCompliance.isTopLevelURIcompliant(this.getIdentity())
				&& UriCompliance.isDisplayIdCompliant(newVersion)) {
			String newURIStr = UriCompliance.extractPersistentId(this.getIdentity()) + '/' + newVersion;			
			URI newURI = URI.create(newURIStr);
			this.setIdentity(newURI);			
		}		
	}
}

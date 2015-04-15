package org.sbolstandard.core2.util;

import java.net.URI;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class UriCompliance {
			
	/**
	 * Extract the persistent identity URI from the given URI.
	 * The persistent identity is simply the identity URI without the version.
	 * @param objURI
	 * @return the extracted persistent identity URI, <code>null</code> otherwise.
	 */
	public static String extractPersistentId(URI objURI) {
		String URIstr = objURI.toString();
		Pattern r = Pattern.compile(genericURIpattern1);
		Matcher m = r.matcher(URIstr);
		if (m.matches()) {
			return m.group(1).substring(0, m.group(1).lastIndexOf('/')); // remove the '/' in the end.
		}			
		else {
			return null;
		}
			
	}
		
	/**
	 * Extract the URI prefix from this object's identity URI.
	 * @param objURI 
	 * @return the extracted URI prefix, <code>null</code> otherwise.
	 */
	public static String extractURIprefix(URI objURI) {
		String URIstr = objURI.toString();		
		Pattern r = Pattern.compile(genericURIpattern1);
		Matcher m = r.matcher(URIstr);
		if (m.matches())
			return m.group(2);
		else 
			return null;	
	}
	
	/**
	 * Extract the object's display ID from the given object's identity URI, according to the given index. 
	 * The given URI is first checked for compliance. If the given index is 0, the extracted display ID is the
	 * top-level object's display ID; if the given index is 1, the extracted display ID is the
	 * child object's display ID; if the given index is 2, the extracted display ID is the
	 * grand child object's display ID; if the given index is 3, the extracted display ID is the
	 * grand grand child object's display ID.
	 * @param objURI
	 * @param index
	 * @return the extracted display ID.
	 */
	public static String extractDisplayId(URI objURI, int index) {
		if (index < 0 || index > 3) {
			// TODO: generate error message
			return null;
		}
		String URIstr = objURI.toString();
		Pattern r = Pattern.compile(genericURIpattern2);
		Matcher m = r.matcher(URIstr);
		if (m.matches()) {			
			//System.out.println("group 1: " + m.group(1));
			String displayIds = m.group(1); // toplevelId/childId/grandChildId/grandGrandChildId/			
			String[] displayIdArray = displayIds.substring(0, displayIds.lastIndexOf('/')).split("/");
			return displayIdArray[index];
		}
		else 
			return null;	
	}

	/**
	 * Extract the version from this object's identity URI.
	 * @param objURI
	 * @return the version if the given URI is compliant, <code>null</code> otherwise. 
	 */
	public static String extractVersion(URI objURI) {		
		String URIstr = objURI.toString();
		Pattern r = Pattern.compile(genericURIpattern1);
		Matcher m = r.matcher(URIstr);
		if (m.matches())
			return m.group(3);
		else 
			return null;
	}
	
//	/**
//	 * Test if this object's identity URI is compliant with the form ⟨prefix⟩/⟨displayId⟩/⟨version⟩. 
//	 * The prefix is established by the owner of this top level object. 
//	 * The owner of a prefix must ensure that the displayIds for their top level SBOL objects are unique under the prefix.
//	 * Multiple versions of each object can exist, but they must each have a unique version at the end of their URI.
//	 * @return <code>true</code> if the identity URI is compliant, <code>false</code> otherwise. 
//	 */
//	public boolean isURIcompliant() {
//		String URIstr = this.getIdentity().toString();
//		// TODO: Need MavenVersionPattern or just a versionPattern?
//		String URIpattern = URIprefixpattern + "/" + displayIDpattern + "/" + versionPattern;
//		Pattern r = Pattern.compile(URIpattern);
//		Matcher m = r.matcher(URIstr);
//		if (m.matches()) {
//			return true;
//		}			
//		else {
//			return false;
//		}
//	}
	
	/**
	 * Test if the given top-level instance's identity URI is compliant with the form ⟨prefix⟩/⟨displayId⟩/⟨version⟩. 
	 * The prefix is established by the owner of this top level object. 
	 * The owner of a prefix must ensure that the displayIds for their top level SBOL objects are unique under the prefix.
	 * Multiple versions of each object can exist, but they must each have a unique version at the end of their URI.
	 * @param topLevelObjURI
	 * @return <code>true</code> if the identity URI is compliant, <code>false</code> otherwise. 
	 */
	 public static final boolean isTopLevelURIcompliant(URI topLevelObjURI) {
		String URIstr = topLevelObjURI.toString();		
		String URIpattern = URIprefixpattern + "/" + displayIDpattern + "/" + versionPattern;
		Pattern r = Pattern.compile(URIpattern);
		Matcher m = r.matcher(URIstr);
		if (m.matches()) {
			return true;
		}			
		else {
			return false;
		}		
	}
	 
	 /**
	 * @param parentURI
	 * @param childURI
	 * @return
	 */
	public static final boolean isChildURIcompliant(URI parentURI, URI childURI) {
		String parentURIstr = parentURI.toString();		
		Pattern URIpattern = Pattern.compile(genericURIpattern1);
		Matcher parentMatcher = URIpattern.matcher(parentURIstr);
		if (parentMatcher.matches()) {
			String childURIstr = childURI.toString();			
			Matcher childMatcher = URIpattern.matcher(childURIstr);
			if (childMatcher.matches()) {
				String parentPersistentId = parentMatcher.group(1).substring(0, parentMatcher.group(1).lastIndexOf('/')); // remove the '/' in the end.
				String childPersistentId = childMatcher.group(1).substring(0, childMatcher.group(1).lastIndexOf('/')); // remove the '/' in the end.
				// Extract the parent persistent ID from the child's persistent ID.
				// Only need to remove the child's own display ID part.
				String parentPartOfChildPersistId = childPersistentId.substring(0, childPersistentId.lastIndexOf('/'));
				if (parentPartOfChildPersistId.equals(parentPersistentId)) {
					return true;
				}
				else {
					// TODO: generate message: Parent persistent ID extracted from the child's persistent ID does not match parent's persistent ID.
					return false;
				}
			}
			else {
				// TODO: generate message: childURI is not compliant
				return false;
			}
		}
		else {
			// TODO: generate message: parentURI is not compliant
			return false;
		}
		
		
		
		// String parentPersistendtId = extractPersistentId(parentURI);
		
		
//		String topLevelURIstr = topLevelURI.toString();
//		String childURIstr = childURI.toString();
//		String topLevelURIpattern = "(" + URIprefixpattern + "/" + displayIDpattern + ")/" + versionPattern;
//		String childURIpattern = "(" + URIprefixpattern + "/" + displayIDpattern + ")/" + displayIDpattern + "/" + versionPattern;
//		Pattern topLevelPattern = Pattern.compile(topLevelURIpattern);
//		Matcher topLevelMatcher = topLevelPattern.matcher(topLevelURIstr);
//		Pattern childPattern = Pattern.compile(childURIpattern);
//		Matcher childMatcher = childPattern.matcher(childURIstr);
//		if (topLevelMatcher.matches() && childMatcher.matches()) {
//			if (topLevelMatcher.group(1).equals(childMatcher.group(1))) {
//				return true;
//			}
//			else
//				return false;
//		}
//		else {
//			return false;
//		}
	 }
	
	/**
	 * @param newDisplayId
	 * @return
	 */
	public static boolean isDisplayIdCompliant(String newDisplayId) {
		Pattern r = Pattern.compile(displayIDpattern);
		Matcher m = r.matcher(newDisplayId);
		if (m.matches()) {
			return true;
		}			
		else {
			return false;
		}
	}
	
	/**
	 * @param newVersion
	 * @return
	 */
	public static boolean isVersionCompliant(String newVersion) {
		Pattern r = Pattern.compile(versionPattern);
		Matcher m = r.matcher(newVersion);
		if (m.matches()) {
			return true;
		}			
		else {
			return false;
		}
	}
	
	// (?:...) is a non-capturing group
	public static final String URIprefixpattern = "\\b(?:https?|ftp|file)://[-a-zA-Z0-9+&@#%?=~_|!:,.;]*[-a-zA-Z0-9+&@#%=~_|]";
	//public static final String URIprefixpattern = "\\b(?:https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]"; 
	
	public static final String displayIDpattern = "[a-zA-Z_]+[a-zA-Z0-9_]+";//"[a-zA-Z0-9_]+";
	
	//public static final String MavenVersionPattern = "[0-9]+(?:[.][0-9]+){0,2}(?:-alpha|beta|SNAPSHOT)*";
	
	public static final String versionPattern = "[-a-zA-Z0-9.]+";
	
	// A URI can have up to 4 display IDs. The one with 4 display IDs can be ComponentDefinition -> SequenceAnnotation -> (Location) MultiRange -> Range.
	// group 1: persistent ID
	// group 2: URI prefix
	// group 3: version
	public static final String genericURIpattern1 = "((" + URIprefixpattern + ")/(?:" + displayIDpattern + "/){1,4})(" + versionPattern + ")";
	
	// A URI can have up to 4 display IDs. The one with 4 display IDs can be ComponentDefinition -> SequenceAnnotation -> (Location) MultiRange -> Range.
	// group 1: top-level display ID
	// group 2: top-level's child display ID
	// group 3: top-level's grand child display ID
	// group 4: top-level's grand grand child display ID
	public static final String genericURIpattern2 = URIprefixpattern + "/((?:" + displayIDpattern + "/){1,4})" + versionPattern;
	

}

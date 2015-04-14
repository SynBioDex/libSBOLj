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
		String URIpattern = "(" + URIprefixpattern + "/(?:" + displayIDpattern + "/)+)" + versionPattern;
		Pattern r = Pattern.compile(URIpattern);
		Matcher m = r.matcher(URIstr);
		if (m.find()) {
			return m.group(1).substring(0, m.group(1).lastIndexOf('/')); // remove the '/' in the end.
		}			
		else
			return null;
	}
		
	/**
	 * Extract the URI prefix from this object's identity URI.
	 * @param objURI 
	 * @return the extracted URI prefix, <code>null</code> otherwise.
	 */
	public static String extractURIprefix(URI objURI) {
		String URIstr = objURI.toString();
		String URIpattern = "("+ URIprefixpattern + ")/(?:" + displayIDpattern + "/)+" + versionPattern;
		Pattern r = Pattern.compile(URIpattern);
		Matcher m = r.matcher(URIstr);
		if (m.find())
			return m.group(1); // the first capturing group is the version.
		else 
			return null;	
	}
	
	/**
	 * Extract the display ID from this object's identity URI.
	 * @param objURI
	 * @return
	 */
	public static String extractTopLevelObjDisplayId(URI objURI) {
		String URIstr = objURI.toString();
		String URIpattern = URIprefixpattern + "/(" + displayIDpattern + ")/" + versionPattern;
		Pattern r = Pattern.compile(URIpattern);
		Matcher m = r.matcher(URIstr);
		if (m.find())
			return m.group(1); // the first capturing group is the version.
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
		String URIpattern = URIprefixpattern + "/(?:" + displayIDpattern + "/)+" + "(" + versionPattern + ")";
		Pattern r = Pattern.compile(URIpattern);
		Matcher m = r.matcher(URIstr);
		if (m.find())
			return m.group(1); // the first capturing group is the version. (?:...) is a non-capturing group.
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
//		if (m.find()) {
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
		// TODO: Need MavenVersionPattern or just a versionPattern?
		String URIpattern = URIprefixpattern + "/" + displayIDpattern + "/" + versionPattern;
		Pattern r = Pattern.compile(URIpattern);
		Matcher m = r.matcher(URIstr);
		if (m.find()) {
			return true;
		}			
		else {
			return false;
		}		
	}
	 
	 /**
	 * @param topLevelURI
	 * @param childURI
	 * @return
	 */
	public static final boolean isChildURIcompliant(URI topLevelURI, URI childURI) {
		String topLevelURIstr = topLevelURI.toString();
		String childURIstr = childURI.toString();
		String topLevelURIpattern = "(" + URIprefixpattern + "/" + displayIDpattern + ")/" + versionPattern;
		String childURIpattern = "(" + URIprefixpattern + "/" + displayIDpattern + ")/" + displayIDpattern + "/" + versionPattern;
		Pattern topLevelPattern = Pattern.compile(topLevelURIpattern);
		Matcher topLevelMatcher = topLevelPattern.matcher(topLevelURIstr);
		Pattern childPattern = Pattern.compile(childURIpattern);
		Matcher childMatcher = childPattern.matcher(childURIstr);
		if (topLevelMatcher.find() && childMatcher.find()) {
			if (topLevelMatcher.group(1).equals(childMatcher.group(1))) {
				return true;
			}
			else
				return false;
		}
		else {
			return false;
		}
	 }
	
	public static boolean isDisplayIdCompliant(String newDisplayId) {
		Pattern r = Pattern.compile(displayIDpattern);
		Matcher m = r.matcher(newDisplayId);
		if (m.find()) {
			return true;
		}			
		else {
			return false;
		}
	}
	 
	public static final String URIprefixpattern = "\\b(?:https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]"; 
	// (?:...) is a non-capturing group
	public static final String displayIDpattern = "[a-zA-Z0-9_]+";
	//public static final String MavenVersionPattern = "[0-9]+(?:[.][0-9]+){0,2}(?:-alpha|beta|SNAPSHOT)*";
	public static final String versionPattern = "[-a-zA-Z0-9.]+";	

}

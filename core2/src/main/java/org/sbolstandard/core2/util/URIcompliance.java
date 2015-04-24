package org.sbolstandard.core2.util;

import java.net.URI;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class URIcompliance {

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
	 * great grand child object's display ID.
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

	/**
	 * Test if the given object's identity URI is compliant with the form {@code ⟨prefix⟩/(⟨displayId⟩/){1,4}⟨version⟩.
	 * The prefix is established by the owner of this object. The number of displayIds can range from 1 to 4, depending on
	 * the level of the given object. If the given index is 0, then {@code objURI} is checked as a top-level URI for compliance;
	 * if the given index is 1, then {@code objURI} is checked as a child of a top-level URI for compliance;
	 * if the given index is 2, then {@code objURI} is checked as a grand child of a top-level URI for compliance; and
	 * if the given index is 3, then {@code objURI} is checked as a great grand child of a top-level URI for compliance.
	 * @param objURI
	 * @param index
	 * @return <code>true</code> if the identity URI is compliant, <code>false</code> otherwise.
	 */
	public static final boolean isURIcompliant(URI objURI, int index) {
		if (index < 0 || index > 3) {
			// TODO: generate error message
			return false;
		}
		Pattern r;
		String URIstr = objURI.toString();
		if (index == 0) {
			r = Pattern.compile(toplevelURIpattern);
		}
		else if (index == 1) {
			r = Pattern.compile(childURIpattern);
		}
		else if (index == 2) {
			r = Pattern.compile(grandchildURIpattern);
		}
		else { // index == 3
			r = Pattern.compile(greatGrandchildURIpattern);
		}
		Matcher m = r.matcher(URIstr);
		if (m.matches()) {
			return true;
		}
		else {
			// TODO: Warning: top-level URI is not compliant.
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
					String parentVersion = parentMatcher.group(3);
					String childVersion = childMatcher.group(3);
					if (parentVersion.equals(childVersion)) {
						return true;
					}
					else {
						// TODO: Warning: Versions do not match.
						return false;
					}

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
			// TODO: Warning: Display ID is not compliant.
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

	/**
	 * @param URIprefix
	 * @return
	 */
	public static boolean isURIprefixCompliant(String URIprefix) {
		Pattern r = Pattern.compile(URIprefixPattern);
		Matcher m = r.matcher(URIprefix);
		if (m.matches()) {
			return true;
		}
		else {
			return false;
		}
	}

	// (?:...) is a non-capturing group
	public static final String URIprefixPattern = "\\b(?:https?|ftp|file)://[-a-zA-Z0-9+&@#%?=~_|!:,.;]*[-a-zA-Z0-9+&@#%=~_|]";
	//public static final String URIprefixpattern = "\\b(?:https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]";

	public static final String displayIDpattern = "[a-zA-Z_]+[a-zA-Z0-9_]+";//"[a-zA-Z0-9_]+";

	//public static final String MavenVersionPattern = "[0-9]+(?:[.][0-9]+){0,2}(?:-alpha|beta|SNAPSHOT)*";

	public static final String versionPattern = "[^/]+"; //"[-a-zA-Z0-9.]+";

	// A URI can have up to 4 display IDs. The one with 4 display IDs can be ComponentDefinition -> SequenceAnnotation -> (Location) MultiRange -> Range.
	// group 1: persistent ID
	// group 2: URI prefix
	// group 3: version
	public static final String genericURIpattern1 = "((" + URIprefixPattern + ")/(?:" + displayIDpattern + "/){1,4})(" + versionPattern + ")";

	// A URI can have up to 4 display IDs. The one with 4 display IDs can be ComponentDefinition -> SequenceAnnotation -> (Location) MultiRange -> Range.
	// group 1: top-level display ID
	// group 2: top-level's child display ID
	// group 3: top-level's grand child display ID
	// group 4: top-level's grand grand child display ID
	public static final String genericURIpattern2 = URIprefixPattern + "/((?:" + displayIDpattern + "/){1,4})" + versionPattern;

	public static final String toplevelURIpattern = URIprefixPattern + "/" + displayIDpattern + "/" + versionPattern;

	public static final String childURIpattern = URIprefixPattern + "/(?:" + displayIDpattern + "/){2}" + versionPattern;

	public static final String grandchildURIpattern = URIprefixPattern + "/(?:" + displayIDpattern + "/){3}" + versionPattern;

	public static final String greatGrandchildURIpattern = URIprefixPattern + "/(?:" + displayIDpattern + "/){4}" + versionPattern;
}
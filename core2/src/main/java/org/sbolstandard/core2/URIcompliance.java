package org.sbolstandard.core2;

import java.net.URI;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

final class URIcompliance {
	
	static void validateIdVersion(String displayId, String version) {
		if (displayId!=null && !isDisplayIdCompliant(displayId)) {
			throw new IllegalArgumentException("Display id `" + displayId + "' is not valid.");
		}
		if (version!=null && !isVersionCompliant(version)) {
			throw new IllegalArgumentException("Version `" + version + "' is not valid.");
		}
	}

	static URI createCompliantURI(String prefix, String displayId, String version) {
		validateIdVersion(displayId, version);
		if (!prefix.endsWith("/") && !prefix.endsWith(":") && !prefix.endsWith("#")) {
			prefix += "/";
		}
		if (version==null || version.equals("")) {
			return URI.create(prefix + displayId);
		}
		return URI.create(prefix + displayId + '/' + version);
	}
	
	static URI createCompliantURI(String prefix, String type, String displayId, String version, boolean useType) {
		validateIdVersion(displayId, version);
		if (!useType) return createCompliantURI(prefix,displayId,version);
		if (!prefix.endsWith("/") && !prefix.endsWith(":") && !prefix.endsWith("#")) {
			prefix += "/";
		}
		if (version==null || version.equals("")) {
			return URI.create(prefix + type + '/' + displayId);
		}
		return URI.create(prefix + type + '/' + displayId + '/' + version);
	}

	/**
	 * Extract the persistent identity URI from the given URI.
	 * The persistent identity is simply the identity URI without the version.
	 * @return the extracted persistent identity URI, <code>null</code> otherwise.
	 */
	static String extractPersistentId(URI objURI) {
		String URIstr = objURI.toString();
		Pattern r = Pattern.compile(genericURIpattern1);
		Matcher m = r.matcher(URIstr);
		if (m.matches()) {
			return m.group(1);
		}
		else {
			throw new IllegalArgumentException(objURI + " does not include a valid persistentIdentity.");
		}

	}

	/**
	 * Extract the URI prefix from this object's identity URI.
	 * @return the extracted URI prefix, <code>null</code> otherwise.
	 */
	static String extractURIprefix(URI objURI) {
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
	 * @return the extracted display ID.
	 */
	static String extractDisplayId(URI objURI) {
		String URIstr = objURI.toString();
		Pattern r = Pattern.compile(genericURIpattern1);
		Matcher m = r.matcher(URIstr);
		if (m.matches()) {
			return m.group(4);
		}
		else
			return null;
	}

	/**
	 * Extract the version from this object's identity URI.
	 * @return the version if the given URI is compliant, <code>null</code> otherwise.
	 */
	static String extractVersion(URI objURI) {
		String URIstr = objURI.toString();
		Pattern r = Pattern.compile(genericURIpattern1);
		Matcher m = r.matcher(URIstr);
		if (m.matches() && m.groupCount()>=6)
			return m.group(6);
		else
			return null;
	}

	static final boolean isURIcompliant(Identified identified) {
		if (!identified.isSetDisplayId()) return false;
		if (!identified.isSetPersistentIdentity()) return false;
		if (!identified.getPersistentIdentity().toString().endsWith("/"+identified.getDisplayId()) &&
			!identified.getPersistentIdentity().toString().endsWith("#"+identified.getDisplayId()) &&
			!identified.getPersistentIdentity().toString().endsWith(":"+identified.getDisplayId())) return false;
		if (!identified.isSetVersion()) {
			if (!identified.identity.toString().equals(identified.getPersistentIdentity().toString())) return false;
		} else {
			if (!identified.identity.toString().equals(identified.getPersistentIdentity().toString()+"/"
					+identified.getVersion())) return false;
		}
		return true;
	}
	
	// TODO: this method is only checking URIs and not other fields.  It also is only allowing / delimiter.
	// Seems to be needed for addChildSafely method.  Should investigate further.
	static final boolean isChildURIformCompliant(URI parentURI, URI childURI) {
		String parentPersistentId = extractPersistentId(parentURI);
		if (parentPersistentId==null) return false;
		String childDisplayId = extractDisplayId(childURI);
		if (childDisplayId==null) return false;
		String parentVersion = extractVersion(parentURI);
		if (parentVersion == null) {
			return childURI.toString().equals(parentPersistentId+"/"+childDisplayId);
		} else {
			return childURI.toString().equals(parentPersistentId+"/"+childDisplayId+"/"+parentVersion);
		}
	}

	static final boolean isChildURIcompliant(Identified parent, Identified child) {
		//URI parentURI = parent.getIdentity();
		//URI childURI = child.getIdentity();
		//String parentPersistentId = extractPersistentId(parentURI);
		//if (parentPersistentId==null) return false;
		//String childDisplayId = extractDisplayId(childURI);
		//if (childDisplayId==null) return false;
		//String parentVersion = extractVersion(parentURI);
		if (!isURIcompliant(child)) return false;
		if (!child.getPersistentIdentity().toString().equals(parent.getPersistentIdentity()+"/"+child.getDisplayId()) &&
				!child.getPersistentIdentity().toString().equals(parent.getPersistentIdentity()+"#"+child.getDisplayId()) &&
				!child.getPersistentIdentity().toString().equals(parent.getPersistentIdentity()+":"+child.getDisplayId())) return false;
		if (parent.isSetVersion()) {
			if (!child.isSetVersion()||!child.getVersion().equals(parent.getVersion())) return false;
		} else if (child.isSetVersion()) {
			return false;
		}
		return true;
	}
	
	/**
	 * Test if the given object's identity URI is compliant with the form {@code ⟨prefix⟩/(⟨displayId⟩/)}{1,3}⟨version⟩.
	 * The prefix is established by the owner of this object. The number of displayIds can range from 1 to 4, depending on
	 * the level of the given object. 
	 * @param objURI
	 * @return <code>true</code> if the identity URI is compliant, <code>false</code> otherwise.
	 */
	static final boolean isTopLevelURIformCompliant(URI topLevelURI) {
		Pattern r;
		String URIstr = topLevelURI.toString();		
		r = Pattern.compile(toplevelURIpattern);
		Matcher m = r.matcher(URIstr);
		return (m.matches());
	}
	
	/**
	 * Test if the given object's identity URI is compliant with the form {@code ⟨prefix⟩/(⟨displayId⟩/)}{1,3}⟨version⟩.
	 * The prefix is established by the owner of this object. The number of displayIds can range from 1 to 4, depending on
	 * the level of the given object. 
	 * @param objURI
	 * @return <code>true</code> if the identity URI is compliant, <code>false</code> otherwise.
	 */
	static final boolean isTopLevelURIcompliant(TopLevel topLevel) {
		if (!isTopLevelURIformCompliant(topLevel.getIdentity())) return false;
		return isURIcompliant(topLevel);
	}
	
//	static final boolean isURIcompliantTemp(URI objURI, String URIprefix, String version, String ... displayIds) {
//		if (displayIds.length == 0 || displayIds.length > 4) {
//			// Wrong number of display IDs.
//			return false;
//		}
//		if (objURI == null) {
//			return false;
//		}
//		String URIstr = objURI.toString();
//		if (URIstr.isEmpty()) {
//			return false;
//		}
//		String[] extractedURIpieces = URIstr.split("/");
//		if (extractedURIpieces.length < 4) { // minimal number of "/" should be 4, such as "http://www.async.ece.utah.edu/LacI_Inv/".
//			return false;
//		}
//		int curIndex = extractedURIpieces.length - 1;
//		String curExtractedStr = extractedURIpieces[curIndex];
//		if (URIstr.endsWith("/")) { // version is empty. The last string extracted from the URI should be the display ID.
//			if (isDisplayIdCompliant(curExtractedStr) && curExtractedStr.equals(displayIds[0])) {
//				String extractedURIprefix = "";
//				String parentDisplayId = null; 				// codereview: assignment to null is redundant - this is set in all code-paths, right?
//				String grandparentDisplayId = null;			// codereview: assignment to null is redundant - this is set in all code-paths, right?
//				String greatGrandparentDisplayId = null;	// codereview: assignment to null is redundant - this is set in all code-paths, right?
//				switch (displayIds.length) {				
//				case 1: // Only one display ID is provided. Should be a top-level object.
//					break;
//				case 2: // Two display IDs are provided. Should be a child of a top-level object.
//					parentDisplayId = displayIds[1];
//					curIndex = curIndex - 1;
//					curExtractedStr = extractedURIpieces[curIndex]; // parent display ID extracted from URI.
//					if (isDisplayIdCompliant(parentDisplayId) && parentDisplayId.equals(curExtractedStr)) {
//						break;
//					}
//					else {
//						// Parent display ID not compliant
//						return false;
//					}
//				case 3: // Three display IDs are provided. Should be a grandchild of a top-level object.
//					parentDisplayId = displayIds[1];
//					curIndex = curIndex - 1;
//					curExtractedStr = extractedURIpieces[curIndex]; // parent display ID extracted from URI.
//					if (isDisplayIdCompliant(parentDisplayId) && parentDisplayId.equals(curExtractedStr)) {
//						grandparentDisplayId = displayIds[2];
//						curIndex = curIndex - 1;
//						curExtractedStr = extractedURIpieces[curIndex]; // grandparent display ID extracted from URI.
//						if (isDisplayIdCompliant(grandparentDisplayId) 
//								&& grandparentDisplayId.equals(curExtractedStr)) {
//							break;
//						}
//						else {
//							// Grandparent display ID not compliant
//							return false;
//						}
//					}
//					else {
//						// Parent display ID not compliant
//						return false;
//					}
//				case 4: // Four display IDs are provided. Should be a great grandchild of a top-level object.
//					parentDisplayId = displayIds[1];
//					curIndex = curIndex - 1;
//					curExtractedStr = extractedURIpieces[curIndex]; // parent display ID extracted from URI.
//					if (isDisplayIdCompliant(parentDisplayId) && parentDisplayId.equals(curExtractedStr)) {
//						grandparentDisplayId = displayIds[2];
//						curIndex = curIndex - 1;
//						curExtractedStr = extractedURIpieces[curIndex]; // grandparent display ID extracted from URI.
//						if (isDisplayIdCompliant(grandparentDisplayId) 
//								&& grandparentDisplayId.equals(curExtractedStr)) {
//							greatGrandparentDisplayId = displayIds[3];
//							curIndex = curIndex - 1;
//							curExtractedStr = extractedURIpieces[curIndex]; // great grandparent display ID extracted from URI.
//							if (isDisplayIdCompliant(greatGrandparentDisplayId) 
//								&& greatGrandparentDisplayId.equals(curExtractedStr)) {
//								break;
//							}
//							else {
//								// Great grandparent display ID not compliant
//								return false;
//							}
//						}
//						else {
//							// Grandparent display ID not compliant
//							return false;
//						}
//					}
//					else {
//						// Parent display ID not compliant
//						return false;
//					}
//				default:
//					return false;				
//				}
//				for (int i = 0; i < curIndex; i++) {
//					extractedURIprefix = extractedURIprefix + extractedURIpieces[i];
//				}
//				// URI prefix not compliant
//				return isURIprefixCompliant(extractedURIprefix) && extractedURIprefix.equals(URIprefix);
//			}
//			else { // displayId not compliant
//				return false;
//			}
//		}
//		else { // version is not empty
//			//String displayId = extractedURIpieces[versionIndex];
//// version not compliant
//			return isVersionCompliant(curExtractedStr);
//		}
//	}

	static boolean isDisplayIdCompliant(String newDisplayId) {
		Pattern r = Pattern.compile(displayIDpattern);
		Matcher m = r.matcher(newDisplayId);
		return m.matches();
	}

	static boolean isVersionCompliant(String newVersion) {
		if (newVersion==null) {
			throw new IllegalArgumentException("Version must not be null");
		}
		if (newVersion.equals("")) return true;
		Pattern r = Pattern.compile(versionPattern);
		Matcher m = r.matcher(newVersion);
		return m.matches();
	}

	static boolean isURIprefixCompliant(String URIprefix) {
		Pattern r = Pattern.compile(URIprefixPattern+delimiter);
		Matcher m = r.matcher(URIprefix);
		return m.matches();
	}

	// (?:...) is a non-capturing group
	//static final String URIprefixPattern = "\\b(?:https?|ftp|file)://[-a-zA-Z0-9+&@#%?=~_|!:,.;]*[-a-zA-Z0-9+&@#%=~_|]";
	
	static final String delimiter = "[/|#|:]";
			
	static final String URIprefixPattern = "\\b(?:https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]";

	static final String displayIDpattern = "[a-zA-Z_]+[a-zA-Z0-9_]*";//"[a-zA-Z0-9_]+";

	static final String versionPattern = "[0-9]+[a-zA-Z0-9_\\.-]*"; // ^ and $ are the beginning and end of the string anchors respectively. 
															// | is used to denote alternates. 

	// A URI can have up to 4 display IDs. The one with 4 display IDs can be ComponentDefinition -> SequenceAnnotation -> (Location) MultiRange -> Range.
	// group 1: persistent ID
	// group 2: URI prefix
	// group 3: version
	static final String genericURIpattern1 = "((" + URIprefixPattern + ")("+delimiter+"(" + displayIDpattern + ")){1,3})(/(" + versionPattern + "))?";

	// A URI can have up to 4 display IDs. The one with 4 display IDs can be ComponentDefinition -> SequenceAnnotation -> (Location) MultiRange -> Range.
	// group 1: top-level display ID
	// group 2: top-level's child display ID
	// group 3: top-level's grand child display ID
	// group 4: top-level's grand grand child display ID
	//static final String genericURIpattern2 = URIprefixPattern + delimiter + "((" + displayIDpattern + "/){1,3})" + versionPattern;

	static final String toplevelURIpattern = URIprefixPattern + delimiter + displayIDpattern + "(/" + versionPattern + ")?";
	
	//static final String childURIpattern = URIprefixPattern + delimiter + "(?:" + displayIDpattern + "/){2}" + versionPattern;

	//static final String grandchildURIpattern = URIprefixPattern + delimiter + "(?:" + displayIDpattern + "/){3}" + versionPattern;

	//static final String greatGrandchildURIpattern = URIprefixPattern + delimiter + "(?:" + displayIDpattern + "/){4}" + versionPattern;

	@SafeVarargs
	static boolean keyExistsInAnyMap(URI key, Map<URI, ?>... maps) {
		for(Map<URI, ?> map : maps) {
			if(map.keySet().contains(key))
				return true;
		}

		return false;
	}
}

package org.sbolstandard.core2;

import java.net.URI;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This class provides functionality to validate URI compliance. 
 * 
 * @author Chris Myers
 * @author Zhen Zhang
 * @version 2.1
 */

final class URIcompliance {
	
	/**
	 * @param displayId
	 * @param version
	 * @throws SBOLValidationException if either of the following SBOL validation rules was violated: 10204, 10206.
	 */
	static void validateIdVersion(String displayId, String version) throws SBOLValidationException {
		if (displayId!=null && !isDisplayIdValid(displayId)) {
			throw new SBOLValidationException("sbol-10204");
		}
		if (version!=null && !isVersionValid(version)) {
			throw new SBOLValidationException("sbol-10206");
		}
	}

	/**
	 * @param prefix
	 * @param displayId
	 * @param version
	 * @return
	 * @throws SBOLValidationException if an SBOL validation exception occurred in {@link URIcompliance#validateIdVersion(String, String)}.
	 */
	static URI createCompliantURI(String prefix, String displayId, String version) throws SBOLValidationException {
		if (prefix == null) {
			throw new IllegalArgumentException("The defaultURIprefix is not set. Please set it to a non-null value");
		}
		validateIdVersion(displayId, version);
		if (!prefix.endsWith("/") && !prefix.endsWith(":") && !prefix.endsWith("#")) {
			prefix += "/";
		}
		if (version==null || version.equals("")) {
			return URI.create(prefix + displayId);
		}
		return URI.create(prefix + displayId + '/' + version);
	}
	
	/**
	 * @param prefix
	 * @param type
	 * @param displayId
	 * @param version
	 * @param useType
	 * @return
	 * @throws SBOLValidationException if either of the following condition is satisfied:
	 * <li>an SBOL validation exception occurred in {@link #validateIdVersion(String, String)}; or</li>
	 * <li>an SBOL validation exception occurred in {@link #createCompliantURI(String, String, String)}.</li>
	 */
	static URI createCompliantURI(String prefix, String type, String displayId, String version, boolean useType) throws SBOLValidationException {
		if (prefix == null) {
			throw new IllegalArgumentException("The defaultURIprefix is not set. Please set it to a non-null value");
		}
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
			return null;//throw new SBOLValidationException(objURI + " does not include a valid persistentIdentity.");
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

	static final void isURIcompliant(Identified identified) throws SBOLValidationException {
		if (!identified.isSetDisplayId()) {
			throw new SBOLValidationException("sbol-10215");
		}
		if (!identified.isSetPersistentIdentity()) {
			throw new SBOLValidationException("sbol-10216");
		}
		if (!identified.getPersistentIdentity().toString().endsWith("/"+identified.getDisplayId()) &&
			!identified.getPersistentIdentity().toString().endsWith("#"+identified.getDisplayId()) &&
			!identified.getPersistentIdentity().toString().endsWith(":"+identified.getDisplayId())) {
			throw new SBOLValidationException("sbol-10216");
		}
		if (!identified.isSetVersion()) {
			if (!identified.identity.toString().equals(identified.getPersistentIdentity().toString())) {
				throw new SBOLValidationException("sbol-10218");
			}
		} else {
			if (!identified.identity.toString().equals(identified.getPersistentIdentity().toString()+"/"
					+identified.getVersion())) {
				throw new SBOLValidationException("sbol-10218");
			}
		}
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

	static final void isChildURIcompliant(Identified parent, Identified child) throws SBOLValidationException {
		try {
			isURIcompliant(child);
		} catch (SBOLValidationException e) {
			if (e.getMessage().contains("sbol-10216")) {
				throw new SBOLValidationException("sbol-10217");
			} else {
				throw new SBOLValidationException(e.getMessage());
			}
		}
		if (!child.getPersistentIdentity().toString().equals(parent.getPersistentIdentity()+"/"+child.getDisplayId()) &&
				!child.getPersistentIdentity().toString().equals(parent.getPersistentIdentity()+"#"+child.getDisplayId()) &&
				!child.getPersistentIdentity().toString().equals(parent.getPersistentIdentity()+":"+child.getDisplayId())) {
			throw new SBOLValidationException("sbol-10217");
		}
		if (parent.isSetVersion()) {
			if (!child.isSetVersion()||!child.getVersion().equals(parent.getVersion())) {
				throw new SBOLValidationException("sbol-10219");
			}
		} else if (child.isSetVersion()) {
			throw new SBOLValidationException("sbol-10219");
		}
	}
	
	/**
	 * Test if the given object's identity URI is compliant with the form {@code ⟨prefix⟩/(⟨displayId⟩/)}{1,3}⟨version⟩.
	 * The prefix is established by the owner of this object. The number of displayIds can range from 1 to 4, depending on
	 * the level of the given object. 
	 * @param objURI
	 * @throws SBOLValidationException 
	 */
	static final void isTopLevelURIformCompliant(URI topLevelURI) throws SBOLValidationException {
		Pattern r;
		String URIstr = topLevelURI.toString();		
		r = Pattern.compile(toplevelURIpattern);
		Matcher m = r.matcher(URIstr);
		if (!m.matches()) {
			throw new SBOLValidationException("sbol-10201");
		}
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

	static boolean isDisplayIdValid(String newDisplayId) {
		Pattern r = Pattern.compile(displayIDpattern);
		Matcher m = r.matcher(newDisplayId);
		return m.matches();
	}

	static boolean isVersionValid(String version) {
		if (version.equals("")) return true;
		Pattern r = Pattern.compile(versionPattern);
		Matcher m = r.matcher(version);
		return m.matches();
	}

	static boolean isURIprefixCompliant(String URIprefix) {
		Pattern r = Pattern.compile(URIprefixPattern+delimiter);
		Matcher m = r.matcher(URIprefix);
		return m.matches();
	}
	
	static boolean isValidURI(String URIstr) {
		Pattern r = Pattern.compile(URI_REFERENCE_REGEX);
		Matcher m = r.matcher(URIstr);
		if (m.matches()) {
			return true;
		} else {
			return false;
		}
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
	
	private static final String URI_REFERENCE_REGEX = "(([a-zA-Z][a-zA-Z0-9\\+\\-\\.]*:((((//((((([a-zA-Z0-9\\-_\\.!\\~\\*'\\(\\);:\\&=\\+$,]|(%[a-fA-F0-9]{2}))*)\\@)?((((([a-zA-Z0-9](([a-zA-Z0-9\\-])*[a-zA-Z0-9])?)\\.)*([a-zA-Z](([a-zA-Z0-9\\-])*[a-zA-Z0-9])?)(\\.)?)|([0-9]+((\\.[0-9]+){3})))(:[0-9]*)?))?|([a-zA-Z0-9\\-_\\.!\\~\\*'\\(\\)$,;:\\@\\&=\\+]|(%[a-fA-F0-9]{2}))+)(/(([a-zA-Z0-9\\-_\\.!\\~\\*'\\(\\):\\@\\&=\\+$,]|(%[a-fA-F0-9]{2}))*(;([a-zA-Z0-9\\-_\\.!\\~\\*'\\(\\):\\@\\&=\\+$,]|(%[a-fA-F0-9]{2}))*)*)(/(([a-zA-Z0-9\\-_\\.!\\~\\*'\\(\\):\\@\\&=\\+$,]|(%[a-fA-F0-9]{2}))*(;([a-zA-Z0-9\\-_\\.!\\~\\*'\\(\\):\\@\\&=\\+$,]|(%[a-fA-F0-9]{2}))*)*))*)?)|(/(([a-zA-Z0-9\\-_\\.!\\~\\*'\\(\\):\\@\\&=\\+$,]|(%[a-fA-F0-9]{2}))*(;([a-zA-Z0-9\\-_\\.!\\~\\*'\\(\\):\\@\\&=\\+$,]|(%[a-fA-F0-9]{2}))*)*)(/(([a-zA-Z0-9\\-_\\.!\\~\\*'\\(\\):\\@\\&=\\+$,]|(%[a-fA-F0-9]{2}))*(;([a-zA-Z0-9\\-_\\.!\\~\\*'\\(\\):\\@\\&=\\+$,]|(%[a-fA-F0-9]{2}))*)*))*))(\\?([a-zA-Z0-9\\-_\\.!\\~\\*'\\(\\);/\\?:\\@\\&=\\+$,]|(%[a-fA-F0-9]{2}))*)?)|(([a-zA-Z0-9\\-_\\.!\\~\\*'\\(\\);\\?:\\@\\&=\\+$,]|(%[a-fA-F0-9]{2}))([a-zA-Z0-9\\-_\\.!\\~\\*'\\(\\);/\\?:\\@\\&=\\+$,]|(%[a-fA-F0-9]{2}))*)))|(((//((((([a-zA-Z0-9\\-_\\.!\\~\\*'\\(\\);:\\&=\\+$,]|(%[a-fA-F0-9]{2}))*)\\@)?((((([a-zA-Z0-9](([a-zA-Z0-9\\-])*[a-zA-Z0-9])?)\\.)*([a-zA-Z](([a-zA-Z0-9\\-])*[a-zA-Z0-9])?)(\\.)?)|([0-9]+((\\.[0-9]+){3})))(:[0-9]*)?))?|([a-zA-Z0-9\\-_\\.!\\~\\*'\\(\\)$,;:\\@\\&=\\+]|(%[a-fA-F0-9]{2}))+)(/(([a-zA-Z0-9\\-_\\.!\\~\\*'\\(\\):\\@\\&=\\+$,]|(%[a-fA-F0-9]{2}))*(;([a-zA-Z0-9\\-_\\.!\\~\\*'\\(\\):\\@\\&=\\+$,]|(%[a-fA-F0-9]{2}))*)*)(/(([a-zA-Z0-9\\-_\\.!\\~\\*'\\(\\):\\@\\&=\\+$,]|(%[a-fA-F0-9]{2}))*(;([a-zA-Z0-9\\-_\\.!\\~\\*'\\(\\):\\@\\&=\\+$,]|(%[a-fA-F0-9]{2}))*)*))*)?)|(/(([a-zA-Z0-9\\-_\\.!\\~\\*'\\(\\):\\@\\&=\\+$,]|(%[a-fA-F0-9]{2}))*(;([a-zA-Z0-9\\-_\\.!\\~\\*'\\(\\):\\@\\&=\\+$,]|(%[a-fA-F0-9]{2}))*)*)(/(([a-zA-Z0-9\\-_\\.!\\~\\*'\\(\\):\\@\\&=\\+$,]|(%[a-fA-F0-9]{2}))*(;([a-zA-Z0-9\\-_\\.!\\~\\*'\\(\\):\\@\\&=\\+$,]|(%[a-fA-F0-9]{2}))*)*))*)|(([a-zA-Z0-9\\-_\\.!\\~\\*'\\(\\);\\@\\&=\\+$,]|(%[a-fA-F0-9]{2}))+(/(([a-zA-Z0-9\\-_\\.!\\~\\*'\\(\\):\\@\\&=\\+$,]|(%[a-fA-F0-9]{2}))*(;([a-zA-Z0-9\\-_\\.!\\~\\*'\\(\\):\\@\\&=\\+$,]|(%[a-fA-F0-9]{2}))*)*)(/(([a-zA-Z0-9\\-_\\.!\\~\\*'\\(\\):\\@\\&=\\+$,]|(%[a-fA-F0-9]{2}))*(;([a-zA-Z0-9\\-_\\.!\\~\\*'\\(\\):\\@\\&=\\+$,]|(%[a-fA-F0-9]{2}))*)*))*)?))(\\?([a-zA-Z0-9\\-_\\.!\\~\\*'\\(\\);/\\?:\\@\\&=\\+$,]|(%[a-fA-F0-9]{2}))*)?))?(\\#([a-zA-Z0-9\\-_\\.!\\~\\*'\\(\\);/\\?:\\@\\&=\\+$,]|(%[a-fA-F0-9]{2}))*)?";

	
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

	/**
	 * Check the given {@code URIprefix} to make sure it is not {@code null} and is compliant,
	 * and if URIprefix does not end with one of the following delimiters: "/", ":", or "#", then
	 * "/" is appended to the end of the given {@code URIprefix}.
	 *
	 * @param URIprefix
	 * @return URIprefix
	 * @throws SBOLValidationException if the given {@code URIprefix} is {@code null}
	 * @throws SBOLValidationException if the given {@code URIprefix} is non-compliant
	 */
	static String checkURIprefix(String URIprefix) throws SBOLValidationException {
		if (URIprefix==null) {
			throw new SBOLValidationException("sbol-10201");
		}
		if (!URIprefix.endsWith("/") && !URIprefix.endsWith(":") && !URIprefix.endsWith("#")) {
			URIprefix += "/";
		}
		if (!isURIprefixCompliant(URIprefix)) {
			throw new SBOLValidationException("sbol-10201");
		}
		return URIprefix;
	}

	static String fixDisplayId(String displayId) {
		displayId = displayId.replaceAll("[^a-zA-Z0-9_]", "_");
		displayId = displayId.replace(" ", "_");
		if (Character.isDigit(displayId.charAt(0))) {
			displayId = "_" + displayId;
		}
		return displayId;
	}
}

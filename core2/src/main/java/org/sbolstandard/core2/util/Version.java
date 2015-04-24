package org.sbolstandard.core2.util;

/**
 * This class provides methods for version comparisons, following the Maven version scheme.
 * @author Zhen Zhang
 *
 */
public final class Version {
	
	
	/**
	 * Compare the given two versions based on the Maven version scheme.
	 * @param version1
	 * @param version2
	 * @return <code>true</code> if the <code>version1</code> is newer than <code>version2</code>, <code>false</code> otherwise. 
	 */
	public static final boolean isFirstVersionNewer(String version1, String version2) {
		ComparableVersion v1 = new ComparableVersion(version1);
		ComparableVersion v2 = new ComparableVersion(version2);
		if (v1.compareTo(v2) > 0)			
			return true;
		else
			return false;
	}
}

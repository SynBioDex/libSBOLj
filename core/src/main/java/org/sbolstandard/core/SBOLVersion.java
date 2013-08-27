package org.sbolstandard.core;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author Evren Sirin
 */
public class SBOLVersion {
	private static final SBOLVersion INSTANCE = new SBOLVersion();

	public static final SBOLVersion getInstance() {
		return INSTANCE;
	}
	
	private final String versionString;
	
	private SBOLVersion() {
		Properties versionProperties = new Properties();
		
		InputStream vstream = SBOLVersion.class.getResourceAsStream("/version.properties");
		if (vstream != null) {
			try {
				versionProperties.load(vstream);
			}
			catch (IOException e) {
				System.err.println("Could not load version properties:");
				e.printStackTrace();
			}
			finally {
				try {
					vstream.close();
				}
				catch (IOException e) {
					System.err.println("Could not close version properties:");
					e.printStackTrace();
				}
			}
		}
		
		versionString = versionProperties.getProperty("org.sbolstandard.core.version", "0.0");
	}
	
	public String getVersionString() {
		return versionString;
	}

	public String toString() {
		return "Version: " + getVersionString();
	}
}

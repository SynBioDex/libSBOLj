package org.oboparser.pro.authoring;

import java.io.*;

public interface OBOTerm {
	public void generateOBO(PrintStream ps);
	public boolean inOBO();
	public void clearInOBO();
}

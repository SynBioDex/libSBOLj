package org.oboparser.obo.annotations.parsers;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.reflect.*;
import java.util.LinkedList;

public interface Exporter {

	public String export(Class cls);
}




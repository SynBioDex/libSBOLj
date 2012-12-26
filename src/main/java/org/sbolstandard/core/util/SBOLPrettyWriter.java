/*
 * Copyright (c) 2012 Clark & Parsia, LLC. <http://www.clarkparsia.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.sbolstandard.core.util;

import java.io.OutputStream;
import java.io.PrintStream;

import org.sbolstandard.core.Collection;
import org.sbolstandard.core.DnaComponent;
import org.sbolstandard.core.DnaSequence;
import org.sbolstandard.core.SBOLDocument;
import org.sbolstandard.core.SBOLObject;
import org.sbolstandard.core.SBOLVisitable;
import org.sbolstandard.core.SBOLWriter;
import org.sbolstandard.core.SequenceAnnotation;

/**
 * Utility class to write the contents of an SBOL document in a text-based, human-readable format. This format is used
 * for information purposes and not intended to be an exchange syntax.
 * 
 * @author Evren Sirin
 */
public class SBOLPrettyWriter implements SBOLWriter {
	public SBOLPrettyWriter() {
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void write(SBOLDocument doc, OutputStream out) {
		doc.accept(new Writer(out));
	}

	/**
	 * Additional utility function to write only the contents of a given object rather than th whole document.
	 */
	public void write(SBOLVisitable obj, OutputStream out) {
		obj.accept(new Writer(out));
	}

	/**
	 * The actual writer implementation.
	 *  
	 * @author Evren Sirin
	 */
	private static class Writer extends SBOLBaseVisitor {
		private static final String INDENT = "   ";

		private final PrintStream out;
		private String indent = "";

		public Writer(OutputStream out) {
			this.out = (out instanceof PrintStream) ? (PrintStream) out : new PrintStream(out);
		}

		protected void startBlock(String name) {
			out.print(indent);
			out.print(name);
			out.println(" [");
			indent += INDENT;
		}

		protected void endBlock() {
			indent = indent.substring(0, indent.length() - INDENT.length());
			out.print(indent);
			out.println("]");
		}

		protected void printAttr(String attr, Object value) {
			if (value != null) {
				if (value instanceof java.util.Collection) {
					for (Object v : (java.util.Collection) value) {
						printAttr(attr, v);
					}
				}
				else {
					out.print(indent);
					out.print(attr);
					out.print(": ");
					if (value instanceof SBOLObject) {
						out.println(((SBOLObject) value).getURI());
					}
					else {
						out.println(value);
					}
				}
			}
		}

		@Override
		public void visit(SBOLDocument doc) {
			startBlock("SBOLDocument");
			super.visit(doc);
			endBlock();
		}

		@Override
		public void visit(Collection coll) {
			startBlock("Collection");
			printAttr("uri", coll.getURI());
			printAttr("displayId", coll.getDisplayId());
			printAttr("description", coll.getDescription());
			super.visit(coll);
			endBlock();

		}

		@Override
		public void visit(DnaComponent component) {
			startBlock("DnaComponent");
			printAttr("uri", component.getURI());
			printAttr("type", component.getTypes());
			printAttr("displayId", component.getDisplayId());
			printAttr("description", component.getDescription());
			if (component.getDnaSequence() != null) {
				visit(component.getDnaSequence());
			}
			java.util.Collection<SequenceAnnotation> annotations = component.getAnnotations();
			if (!annotations.isEmpty()) {
				startBlock("annotations:");
				for (SequenceAnnotation sequenceAnnotation : annotations) {
					visit(sequenceAnnotation);
				}
				endBlock();
			}
			endBlock();
		}

		@Override
		public void visit(DnaSequence sequence) {
			startBlock("DnaSequence");
			printAttr("uri", sequence.getURI());
			printAttr("nucleotides", sequence.getNucleotides());
			endBlock();
		}

		@Override
		public void visit(SequenceAnnotation annotation) {
			startBlock("SequenceAnnotation");
			printAttr("uri", annotation.getURI());
			printAttr("precedes", annotation.getPrecedes());
			printAttr("biosStart", annotation.getBioStart());
			printAttr("bioEnd", annotation.getBioEnd());
			printAttr("strand", annotation.getStrand());
			DnaComponent subComponent = annotation.getSubComponent();
			if (subComponent != null) {
				startBlock("subComponent:");
				visit(subComponent);
				endBlock();
			}
			endBlock();
		}
	}
}

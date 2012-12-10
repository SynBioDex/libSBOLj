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

package org.sbolstandard.core.impl;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URI;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;

import org.sbolstandard.core.Collection;
import org.sbolstandard.core.DnaComponent;
import org.sbolstandard.core.DnaSequence;
import org.sbolstandard.core.SBOLDocument;
import org.sbolstandard.core.SBOLFactory;
import org.sbolstandard.core.SBOLObject;
import org.sbolstandard.core.SBOLValidationException;
import org.sbolstandard.core.SBOLValidator;
import org.sbolstandard.core.SequenceAnnotation;
import org.sbolstandard.core.util.SBOLBaseVisitor;

public class SBOLValidatorImpl implements SBOLValidator {
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void validate(SBOLDocument doc) throws SBOLValidationException {
		try {
			SBOLFactory.write(doc, new ByteArrayOutputStream());
			validateWithoutSchema(doc);
		}
		catch (IOException e) {
			throw new SBOLValidationException(e);
		}
	}

	/**
	 * 
	 */
	public void validateWithoutSchema(SBOLDocument doc) throws SBOLValidationException {
		Validator validator = new Validator();
		validator.visit(doc);
	}

	private static class Validator extends SBOLBaseVisitor {
		private LinkedHashSet<SBOLObject> visited = new LinkedHashSet<SBOLObject>();
		private Map<URI, SBOLObject> uris = new HashMap<URI, SBOLObject>();
		private Map<String, SBOLObject> displayIds = new HashMap<String, SBOLObject>();
		private DnaComponent parentComponent;

		private void assertTrue(boolean condition, String message, java.util.Collection<? extends SBOLObject> objects) {
			if (!condition) {
				throw new SBOLValidationException(message, objects);
			}
		}

		private void assertTrue(boolean condition, String message, SBOLObject... objects) {
			if (!condition) {
				throw new SBOLValidationException(message, objects);
			}
		}

		private void markVisited(SBOLObject obj) {
			assertTrue(visited.add(obj), "Cyclic object reference", visited);

			SBOLObject prev = uris.put(obj.getURI(), obj);
			assertTrue(prev == null || prev.equals(obj), "Multiple objects with same URI", obj);
		}

		private void unmarkVisited(SBOLObject obj) {
			visited.remove(obj);
		}

		private void checkDisplayId(String displayId, SBOLObject obj) {
			SBOLObject prev = displayIds.put(displayId, obj);
			assertTrue(prev == null || prev.equals(obj), "Multiple objects with same displayId: " + displayId, obj,
			           prev);
		}

		private void checkPrecedeCycle(SequenceAnnotation annotation, LinkedHashSet<SequenceAnnotation> path) {
			assertTrue(path.add(annotation), "Cyclic precedes relation", path);

			for (SequenceAnnotation sa : annotation.getPrecedes()) {
				checkPrecedeCycle(sa, path);
			}

			path.remove(annotation);
		}

		private void checkPositionConsistency(SequenceAnnotation annotation) {
			Integer bioEnd = annotation.getBioEnd();
			if (bioEnd != null) {
				Integer bioStart = annotation.getBioStart();
				int expectedLength = bioEnd - bioStart + 1;
				assertTrue(expectedLength > 0, "Inconsistent bioStart and bioEnd values", annotation);

				DnaSequence dnaSequence = annotation.getSubComponent().getDnaSequence();
				if (dnaSequence != null) {
					String sequence = dnaSequence.getNucleotides();

					assertTrue(expectedLength == sequence.length(),
					           "DnaSequence length does not match bioStart and bioEnd values", annotation);

					DnaSequence parentDnaSequence = parentComponent.getDnaSequence();
					if (parentDnaSequence != null) {
						String parentSequence = parentDnaSequence.getNucleotides();

						assertTrue(sequence.equals(parentSequence.substring(bioStart - 1, bioEnd)),
						           "Sequence in the annotation does not match the parent DnaComponent sequence",
						           annotation);
					}
				}

				for (SequenceAnnotation sa : annotation.getPrecedes()) {
					bioStart = sa.getBioStart();
					assertTrue(bioStart == null || bioStart > bioEnd, "Inconsistent precedes and relative position",
					           annotation, sa);
				}
			}
		}

		@Override
		public void visit(Collection coll) {
			markVisited(coll);
			checkDisplayId(coll.getDisplayId(), coll);

			super.visit(coll);
			
			unmarkVisited(coll);
		}

		@Override
		public void visit(DnaSequence sequence) {
			markVisited(sequence);
			
			super.visit(sequence);
			
			unmarkVisited(sequence);
		}

		@Override
		public void visit(DnaComponent component) {
			markVisited(component);
			checkDisplayId(component.getDisplayId(), component);

			DnaComponent previous = parentComponent;
			parentComponent = component;
			super.visit(component);
			parentComponent = previous;
			unmarkVisited(component);
		}

		@Override
		public void visit(SequenceAnnotation annotation) {
			markVisited(annotation);

			checkPrecedeCycle(annotation, new LinkedHashSet<SequenceAnnotation>());
			checkPositionConsistency(annotation);

			super.visit(annotation);
			
			unmarkVisited(annotation);
		}
	}
}
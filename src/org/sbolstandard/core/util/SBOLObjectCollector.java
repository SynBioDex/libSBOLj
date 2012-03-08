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

import java.net.URI;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.sbolstandard.core.Collection;
import org.sbolstandard.core.DnaComponent;
import org.sbolstandard.core.DnaSequence;
import org.sbolstandard.core.SBOLObject;
import org.sbolstandard.core.SBOLVisitable;
import org.sbolstandard.core.SequenceAnnotation;

/**
 * Utility class to find all the objects contained in an SBOL object.
 * 
 * @author Evren Sirin
 */
public class SBOLObjectCollector {
	/**
	 * Convenience function for {@link #collectFrom(Iterable)} function. 
	 */ 
	public static Map<URI, SBOLObject> collectFrom(SBOLVisitable... visitables) {
		return collectFrom(Arrays.asList(visitables));
	}

	/**
	 * Collects all the objects contained in the given list of SBOL objects. Containment is defined similar to how the
	 * objects would be nested in the XML serialization. For example, a {@link DnaComponent} contains its
	 * {@link DnaSequence} and all its {@link SequenceAnnotation} objects. Reference does not imply containment. A
	 * {@code SequenceAnnotation} does not contain the other {@code SequenceAnnotation}s it
	 * {@link SequenceAnnotation#getPrecedes()} precedes.
	 * 
	 * @param visitables list of objects to collect the 
	 * @return a mapping from URIs to objects
	 */
	public static Map<URI, SBOLObject> collectFrom(Iterable<SBOLVisitable> visitables) {
		Collector collector = new Collector();
		for (SBOLVisitable visitable : visitables) {
			visitable.accept(collector);
		}
		return collector.map;
	}

	private static class Collector extends SBOLBaseVisitor {
		private Map<URI, SBOLObject> map = new HashMap<URI, SBOLObject>();

		private void add(SBOLObject obj) {
			map.put(obj.getURI(), obj);
		}

		@Override
		public void visit(Collection coll) {
			super.visit(coll);
			add(coll);
		}

		@Override
		public void visit(DnaComponent component) {
			super.visit(component);
			add(component);
		}

		@Override
		public void visit(DnaSequence sequence) {
			super.visit(sequence);
			add(sequence);
		}

		@Override
		public void visit(SequenceAnnotation annotation) {
			super.visit(annotation);
			add(annotation);
		}

	}
}

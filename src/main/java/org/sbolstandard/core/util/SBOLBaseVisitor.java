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

import org.sbolstandard.core.Collection;
import org.sbolstandard.core.DnaComponent;
import org.sbolstandard.core.DnaSequence;
import org.sbolstandard.core.SBOLDocument;
import org.sbolstandard.core.SBOLRootObject;
import org.sbolstandard.core.SBOLVisitor;
import org.sbolstandard.core.SequenceAnnotation;

/**
 * Convenience class to make implementing {@link SBOLVisitor} interface easier. This default implementation simply calls
 * the visit function recursively for the objects contained in an SBOL object. So visiting an {@link SBOLDocument} will
 * automatically visit all its root objects which will recursively visit all th eobjects contained within the root
 * objects. 
 * 
 * @author Evren Sirin
 */
public class SBOLBaseVisitor implements SBOLVisitor {
	/**
	 * {@inheritDoc}
	 */
	public void visit(SBOLDocument doc) {
		for (SBOLRootObject rootObj : doc.getContents()) {
			rootObj.accept(this);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public void visit(Collection coll) {
		for (DnaComponent component : coll.getComponents()) {
			visit(component);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public void visit(DnaComponent component) {
		for (SequenceAnnotation sequenceAnnotation : component.getAnnotations()) {
			visit(sequenceAnnotation);
		}
		if (component.getDnaSequence() != null) {
			visit(component.getDnaSequence());
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public void visit(DnaSequence sequence) {
		// nothing to do here
	}

	/**
	 * {@inheritDoc}
	 */
	public void visit(SequenceAnnotation annotation) {
		if (annotation.getSubComponent() != null) {
			visit(annotation.getSubComponent());
		}
	}
}

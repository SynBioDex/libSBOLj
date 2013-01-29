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

package org.sbolstandard.core;

import java.util.List;

import org.sbolstandard.core.util.SBOLObjectCollector;

/**
 * The top level container for SBOL objects. This class represents how SBOL objects are grouped in a serialized file.
 * 
 * @author Evren Sirin
 */
public interface SBOLDocument extends SBOLVisitable {
	/**
	 * Returns the top-level objects contained in this document. Top level objects may contain other SBOL objects. For
	 * example, a {@link DnaComponent} may contain a {@link DnaSequence} and multiple {@link SequenceAnnotation}s which
	 * will not be included in the results of this function. To get all the objects contained in a document you can use
	 * the {@link SBOLObjectCollector} function.
	 */
	public List<SBOLRootObject> getContents();

	/**
	 * Adds a new top level object to the document. Only top-level objects need to be added explicitly to a document.
	 */
	public void addContent(SBOLRootObject obj);

	/**
	 * Removes a top level object from the document.
	 */
	public void removeContent(SBOLRootObject obj);
}
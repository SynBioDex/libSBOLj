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


/**
 * Visitor interface for SBOL objects to support the <a href="http://en.wikipedia.org/wiki/Visitor_pattern">visitor
 * pattern</a>.
 * 
 * @see SBOLVisitable
 * @author Evren Sirin
 */
public interface SBOLVisitor<T extends Throwable> {
	public void visit(SBOLDocument doc) throws T;

	public void visit(Collection coll) throws T;

	public void visit(DnaComponent component) throws T;

	public void visit(DnaSequence sequence) throws T;

	public void visit(SequenceAnnotation annotation) throws T;
}
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
 * Marker interface to denote which SBOL objects can be added directly to an {@link SBOLDocument} as top-level objects.
 * Some objects such as {@link SequenceAnnotation}s cannot be top-level objects, thus dos not implement this interface. 
 * 
 * @author Evren Sirin
 */
public interface SBOLRootObject extends SBOLObject {
}

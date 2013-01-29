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
 * Top level interface for objects that can be visited by the {@link SBOLVisitor} class.
 * 
 * NOTE: Implementation of this class does not provide a custom {@link Object#equals(Object)} implementation and uses the
 * default identity equality implementation. 
 * 
 * @author Evren Sirin
 */
public interface SBOLVisitable {
	/**
	 * Calls the appropriate visit function from the {@link SBOLVisitor} class.
     *
     * @param visitor the visitor to accept
     * @throws T if the visitor throws T
	 */
	public <T extends Throwable> void accept(SBOLVisitor<T> visitor) throws T;
	
	
	/**
	 * The default equals implementation which returns <code>true</code> only if the given object is <code>==</code> to
	 * this object.
	 */
	public boolean equals(Object obj);
}

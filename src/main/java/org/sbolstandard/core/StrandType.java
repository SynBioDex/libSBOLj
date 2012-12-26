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
 * Enumeration representing the valid values of a strand type.
 * 
 * @author Evren Sirin
 */
public enum StrandType {
	/**
	 * Represents <code>+</code> strand which is 5' to 3'.
	 */
	POSITIVE("+"), 
	
	/**
	 * Represents <code>-</code> strand which is 3' to 5'.
	 */
	NEGATIVE("-");
	
	private String symbol;
	
	private StrandType(String symbol) {
		this.symbol = symbol;
	}
	
	/**
	 * Returns the symbol (<code>+</code> or <code>-</code>) for this strand type. 
	 */
	public String getSymbol() {
		return symbol;
	}
	
	@Override
	public String toString() {
		return symbol;
	}
}

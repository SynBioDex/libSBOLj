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

import java.net.URI;

import javax.xml.bind.annotation.adapters.XmlAdapter;

import org.sbolstandard.core.StrandType;

/**
 * XmlAdapter interfaces used by JAXB serialization code.
 *  
 * @author Evren Sirin
 */
public class XmlAdapters {
	// This class only provides static fields, cannot be instantiated
	private XmlAdapters() {		
	}
	
	public static class IntegerAdapter extends XmlAdapter<String, Integer> {
		public Integer unmarshal(String value) {
			return (javax.xml.bind.DatatypeConverter.parseInt(value));
		}

		public String marshal(Integer value) {
			if (value == null) {
				return null;
			}
			return (javax.xml.bind.DatatypeConverter.printInt(value));
		}
	}

	public static class URIAdapter extends XmlAdapter<String, URI> {
		public URI unmarshal(String value) {
			return URI.create(value);
		}

		public String marshal(URI value) {
			if (value == null) {
				return null;
			}
			return value.toASCIIString();
		}
	}

	public static class StrandTypeAdapter extends XmlAdapter<String, StrandType> {
		public StrandType unmarshal(String value) {
			if (value.equals("+")) {
				return StrandType.POSITIVE;
			}
			if (value.equals("-")) {
				return StrandType.NEGATIVE;
			}
			throw new IllegalArgumentException("Invalid stardn value: " + value);
		}

		public String marshal(StrandType value) {
			if (value == null) {
				return null;
			}
			return value.toString();
		}
	}
}

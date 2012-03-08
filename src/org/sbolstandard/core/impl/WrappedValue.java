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

import javax.xml.bind.annotation.XmlTransient;


/**
 * A simple utility class to wrap a given value without any additional functionality. The purpose of this class is to
 * handle JAXB marshalling/unmarshalling where every XML element needs to be mapped to a distinct Java object whereas
 * in SBOl serialization there are various superfluous XML elements. 
 * 
 * @author Evren Sirin
 */
@XmlTransient
public class WrappedValue<T> {
	@XmlTransient
	private T value;
	
	public WrappedValue() {		
	}
	
	public void setValue(T value) {
		this.value = value;
	}
	
	public T getValue() {
		return value;
	}

	@Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((value == null) ? 0 : value.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
	        return true;
        if (obj == null)
	        return false;
        if (getClass() != obj.getClass())
	        return false;
        WrappedValue other = (WrappedValue) obj;
        if (value == null) {
	        if (other.value != null)
		        return false;
        }
        else if (!value.equals(other.value))
	        return false;
        return true;
    }
}

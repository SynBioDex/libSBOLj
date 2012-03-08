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

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import org.sbolstandard.core.SBOLDocument;
import org.sbolstandard.core.SBOLRootObject;
import org.sbolstandard.core.SBOLVisitor;


/**
 * 
 * @author Evren Sirin
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "rootObjects"
})
@XmlRootElement(name = "RDF", namespace = "http://www.w3.org/1999/02/22-rdf-syntax-ns#")
public class SBOLDocumentImpl extends SBOLVisitableImpl implements SBOLDocument {
    @XmlElements({
        @XmlElement(name = "Collection", type = CollectionImpl.class),
        @XmlElement(name = "DnaComponent", type = DnaComponentImpl.class),
        @XmlElement(name = "DnaSequence", type = DnaSequenceImpl.class)
    })
    protected List<SBOLRootObject> rootObjects;

    /**
     * {@inheritDoc}
     */
	@Override
    public List<SBOLRootObject> getContents() {
        if (rootObjects == null) {
            rootObjects = new ArrayList<SBOLRootObject>();
        }
        return this.rootObjects;
    }

	/**
     * {@inheritDoc}
     */
	@Override
	public void addContent(SBOLRootObject obj) {
		if (obj == null) {
			throw new NullPointerException();
		}
		
		getContents().add(obj);
	}

	/**
     * {@inheritDoc}
     */
	@Override
	public void removeContent(SBOLRootObject obj) {
		if (obj == null) {
			throw new NullPointerException();
		}
		
		getContents().remove(obj);
	}

	/**
     * {@inheritDoc}
     */
    @Override
    public void accept(SBOLVisitor visitor) {
	    visitor.visit(this);
    }
}

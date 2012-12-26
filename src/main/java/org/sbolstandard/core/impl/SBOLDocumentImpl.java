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
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import org.sbolstandard.core.*;
import org.sbolstandard.core.util.SBOLBaseVisitor;


/**
 * 
 * @author Evren Sirin
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "rootObjects"
})
@XmlRootElement(name = "RDF", namespace = "http://www.w3.org/1999/02/22-rdf-syntax-ns#")
public class SBOLDocumentImpl extends SBOLVisitableImpl implements SBOLDocument, SbolResolver {
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

    @Override
    @Transient
    public UriResolver<Collection> getCollectionUriResolver() {
        return new UriResolver<Collection>() {
            @Override
            public Collection resolve(final URI uri) {
                final List<Collection> found = new ArrayList<Collection>();

                accept(new SBOLBaseVisitor() {
                    @Override
                    public void visit(Collection coll) {
                        if(coll.getURI().equals(uri))
                            found.add(coll);
                    }
                });

                // fixme: should merge these
                return found.isEmpty() ? null : found.get(0);
            }
        };
    }

    @Override
    @Transient
    public UriResolver<DnaComponent> getComponentUriResolver() {
        return new UriResolver<DnaComponent>() {
            @Override
            public DnaComponent resolve(final URI uri) {
                final List<DnaComponent> found = new ArrayList<DnaComponent>();

                accept(new SBOLBaseVisitor() {
                    @Override
                    public void visit(DnaComponent dc) {
                        if(dc.getURI().equals(uri))
                            found.add(dc);
                    }
                });

                // fixme: should merge these
                return found.isEmpty() ? null : found.get(0);
            }
        };
    }

    @Override
    @Transient
    public UriResolver<DnaSequence> getSequenceUriResolver() {
        return new UriResolver<DnaSequence>() {
            @Override
            public DnaSequence resolve(final URI uri) {
                final List<DnaSequence> found = new ArrayList<DnaSequence>();

                accept(new SBOLBaseVisitor() {
                    @Override
                    public void visit(DnaSequence ds) {
                        if(ds.getURI().equals(uri))
                            found.add(ds);
                    }
                });

                // fixme: should merge these
                return found.isEmpty() ? null : found.get(0);
            }
        };
    }

    @Override
    @Transient
    public UriResolver<SequenceAnnotation> getAnnotationUriResolver() {
        return new UriResolver<SequenceAnnotation>() {
            @Override
            public SequenceAnnotation resolve(final URI uri) {
                final List<SequenceAnnotation> found = new ArrayList<SequenceAnnotation>();

                accept(new SBOLBaseVisitor() {
                    @Override
                    public void visit(SequenceAnnotation annotation) {
                        if(annotation.getURI().equals(uri))
                            found.add(annotation);
                    }
                });

                // fixme: should merge these
                return found.isEmpty() ? null : found.get(0);
            }
        };
    }

    @Override
    @Transient
    public DisplayIdResolver<Collection> getCollectionDisplayIdResolver() {
        return new DisplayIdResolver<Collection>() {
            @Override
            public Collection resolve(final String displayId) {
                final List<Collection> found = new ArrayList<Collection>();

                accept(new SBOLBaseVisitor() {
                    @Override
                    public void visit(Collection coll) {
                        if(coll.getDisplayId().equals(displayId))
                            found.add(coll);
                    }
                });

                // fixme: should merge these
                return found.isEmpty() ? null : found.get(0);
            }
        };
    }

    @Override
    @Transient
    public DisplayIdResolver<DnaComponent> getComponentDisplayIdResolver() {
        return new DisplayIdResolver<DnaComponent>() {
            @Override
            public DnaComponent resolve(final String displayId) {
                final List<DnaComponent> found = new ArrayList<DnaComponent>();

                accept(new SBOLBaseVisitor() {
                    @Override
                    public void visit(DnaComponent component) {
                        if(component.getDisplayId().equals(displayId))
                            found.add(component);
                    }
                });

                // fixme: should merge these
                return found.isEmpty() ? null : found.get(0);
            }
        };
    }
}

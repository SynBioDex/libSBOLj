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

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import org.sbolstandard.core.DnaComponent;
import org.sbolstandard.core.DnaSequence;
import org.sbolstandard.core.SBOLVisitor;
import org.sbolstandard.core.SequenceAnnotation;
import org.sbolstandard.core.impl.XmlAdapters.URIAdapter;
import org.w3c.dom.Element;

/**
 * @author Evren Sirin
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DnaComponent", propOrder = { "types", "displayId", "name", "description", "dnaSequence", "annotations" })
public class DnaComponentImpl extends SBOLObjectImpl implements DnaComponent {
	@XmlElement(name = "type", namespace = "http://www.w3.org/1999/02/22-rdf-syntax-ns#")
	protected final List<URIWrapper> types = new ArrayList<URIWrapper>();
	@XmlTransient
	protected final WrappedList<URI, URIWrapper> wrappedTypes = new WrappedList<URI, URIWrapper>(URIWrapper.class,
	                types);
	@XmlElement(required = true)
	protected String displayId;
	protected String name;
	protected String description;
	protected DnaSequenceWrapper dnaSequence;
	@XmlElement(name = "annotation")
	protected final List<SequenceAnnotationWrapper> annotations = new ArrayList<SequenceAnnotationWrapper>();
	@XmlTransient
	protected final WrappedList<SequenceAnnotationImpl, SequenceAnnotationWrapper> wrappedAnnotations = new WrappedList<SequenceAnnotationImpl, SequenceAnnotationWrapper>(
	                SequenceAnnotationWrapper.class, annotations);
	@XmlTransient
	protected List<Element> any;
	
    /**
     * {@inheritDoc}
     */
	@Override
	public List<URI> getTypes() {
		return this.wrappedTypes;
	}

	/**
     * {@inheritDoc}
     */
	@Override
	public void addType(URI type) {
		getTypes().add(type);
	}

	/**
     * {@inheritDoc}
     */
	@Override
	public void removeType(URI type) {
		getTypes().remove(type);
	}

	/**
     * {@inheritDoc}
     */
	@Override
	public String getDisplayId() {
		return displayId;
	}

	/**
     * {@inheritDoc}
     */
	@Override
	public void setDisplayId(String value) {
		this.displayId = value;
	}

	/**
     * {@inheritDoc}
     */
	@Override
	public String getName() {
		return name;
	}

	/**
     * {@inheritDoc}
     */
	@Override
	public void setName(String value) {
		this.name = value;
	}

	/**
     * {@inheritDoc}
     */
	@Override
	public String getDescription() {
		return description;
	}

	/**
     * {@inheritDoc}
     */
	@Override
	public void setDescription(String value) {
		this.description = value;
	}

	/**
     * {@inheritDoc}
     */
	@Override
	public DnaSequenceImpl getDnaSequence() {
		return dnaSequence == null ? null : dnaSequence.getValue();
	}

	/**
     * {@inheritDoc}
     */
	@Override
	public void setDnaSequence(DnaSequence value) {
		dnaSequence = new DnaSequenceWrapper();
		dnaSequence.setValue((DnaSequenceImpl) value);
	}

	/**
     * {@inheritDoc}
     */
	@Override
	@SuppressWarnings("unchecked")
	public List<SequenceAnnotation> getAnnotations() {
		return (List) this.wrappedAnnotations;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void addAnnotation(SequenceAnnotation annotation) {
		getAnnotations().add(annotation);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void removeAnnotation(SequenceAnnotation annotation) {
		getAnnotations().remove(annotation);
	}

	public List<Element> getAny() {
		if (any == null) {
			any = new ArrayList<Element>();
		}
		return this.any;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void accept(SBOLVisitor visitor) {
		visitor.visit(this);
	}

	@XmlAccessorType(XmlAccessType.NONE)
	public static class DnaSequenceWrapper extends WrappedValue<DnaSequenceImpl> {
		@XmlElement(name = "DnaSequence", required = true)
		@Override
		public DnaSequenceImpl getValue() {
			return super.getValue();
		}

		@Override
		public void setValue(DnaSequenceImpl value) {
			super.setValue(value);
		}
	}

	@XmlAccessorType(XmlAccessType.NONE)
	public static class SequenceAnnotationWrapper extends WrappedValue<SequenceAnnotationImpl> {
		@XmlElement(name = "SequenceAnnotation")
		@Override
		public SequenceAnnotationImpl getValue() {
			return super.getValue();
		}

		@Override
		public void setValue(SequenceAnnotationImpl value) {
			super.setValue(value);
		}
	}

	@XmlAccessorType(XmlAccessType.PROPERTY)
	@XmlType(name = "")
	public static class URIWrapper extends WrappedValue<URI> {
		@XmlAttribute(name = "resource", namespace = "http://www.w3.org/1999/02/22-rdf-syntax-ns#", required = true)
		@XmlJavaTypeAdapter(URIAdapter.class)
		@Override
		public URI getValue() {
			return super.getValue();
		}

		@Override
		public void setValue(URI value) {
			super.setValue(value);
		}
	}
}

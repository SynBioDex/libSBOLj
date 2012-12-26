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
import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import org.sbolstandard.core.DnaComponent;
import org.sbolstandard.core.SBOLVisitor;
import org.sbolstandard.core.SequenceAnnotation;
import org.sbolstandard.core.StrandType;
import org.sbolstandard.core.impl.XmlAdapters.URIAdapter;
import org.w3c.dom.Element;

/**
 * 
 * @author Evren Sirin
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SequenceAnnotation", propOrder = { "precedes", "bioStart", "bioEnd", "strand", "subComponent", "any" })
@XmlRootElement
public class SequenceAnnotationImpl extends SBOLObjectImpl implements SequenceAnnotation {

	protected final List<PrecedeReference> precedes = new ArrayList<PrecedeReference>();
	@XmlTransient
	protected final WrappedList<SequenceAnnotation, PrecedeReference> wrappedPrecedes = new WrappedList<SequenceAnnotation, PrecedeReference>(
	                PrecedeReference.class, precedes);
	@XmlElement(type = String.class)
	@XmlJavaTypeAdapter(XmlAdapters.IntegerAdapter.class)
	@XmlSchemaType(name = "positiveInteger")
	protected Integer bioStart;
	@XmlElement(type = String.class)
	@XmlJavaTypeAdapter(XmlAdapters.IntegerAdapter.class)
	@XmlSchemaType(name = "positiveInteger")
	protected Integer bioEnd;
	@XmlJavaTypeAdapter(XmlAdapters.StrandTypeAdapter.class)
	protected StrandType strand;
	@XmlElement(required = true)
	protected DnaComponentWrapper subComponent;
	@XmlAnyElement
	protected List<Element> any;

	/**
     * {@inheritDoc}
     */
	@Override
	public Integer getBioStart() {
		return bioStart;
	}

	/**
     * {@inheritDoc}
     */
	@Override
	public void setBioStart(Integer value) {
		this.bioStart = value;
	}

	/**
     * {@inheritDoc}
     */
	@Override
	public Integer getBioEnd() {
		return bioEnd;
	}

	/**
     * {@inheritDoc}
     */
	@Override
	public void setBioEnd(Integer value) {
		this.bioEnd = value;
	}

	/**
     * {@inheritDoc}
     */
	@Override
	public StrandType getStrand() {
		return strand;
	}

	/**
     * {@inheritDoc}
     */
	@Override
	public void setStrand(StrandType value) {
		this.strand = value;
	}

	public List<Element> getAny() {
		if (any == null) {
			any = new ArrayList<Element>();
		}
		return this.any;
	}

	/**
	 * Required for JAXB use.
	 */
	public List<PrecedeReference> getPrecedeReferences() {
		return precedes;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void addPrecede(SequenceAnnotation precede) {
		wrappedPrecedes.add(precede);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void removePrecede(SequenceAnnotation precede) {
		wrappedPrecedes.remove(precede);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<SequenceAnnotation> getPrecedes() {
		return wrappedPrecedes;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setSubComponent(DnaComponent subComponent) {
		this.subComponent = new DnaComponentWrapper();
		this.subComponent.value = (DnaComponentImpl) subComponent;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public DnaComponent getSubComponent() {
		return subComponent == null ? null : subComponent.value;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void accept(SBOLVisitor visitor) {
		visitor.visit(this);
	}

	@XmlAccessorType(XmlAccessType.FIELD)
	@XmlType(name = "")
	public static class DnaComponentWrapper {
		@XmlElement(name = "DnaComponent", required = true)
		private DnaComponentImpl value;
	}

	@XmlAccessorType(XmlAccessType.FIELD)
	@XmlType(name = "")
	public static class PrecedeReference extends WrappedValue<SequenceAnnotation> {
		@XmlAttribute(name = "resource", namespace = "http://www.w3.org/1999/02/22-rdf-syntax-ns#", required = true)
		@XmlJavaTypeAdapter(URIAdapter.class)
		@XmlSchemaType(name = "anyURI")
		protected URI resource;

		PrecedeReference() {
		}

		@Override
		public void setValue(SequenceAnnotation annotation) {
			super.setValue(annotation);
			resource = annotation.getURI();
		}
	}

}

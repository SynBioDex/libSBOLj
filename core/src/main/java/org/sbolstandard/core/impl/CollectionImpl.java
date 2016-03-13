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
import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

import org.sbolstandard.core.Collection;
import org.sbolstandard.core.DnaComponent;
import org.sbolstandard.core.SBOLVisitor;
import org.w3c.dom.Element;

/**
 * JAXB implementation of an SBOL collection.
 * 
 * @author Evren Sirin
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Collection", propOrder = { "displayId", "name", "description", "components", "any" })
public class CollectionImpl extends SBOLObjectImpl implements Collection {

	@XmlElement(required = true)
	protected String displayId;
	protected String name;
	protected String description;
	@XmlElement(name = "component")
	protected final List<DnaComponentWrapper> components = new ArrayList<>();
	@XmlTransient
	protected final WrappedList<DnaComponentImpl, DnaComponentWrapper> wrappedComponents = new WrappedList<>(
			DnaComponentWrapper.class, components);
	@XmlAnyElement
	protected List<Element> any;

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
	@SuppressWarnings("unchecked")
	public List<DnaComponent> getComponents() {
		return (List) wrappedComponents;
	}

	public List<Element> getAny() {
		if (any == null) {
			any = new ArrayList<>();
		}
		return this.any;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void addComponent(DnaComponent component) {
		getComponents().add(component);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void removeComponent(DnaComponent component) {
		getComponents().remove(component);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public <T extends Throwable> void accept(SBOLVisitor<T> visitor) throws T {
		visitor.visit(this);
	}

	@XmlAccessorType(XmlAccessType.PROPERTY)
	@XmlType(name = "")
	public static class DnaComponentWrapper extends WrappedValue<DnaComponentImpl> {
		@XmlElement(name = "DnaComponent")
		@Override
		public DnaComponentImpl getValue() {
			return super.getValue();
		}

		@Override
		public void setValue(DnaComponentImpl value) {
			super.setValue(value);
		}
	}

}

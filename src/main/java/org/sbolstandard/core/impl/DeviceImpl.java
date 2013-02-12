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

import org.sbolstandard.core.Device;
import org.sbolstandard.core.SBOLVisitor;
import org.sbolstandard.core.impl.XmlAdapters.URIAdapter;
import org.w3c.dom.Element;

/**
 * @author Ernst Oberortner
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Device", propOrder = { "displayId", "name", "description" })
public class DeviceImpl 
		extends SBOLObjectImpl 
		implements Device {
	
	@XmlElement(required = true)
	protected String displayId;
	protected String name;
	protected String description;
	
	@XmlTransient
	protected List<Element> any;   // ???
	
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
	public <T extends Throwable> void accept(SBOLVisitor<T> visitor) throws T {
		//visitor.visit(this);
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

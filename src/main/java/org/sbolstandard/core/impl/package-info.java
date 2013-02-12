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

/**
 * Provides the standard implementations of libSBOL interfaces based on JAXB.
 */
@XmlSchema(
	namespace = "http://sbols.org/v1#", 
	elementFormDefault = XmlNsForm.QUALIFIED,
        xmlns = {
    @XmlNs(prefix = "rdf", namespaceURI = "http://www.w3.org/1999/02/22-rdf-syntax-ns#"),
    @XmlNs(prefix = "so", namespaceURI = "http://purl.obolibrary.org/obo/"),
    @XmlNs(prefix = "s", namespaceURI = "http://sbols.org/v1#")
} //,
//elementFormDefault = XmlNsForm.UNQUALIFIED,
//attributeFormDefault = XmlNsForm.UNSET
)
package org.sbolstandard.core.impl;

import javax.xml.bind.annotation.XmlNs;
import javax.xml.bind.annotation.XmlNsForm;
import javax.xml.bind.annotation.XmlSchema;


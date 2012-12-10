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
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

import org.sbolstandard.core.DnaSequence;
import org.sbolstandard.core.SBOLVisitor;
import org.w3c.dom.Element;


/**
 * @author Evren Sirin
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DnaSequence", propOrder = {
	"uri",			
    "nucleotides"
})
public class DnaSequenceImpl extends SBOLObjectImpl implements DnaSequence {
    @XmlElement(required = true)
    protected String nucleotides;
    @XmlTransient
    protected List<Element> any;

    public DnaSequenceImpl() {
    }

    /**
     * {@inheritDoc}
     */
	@Override
    public String getNucleotides() {
        return nucleotides;
    }

	/**
     * {@inheritDoc}
     */
	@Override
    public void setNucleotides(String value) {
        this.nucleotides = value;
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
}

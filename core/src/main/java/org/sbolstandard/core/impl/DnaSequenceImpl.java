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
	
	public String getReverseComplementaryNucleotides() {
		StringBuilder complementary = new StringBuilder(nucleotides.length());
		for (int i = nucleotides.length() - 1; i >= 0; i--) {
			char nucleotide = nucleotides.charAt(i);
			if (nucleotide == 'a')
				complementary.append('t');
			else if (nucleotide == 't')
				complementary.append('a');
			else if (nucleotide == 'g')
				complementary.append('c');
			else if (nucleotide == 'c')
				complementary.append('g');
			else if (nucleotide == 'm')
				complementary.append('k');
			else if (nucleotide == 'r')
				complementary.append('y');
			else if (nucleotide == 'w')
				complementary.append('w');
			else if (nucleotide == 's')
				complementary.append('s');
			else if (nucleotide == 'y')
				complementary.append('r');
			else if (nucleotide == 'k')
				complementary.append('m');
			else if (nucleotide == 'v')
				complementary.append('b');
			else if (nucleotide == 'h')
				complementary.append('d');
			else if (nucleotide == 'd')
				complementary.append('h');
			else if (nucleotide == 'b')
				complementary.append('v');
			else if (nucleotide == 'n')
				complementary.append('n');
		}
		return complementary.toString();
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
            any = new ArrayList<>();
        }
        return this.any;
    }

	/**
     * {@inheritDoc}
     */
    @Override
    public <T extends Throwable> void accept(SBOLVisitor<T> visitor) throws T {
	    visitor.visit(this);
    }
}

package org.sbolstandard.regulatory.impl;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import org.sbolstandard.core.SBOLVisitor;
import org.sbolstandard.core.SequenceAnnotation;
import org.sbolstandard.core.impl.SBOLObjectImpl;
import org.sbolstandard.regulatory.Regulation;
import org.sbolstandard.regulatory.RegulationType;

/**
 * @author Ernst Oberortner
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Regulation")
public class RegulationImpl 
	extends SBOLObjectImpl 
	implements Regulation {

	@XmlElement(required = true)
	protected String displayId;
	protected String name;
	protected String description;
	
	protected SequenceAnnotation leftAnnotation;
	protected SequenceAnnotation rightAnnotation;
	protected RegulationType regulationType;
	
	@Override
	public String getDescription() {
		return this.description;
	}

	@Override
	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String getDisplayId() {
		return this.displayId;
	}

	@Override
	public void setDisplayId(String displayId) {
		this.displayId = displayId;
	}

	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public <T extends Throwable> void accept(SBOLVisitor<T> visitor) throws T {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setRegulation(
			SequenceAnnotation leftAnnotation, 
			RegulationType regulationType,
			SequenceAnnotation rightAnnotation) {
		this.leftAnnotation = leftAnnotation;
		this.regulationType = regulationType;
		this.rightAnnotation = rightAnnotation;
	}
	
	public SequenceAnnotation getLeftAnnotation() {
		return this.leftAnnotation;
	}
	
	public RegulationType getRegulationType() {
		return this.regulationType;
	}
	
	public SequenceAnnotation getRightAnnotation() {
		return this.rightAnnotation;
	}
	
}

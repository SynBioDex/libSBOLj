package org.sbolstandard.regulatory.impl;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import org.sbolstandard.core.SBOLVisitor;
import org.sbolstandard.core.impl.SBOLObjectImpl;
import org.sbolstandard.regulatory.Regulation;
import org.sbolstandard.regulatory.RegulatoryDevice;

/**
 * @author Ernst Oberortner
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RegulatoryDevice")
public class RegulatoryDeviceImpl 
	extends SBOLObjectImpl 
	implements RegulatoryDevice {

	@XmlElement(required = true)
	protected String displayId;
	protected String name;
	protected String description;
	
	private List<Regulation> regulations = 
			new ArrayList<Regulation>();
	
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
	public List<Regulation> getRegulations() {
		return this.regulations;
	}
	
}

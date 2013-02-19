package org.sbolstandard.regulatory.impl;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.sbolstandard.core.SBOLVisitor;
import org.sbolstandard.regulatory.Regulation;
import org.sbolstandard.regulatory.RegulatoryDevice;

/**
 * @author Ernst Oberortner
 */
public class RegulatoryDeviceImpl 
	implements RegulatoryDevice {

	protected String displayId;
	protected String name;
	protected String description;
	protected URI uri;
	
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

	@Override
	public URI getURI() {
		return this.uri;
	}

	@Override
	public void setURI(URI uri) {
		this.uri = uri;
	}
	
}

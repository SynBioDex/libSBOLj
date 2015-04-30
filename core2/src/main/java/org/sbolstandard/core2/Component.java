package org.sbolstandard.core2;

import java.net.URI;

import static org.sbolstandard.core2.URIcompliance.*;

public class Component extends ComponentInstance{

	public Component(URI identity, AccessType access, URI componentDefinition) {
		super(identity, access, componentDefinition);
	}
	
	protected Component(Component component) {
		super(component);
	}

	@Override
	protected Component deepCopy() {
		return new Component(this); 
	}

	/**
	 * Assume this Component object and all its descendants (children, grand children, etc) have compliant URI, and all given parameters have compliant forms.
	 * This method is called by {@link ComponentDefinition#copy(String, String, String)}.
	 */
	void updateCompliantURI(String URIprefix, String parentDisplayId, String version) {
		String thisObjDisplayId = extractDisplayId(this.getIdentity()); // 1 indicates that this object is a child of a top-level object.
		URI newIdentity = URI.create(URIprefix + '/' + parentDisplayId + '/' 
				+ thisObjDisplayId + '/' + version);
		if (!this.getMapsTos().isEmpty()) {
			// Update children's URIs
			for (MapsTo mapsTo : this.getMapsTos()) {
				mapsTo.updateCompliantURI(URIprefix, parentDisplayId, thisObjDisplayId, version);
			}
		}
		// TODO: need to set wasDerivedFrom here?
		this.setWasDerivedFrom(this.getIdentity());
		this.setIdentity(newIdentity);		
	}
}

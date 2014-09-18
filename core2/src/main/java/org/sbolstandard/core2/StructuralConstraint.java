package org.sbolstandard.core2;

import java.net.URI;

import org.sbolstandard.core2.abstract_classes.Identified;

public class StructuralConstraint extends Identified {

	private URI restriction;
	private StructuralInstantiation subject;
	private StructuralInstantiation object;
	
	public StructuralConstraint(URI identity, URI persistentIdentity, String version,
			URI restriction, StructuralInstantiation subject, StructuralInstantiation object) {
		super(identity, persistentIdentity, version);
		this.restriction = restriction;
		this.subject = subject;
		this.object = object;
	}

	public URI getRestriction() {
		return restriction;
	}

	public void setRestriction(URI restriction) {
		this.restriction = restriction;
	}

	public StructuralInstantiation getSubject() {
		return subject;
	}

	public void setSubject(StructuralInstantiation subject) {
		this.subject = subject;
	}

	public StructuralInstantiation getObject() {
		return object;
	}

	public void setObject(StructuralInstantiation object) {
		this.object = object;
	}

	@Override
	public <T extends Throwable> void accept(SBOLVisitor<T> visitor) throws T {
		// TODO Auto-generated method stub
		
	}

}

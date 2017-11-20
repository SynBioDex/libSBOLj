package org.sbolstandard.core2;

import static org.sbolstandard.core2.URIcompliance.createCompliantURI;
import static org.sbolstandard.core2.URIcompliance.extractDisplayId;

import java.net.URI;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * Represents a VariableComponent in the SBOL data model
 * 
 * @author Zach Zundel
 * @version 2.1
 */

public class VariableComponent extends Identified {
	HashSet<URI> variants;
	HashSet<URI> variantCollections;
	HashSet<URI> variantDerivations;
	URI variable;
	
	public VariableComponent(URI identity, URI variable) throws SBOLValidationException {
		super(identity);
		this.variable = variable;
		this.variants = new HashSet<>();
		this.variantCollections = new HashSet<>();
		this.variantDerivations = new HashSet<>();
	}
	
	private VariableComponent(VariableComponent variableComponent) throws {
		super(variableComponent.getIdentity());
		
		this.variable = variableComponent.variable;
		this.variants = variableComponent.variants;
		this.variantCollections = variableComponent.variantCollections;
		this.variantDerivations = variableComponent.variantDerivations;
	}

	public void addVariant(URI variant) {
		variants.add(variant);
	}
	
	public void addVariantCollection(URI variantCollection) {
		variantCollections.add(variantCollection);
	}
	
	public void addVariantDerivation(URI variantDerivation) {
		variantDerivations.add(variantDerivation);
	}
	
	public void addVariant(String uriPrefix, String displayId, String version) throws SBOLValidationException {
		URI uri = URIcompliance.createCompliantURI(uriPrefix, displayId, version);
		
		ComponentDefinition componentDefinition = this.getSBOLDocument().getComponentDefinition(uri);
		variants.add(componentDefinition.getIdentity());		
	}
	
	public void addVariantCollection(String uriPrefix, String displayId, String version) throws SBOLValidationException {
		URI uri = URIcompliance.createCompliantURI(uriPrefix, displayId, version);
		
		Collection collection = this.getSBOLDocument().getCollection(uri);
		variantCollections.add(collection.getIdentity());		
	}
	
	public void addVariantDerivation(String uriPrefix, String displayId, String version) throws SBOLValidationException {
		URI uri = URIcompliance.createCompliantURI(uriPrefix, displayId, version);
		
		CombinatorialDerivation combinatorialDerivation = this.getSBOLDocument().getCombinatorialDerivation(uri);
		variants.add(combinatorialDerivation.getIdentity());		
	}

	@Override
	Identified deepCopy() throws SBOLValidationException {
		return new VariableComponent(this);
	}
}
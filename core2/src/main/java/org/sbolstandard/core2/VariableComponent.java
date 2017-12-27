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
	OperatorType operator;
	
	public VariableComponent(URI identity, URI variable, OperatorType operator) throws SBOLValidationException {
		super(identity);
		this.variable = variable;
		this.operator = operator;
		this.variants = new HashSet<>();
		this.variantCollections = new HashSet<>();
		this.variantDerivations = new HashSet<>();
	}
	
	private VariableComponent(VariableComponent variableComponent) throws SBOLValidationException {
		super(variableComponent.getIdentity());
		
		this.variable = variableComponent.variable;
		this.operator = variableComponent.operator;
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
	
	public URI getVariable() {
		return this.variable;
	}
	
	public OperatorType getOperator() {
		return this.operator;
	}
	
	public Set<URI> getVariantURIs() {
		return new HashSet<URI>(this.variants);
	}
	
	public Set<URI> getVariantCollections () {
		return new HashSet<URI>(this.variantCollections);
	}
	
	public Set<URI> getVariantDerivations () {
		return new HashSet<URI>(this.variantDerivations);
	}
	
	public void setVariants(Set<URI> variants) {
		this.variants = (HashSet<URI>) variants;
	}
	
	public void setVariantCollections(Set<URI> variantCollections) {
		this.variantCollections = (HashSet<URI>) variantCollections;
	}
	
	public void setVariantDerivations(Set<URI> variantDerivations) {
		this.variantDerivations = (HashSet<URI>) variantDerivations;
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
	
	@Override
	public String toString() {
		return super.toString() 
				+ ", operator=" + this.getOperator()
				+ ", variable=" + this.getVariable()
				+ (variants.size()>0?", variants=" + variants:"")
				+ (variantCollections.size()>0?", variantCollections=" + variantCollections:"")
				+ (variantDerivations.size()>0?", variantDeriviations=" + variantDerivations:"");
	}
}


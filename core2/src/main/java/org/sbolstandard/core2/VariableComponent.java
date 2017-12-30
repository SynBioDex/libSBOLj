package org.sbolstandard.core2;

import static org.sbolstandard.core2.URIcompliance.createCompliantURI;

import java.net.URI;
import java.util.HashSet;
import java.util.Set;

/**
 * Represents a VariableComponent in the SBOL data model
 * 
 * @author Zach Zundel
 * @author Igor Durovic
 * @version 2.1
 */

public class VariableComponent extends Identified {
	private HashSet<URI> variants;
	private HashSet<URI> variantCollections;
	private HashSet<URI> variantDerivations;
	private URI variable;
	private OperatorType operator;

	/**
	 * Parent combinatorial derivation of this variable component
	 */
	private CombinatorialDerivation combinatorialDerivation = null;

	/**
	 * @param identity
	 * @param operator
	 * @param variable
	 *            the referenced component
	 * @throws SBOLValidationException
	 *             if an SBOL validation rule violation occurred in
	 *             {@link Identified#Identified(URI)}
	 */
	VariableComponent(URI identity, OperatorType operator, URI variable) throws SBOLValidationException {
		super(identity);
		this.variable = variable;
		this.operator = operator;
		this.variants = new HashSet<>();
		this.variantCollections = new HashSet<>();
		this.variantDerivations = new HashSet<>();
	}

	/**
	 * @param variableComponent
	 * @throws SBOLValidationException
	 *             if an SBOL validation rule violation occurred in any of the
	 *             following constructors or methods:
	 * 
	 *             TODO: copy over objects instead of references like in
	 *             combinatorial derivation
	 */
	private VariableComponent(VariableComponent variableComponent) throws SBOLValidationException {
		super(variableComponent.getIdentity());

		this.variable = variableComponent.variable;
		this.operator = variableComponent.operator;
		this.variants = variableComponent.variants;
		this.variantCollections = variableComponent.variantCollections;
		this.variantDerivations = variableComponent.variantDerivations;
	}

	/**
	 * Sets the parent combinatorial derivation to the given one.
	 *
	 * @param combinatorialDerivation
	 *            the given combinatorial derivation to set to
	 */
	public void setCombinatorialDerivation(CombinatorialDerivation combinatorialDerivation) {
		this.combinatorialDerivation = combinatorialDerivation;
	}

	/**
	 * Adds the given variant URI to the list of variant URIs.
	 * 
	 * @param variant
	 * 			variant to be added
	 */
	public void addVariant(URI variant) {
		variants.add(variant);
	}

	/**
	 * Adds the given variant collection URI to the list of variant collection URIs.
	 * 
	 * @param variantCollection
	 * 			variant collection to be added
	 */
	public void addVariantCollection(URI variantCollection) {
		variantCollections.add(variantCollection);
	}

	/**
	 * Adds the given variant derivation URI to the list of variant derivations
	 * URIs.
	 * 
	 * @param variantDerivation
	 * 			variant derivation to be added
	 */
	public void addVariantDerivation(URI variantDerivation) {
		variantDerivations.add(variantDerivation);
	}

	/**
	 * Returns the instance matching the given variable component's variable.
	 *
	 * @return the matching component if present, or {@code null} otherwise.
	 */
	public Component getVariable() {
		if (combinatorialDerivation==null) return null;
		return combinatorialDerivation.getTemplate().getComponent(variable);
	}
	
	/**
	 * Returns the URI of the instance matching the given variable component's variable.
	 *
	 * @return the matching component if present, or {@code null} otherwise.
	 */
	public URI getVariableURI() {
		return this.variable;
	}

	/**
	 * Sets the variable property to the given one.
	 *
	 * @param variable
	 *            the given component to set to
	 */
	public void setVariable(URI variable) {
		// TODO: validation
		this.variable = variable;
	}

	/**
	 * Returns the operator of the given variable component.
	 *
	 * @return the matching operator if present, or {@code null} otherwise.
	 */
	public OperatorType getOperator() {
		return this.operator;
	}

	/**
	 * Sets the operator property to the given one.
	 *
	 * @param operator
	 *            the given operator type to set to
	 * @throws SBOLValidationException
	 *             if the following SBOL validation rule was violated: TODO: 10607
	 */
	public void setOperator(OperatorType operator) throws SBOLValidationException {
		if (operator == null) {
			throw new SBOLValidationException("sbol-XXXXX", this);
		}

		this.operator = operator;
	}

	/**
	 * Returns a set of component definitions belonging to the variable component.
	 *
	 * @return the matching set of component definitions
	 */
	public Set<ComponentDefinition> getVariants() {
		HashSet<ComponentDefinition> tempVariants = new HashSet<>();

		for (URI variantURI : variants) {
			tempVariants.add(this.getSBOLDocument().getComponentDefinition(variantURI));
		}

		return tempVariants;
	}

	/**
	 * Returns a set of component definition URIs belonging to the variable component.
	 *
	 * @return the matching set of component definition URIs
	 */
	public Set<URI> getVariantURIs() {
		return new HashSet<URI>(this.variants);
	}

	/**
	 * Returns a set of collections belonging to the variable component.
	 *
	 * @return the matching set of collections
	 */
	public Set<Collection> getVariantCollections() {
		HashSet<Collection> tempVariantCollections = new HashSet<>();

		for (URI variantCollectionURI : variantCollections) {
			tempVariantCollections.add(this.getSBOLDocument().getCollection(variantCollectionURI));
		}

		return tempVariantCollections;
	}
	
	/**
	 * Returns a set of collection URIs belonging to the variable component.
	 *
	 * @return the matching set of collection URIs
	 */
	public Set<URI> getVariantCollectionURIs() {
		return new HashSet<URI>(this.variantCollections);
	}

	/**
	 * Returns a set of combinatorial derivations belonging to the variable component.
	 *
	 * @return the matching set of combinatorial derivations
	 */
	public Set<CombinatorialDerivation> getVariantDerivations() {
		HashSet<CombinatorialDerivation> tempVariantDerivations = new HashSet<>();

		for (URI variantDerivationURI : variantDerivations) {
			tempVariantDerivations.add(this.getSBOLDocument().getCombinatorialDerivation(variantDerivationURI));
		}

		return tempVariantDerivations;
	}
	
	/**
	 * Returns a set of combinatorial derivation URIs belonging to the variable component.
	 *
	 * @return the matching set of combinatorial derivation URIs
	 */
	public Set<URI> getVariantDerivationURIs() {
		return new HashSet<URI>(this.variantDerivations);
	}

	/**
	 * @param variants
	 * TODO: validation (null check)
	 */
	public void setVariants(Set<URI> variants) {
		this.variants = (HashSet<URI>) variants;
	}

	/**
	 * @param variantCollections
	 * TODO: validation (null check)
	 */
	public void setVariantCollections(Set<URI> variantCollections) {
		this.variantCollections = (HashSet<URI>) variantCollections;
	}

	/**
	 * @param variantDerivations
	 * TODO: validation (null check)
	 */
	public void setVariantDerivations(Set<URI> variantDerivations) {
		this.variantDerivations = (HashSet<URI>) variantDerivations;
	}

	/**
	 * Adds the given variant to the list of variants.
	 * 
	 * @param uriPrefix
	 * 			TODO: change variable name
	 * @param displayId
	 * 			display id for variant
	 * @param version
	 * 			version for variant
	 * @throws SBOLValidationException 
	 */
	public void addVariant(String uriPrefix, String displayId, String version) throws SBOLValidationException {
		URI uri = URIcompliance.createCompliantURI(uriPrefix, displayId, version);

		ComponentDefinition componentDefinition = this.getSBOLDocument().getComponentDefinition(uri);
		variants.add(componentDefinition.getIdentity());
	}

	/**
	 * Adds the given variant collection to the list of variant collections.
	 * 
	 * @param uriPrefix
	 * 			TODO: change variable name
	 * @param displayId
	 * @param version
	 * @throws SBOLValidationException 
	 */
	public void addVariantCollection(String uriPrefix, String displayId, String version)
			throws SBOLValidationException {
		URI uri = URIcompliance.createCompliantURI(uriPrefix, displayId, version);

		Collection collection = this.getSBOLDocument().getCollection(uri);
		variantCollections.add(collection.getIdentity());
	}

	/**
	 * Adds the given variant derivation to the list of variant derivations.
	 * 
	 * @param uriPrefix
	 * 			TODO: change variable name
	 * @param displayId
	 * @param version
	 * @throws SBOLValidationException 
	 */
	public void addVariantDerivation(String uriPrefix, String displayId, String version)
			throws SBOLValidationException {
		URI uri = URIcompliance.createCompliantURI(uriPrefix, displayId, version);

		CombinatorialDerivation combinatorialDerivation = this.getSBOLDocument().getCombinatorialDerivation(uri);
		variants.add(combinatorialDerivation.getIdentity());
	}

	/**
	 * Removes the given component definition from the list of variants.
	 *
	 * @param variant
	 *            a component definition be removed
	 * @return {@code true} if the matching component definition is removed
	 *         successfully, {@code false} otherwise.
	 */
	public boolean removeVariant(ComponentDefinition variant) {
		return variants.remove(variant.getIdentity());
	}

	/**
	 * Removes the component definition with the given URI from the list of
	 * variants.
	 *
	 * @param variantURI
	 *            a component definition URI be removed
	 * @return {@code true} if the matching component definition is removed
	 *         successfully, {@code false} otherwise.
	 */
	public boolean removeVariantURI(URI variantURI) {
		return variants.remove(variantURI);
	}

	/**
	 * removes all entries of this variable component's set of variant URIs.
	 *
	 */
	public void clearVariants() {
		variants.clear();
	}

	/**
	 * Removes the given collection from the list of variantCollections.
	 *
	 * @param variantCollection
	 *            a collection to be removed
	 * @return {@code true} if the matching collection is removed successfully,
	 *         {@code false} otherwise.
	 */
	public boolean removeVariantCollection(Collection variantCollection) {
		return variantCollections.remove(variantCollection.getIdentity());
	}

	/**
	 * Removes the collection with the given URI from the list of
	 * variantCollections.
	 *
	 * @param variantCollectionURI
	 *            a collection URI to be removed
	 * @return {@code true} if the matching collection is removed successfully,
	 *         {@code false} otherwise.
	 */
	public boolean removeVariantCollectionURI(URI variantCollectionURI) {
		return variantCollections.remove(variantCollectionURI);
	}

	/**
	 * removes all entries of this variable component's set of variant collection
	 * URIs.
	 *
	 */
	public void clearVariantCollections() {
		variantCollections.clear();
	}

	/**
	 * Removes the given combinatorial derivation from the list of variant
	 * derivations.
	 *
	 * @param variantDerivation
	 *            a combinatorial derivation to be removed
	 * @return {@code true} if the matching combinatorial derivation is removed
	 *         successfully, {@code false} otherwise.
	 */
	public boolean removeVariantDerivation(CombinatorialDerivation variantDerivation) {
		return variantDerivations.remove(variantDerivation.getIdentity());
	}

	/**
	 * Removes the combinatorial derivation with the given URI from the list of
	 * variant derivations.
	 *
	 * @param variantDerivationURI
	 *            a combinatorial derivation URI to be removed
	 * @return {@code true} if the matching combinatorial derivation is removed
	 *         successfully, {@code false} otherwise.
	 */
	public boolean removeVariantDerivationURI(URI variantDerivationURI) {
		return variantDerivations.remove(variantDerivationURI);
	}

	/**
	 * removes all entries of this variable component's set of variant derivation
	 * URIs.
	 *
	 */
	public void clearVariantDerivations() {
		variantDerivations.clear();
	}

	/**
	 * Updates this variable component's and each of its member identity URIs with
	 * compliant URIs.
	 * 
	 * @throws SBOLValidationException
	 *             if any of the following SBOL validation rules was violated:
	 *             <ul>
	 *             <li>{@link URIcompliance#createCompliantURI(String, String, String)};</li>
	 *             <li>{@link #setWasDerivedFrom(URI)};</li>
	 *             <li>{@link #setIdentity(URI)};</li>
	 *             <li>{@link #setDisplayId(String)};</li>
	 *             <li>{@link #setVersion(String)};</li>
	 *             </ul>
	 */
	void updateCompliantURI(String URIprefix, String displayId, String version) throws SBOLValidationException {
		if (!this.getIdentity().equals(createCompliantURI(URIprefix, displayId, version))) {
			this.addWasDerivedFrom(this.getIdentity());
		}
		this.setIdentity(createCompliantURI(URIprefix, displayId, version));
		this.setPersistentIdentity(createCompliantURI(URIprefix, displayId, ""));
		this.setDisplayId(displayId);
		this.setVersion(version);

		// TODO: update URIs for variants, variantCollections, variantDerivations,
		// and/or variable?
	}

	/**
	 * @throws SBOLValidationException
	 *             if an SBOL validation rule violation occurred in
	 *             {@link #VariableComponent(VariableComponent)}.
	 */
	VariableComponent deepCopy() throws SBOLValidationException {
		return new VariableComponent(this);
	}

	@Override
	public String toString() {
		return "VariableComponent [" +
				super.toString() + ", operator=" + this.getOperator() + ", variable=" + this.getVariableURI()
				+ (variants.size() > 0 ? ", variants=" + variants : "")
				+ (variantCollections.size() > 0 ? ", variantCollections=" + variantCollections : "")
				+ (variantDerivations.size() > 0 ? ", variantDeriviations=" + variantDerivations : "")
				+ "]" + (combinatorialDerivation==null? combinatorialDerivation.getIdentity() + " " + combinatorialDerivation.getTemplateURI():"");
	}
}

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
 * @author Igor Durovic
 * @version 2.1
 */

public class VariableComponent extends Identified {
	private HashSet<URI> variants;
	private HashSet<URI> variantCollections;
	private HashSet<URI> variantDerivations;
	private Component variable;
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
	public VariableComponent(URI identity, OperatorType operator, Component variable) throws SBOLValidationException {
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

	public void setCombinatorialDerivation(CombinatorialDerivation combinatorialDerivation) {
		this.combinatorialDerivation = combinatorialDerivation;
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

	public Component getVariable() {
		return this.variable;
	}

	/**
	 * Sets the variable property to the given one.
	 *
	 * @param variable
	 *            the given component to set to
	 * @throws SBOLValidationException
	 *             if either of the following SBOL validation rules was violated:
	 *             TODO: 10604, 10605.
	 */
	public void setVariable(Component variable) throws SBOLValidationException {
		//TODO: validation
		this.variable = variable;
	}

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

	public Set<CombinatorialDerivation> getVariants() {
		HashSet<CombinatorialDerivation> tempVariants = new HashSet<>();

		for (URI variantURI : variants) {
			variants.add(this.getSBOLDocument().getCombinatorialDerivation(variantURI).getIdentity());
		}

		return tempVariants;
	}

	public Set<URI> getVariantURIs() {
		return new HashSet<URI>(this.variants);
	}

	public Set<URI> getVariantCollections() {
		return new HashSet<URI>(this.variantCollections);
	}

	public Set<URI> getVariantDerivations() {
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

	public void addVariantCollection(String uriPrefix, String displayId, String version)
			throws SBOLValidationException {
		URI uri = URIcompliance.createCompliantURI(uriPrefix, displayId, version);

		Collection collection = this.getSBOLDocument().getCollection(uri);
		variantCollections.add(collection.getIdentity());
	}

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
		return super.toString() + ", operator=" + this.getOperator() + ", variable=" + this.getVariable()
				+ (variants.size() > 0 ? ", variants=" + variants : "")
				+ (variantCollections.size() > 0 ? ", variantCollections=" + variantCollections : "")
				+ (variantDerivations.size() > 0 ? ", variantDeriviations=" + variantDerivations : "");
	}
}


package org.sbolstandard.core2;

import static org.sbolstandard.core2.URIcompliance.createCompliantURI;
import static org.sbolstandard.core2.URIcompliance.isChildURIcompliant;

import java.net.URI;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

/**
 * Represents a CombinatorialDerivation object in the SBOL data model.
 * 
 * @author Zach Zundel
 * @author Igor Durovic
 * @version 2.1
 */
public class CombinatorialDerivation extends TopLevel {

	private URI template;

	private StrategyType strategy;

	private HashMap<URI, VariableComponent> variableComponents;

	/**
	 * @param identity
	 * @param template
	 * @param strategy
	 * @throws SBOLValidationException
	 *             if an SBOL validation rule violation occurred in the following
	 *             constructor or method:
	 *             <ul>
	 *             <li>{@link TopLevel#TopLevel(URI)}, or</li>
	 *             </ul>
	 */
	public CombinatorialDerivation(URI identity, URI template, StrategyType strategy) throws SBOLValidationException {
		super(identity);

		this.template = template;

		this.strategy = strategy;
		this.variableComponents = new HashMap<>();
	}

	/**
	 * @param componentDefinition
	 * @throws SBOLValidationException
	 *             if an SBOL validation rule violation occurred in any of the
	 *             following constructors or methods:
	 *             <ul>
	 *             <li>{@link TopLevel#TopLevel(TopLevel)},</li>
	 *             <li>{@link #addSVariableComponent(VariableComponent)},</li>
	 *             <li>{@link VariableComponent#deepCopy()}</li>
	 *             </ul>
	 */
	private CombinatorialDerivation(CombinatorialDerivation combinatorialDerivation) throws SBOLValidationException {
		super(combinatorialDerivation);

		this.variableComponents = new HashMap<>();
		for (VariableComponent variableComponent : combinatorialDerivation.getVariableComponents()) {
			this.addVariableComponent(variableComponent.deepCopy());
		}
	}

	/**
	 * Checks if the strategy property is set.
	 * 
	 * @return {@code true} if it is not {@code null}, {@code false} otherwise
	 */
	public boolean isSetStrategy() {
		return strategy != null;
	}

	/**
	 * Adds the given variable component to the list of variable components.
	 * 
	 * @param variableComponent
	 */
	private void addVariableComponent(VariableComponent variableComponent) {
		this.variableComponents.put(variableComponent.getIdentity(), variableComponent);
	}

	/**
	 * Returns the instance matching the given variable component's identity URI.
	 *
	 * @param variableComponentURI
	 *            the identity URI of the variable component to be retrieved
	 * @return the matching variable component if present, or {@code null}
	 *         otherwise.
	 */
	public VariableComponent getVariableComponent(URI variableComponentURI) {
		return this.variableComponents.get(variableComponentURI);
	}

	/**
	 * Returns the variable component matching the given variable component's
	 * display ID.
	 * <p>
	 * This method first creates a compliant URI for the variable component to be
	 * retrieved. It starts with this combinatorial derivation's persistent
	 * identity, followed by the given variable component's display ID, and ends with this
	 * combinatorial derivation's version.
	 * 
	 * @param displayId
	 *            the display ID of the variable component to be retrieved
	 * @return the matching variable component if present, or {@code null} otherwise.
	 */
	public VariableComponent getVariableComponent(String displayId) {
		try {
			return variableComponents
					.get(createCompliantURI(this.getPersistentIdentity().toString(), displayId, this.getVersion()));
		} catch (SBOLValidationException e) {
			return null;
		}
	}

	public Set<VariableComponent> getVariableComponents() {
		Set<VariableComponent> variableComponents = new HashSet<>();
		variableComponents.addAll(this.variableComponents.values());
		return variableComponents;
	}

	void copy(CombinatorialDerivation combinatorialDerivation) throws SBOLValidationException {
		((TopLevel) this).copy((TopLevel) combinatorialDerivation);

		Set<VariableComponent> sourceVariableComponents = combinatorialDerivation.getVariableComponents();

		for (VariableComponent variableComponent : sourceVariableComponents) {
			String displayId = variableComponent.getDisplayId();
			if (displayId == null) {
				displayId = URIcompliance.extractDisplayId(variableComponent.getIdentity());
			}

			this.createVariableComponent(variableComponent.getIdentity(), variableComponent.getVariable(),
					variableComponent.getOperator());
		}

		this.setTemplate(combinatorialDerivation.getTemplateURI());
		this.setStrategy(combinatorialDerivation.getStrategy());
	}

	private VariableComponent createVariableComponent(URI identity, URI variable, OperatorType operator)
			throws SBOLValidationException {
		VariableComponent newVariableComponent = new VariableComponent(identity, variable, operator);
		this.addVariableComponent(identity, newVariableComponent);
		return null;
	}

	public VariableComponent createVariableComponent(String displayId, OperatorType operator, String variableId,
			String version) throws SBOLValidationException {
		URI variableURI = URIcompliance.createCompliantURI(this.getSBOLDocument().getDefaultURIprefix(),
				TopLevel.COMPONENT_DEFINITION, variableId, version, this.getSBOLDocument().isTypesInURIs());
		return createVariableComponent(displayId, variableURI, operator);
	}

	public VariableComponent createVariableComponent(String displayId, OperatorType operator, String variableId)
			throws SBOLValidationException {
		return createVariableComponent(displayId, operator, variableId, "");
	}

	public VariableComponent createVariableComponent(String displayId, URI variableURI, OperatorType operator)
			throws SBOLValidationException {
		if (this.getSBOLDocument() != null && this.getSBOLDocument().isComplete()) {
			if (this.getVariableComponent(variableURI) == null) {
				throw new SBOLValidationException("sbol-XXXXX", this);
			}
		}
		String URIprefix = this.getPersistentIdentity().toString();
		String version = this.getVersion();
		VariableComponent c = createVariableComponent(createCompliantURI(URIprefix, displayId, version), variableURI,
				operator);
		c.setPersistentIdentity(createCompliantURI(URIprefix, displayId, ""));
		c.setDisplayId(displayId);
		c.setVersion(version);
		return c;
	}

	public URI getTemplateURI() {
		return this.template;
	}

	public ComponentDefinition getTemplate() {
		if (this.getSBOLDocument() == null)
			return null;
		return this.getSBOLDocument().getComponentDefinition(template);
	}

	public void setTemplate(String displayId, String version) throws SBOLValidationException {
		URI templateURI = URIcompliance.createCompliantURI(this.getSBOLDocument().getDefaultURIprefix(),
				TopLevel.COMPONENT_DEFINITION, displayId, version, this.getSBOLDocument().isTypesInURIs());

		this.setTemplate(templateURI);
	}

	public void setTemplate(ComponentDefinition template) {
		this.setTemplate(template.getIdentity());
	}

	public void setTemplate(URI template) {
		this.template = template;
	}

	public StrategyType getStrategy() {
		return this.strategy;
	}

	public void setStrategy(StrategyType strategy) {
		this.strategy = strategy;
	}

	public void clearStrategy() {
		this.strategy = null;
	}

	public void checkDescendantsURIcompliance() throws SBOLValidationException {
		if (!variableComponents.isEmpty()) {
			for (VariableComponent variableComponent : variableComponents.values()) {
				try {
					isChildURIcompliant(this, variableComponent);
				} catch (SBOLValidationException e) {
					throw new SBOLValidationException(e.getRule(), variableComponent);
				}
			}
		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode() * prime;

		for (Entry<URI, VariableComponent> entry : this.variableComponents.entrySet()) {
			result *= entry.getValue().hashCode();
			result *= entry.getKey().hashCode();
		}

		result *= this.template != null ? this.template.hashCode() : 1;
		result *= this.strategy != null ? this.strategy.hashCode() : 1;

		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.sbolstandard.core2.abstract_classes.Documented#equals(java.lang.Instance)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		CombinatorialDerivation other = (CombinatorialDerivation) obj;
		if (strategy != other.strategy)
			return false;
		if (template == null) {
			if (other.template != null)
				return false;
		} else if (!template.equals(other.template)) {
			/*
			 * TODO: add getTemplateIdentity method if (getTemplateIdentity() == null ||
			 * other.getTemplateIdentity() == null ||
			 * !getTemplateIdentity().equals(other.getTemplateIdentity())) {
			 */
			return false;
			/* } */

		}
		if (variableComponents == null) {
			if (other.variableComponents != null)
				return false;
		} else if (!variableComponents.equals(other.variableComponents))
			return false;
		return true;
	}

	@Override
	Identified copy(String URIprefix, String displayId, String version) throws SBOLValidationException {
		CombinatorialDerivation combinatorialDerivation = this.getDocument().getCombinatorialDerivation(URIprefix,
				displayId, version);

		return new CombinatorialDerivation(combinatorialDerivation);
	}

	@Override
	Identified deepCopy() throws SBOLValidationException {
		return new CombinatorialDerivation(this);
	}

	public void setVariableComponents(Set<VariableComponent> variableComponents) {
		this.variableComponents.clear();

		for (VariableComponent variableComponent : variableComponents) {
			this.variableComponents.put(variableComponent.getIdentity(), variableComponent);
		}
	}

	@Override
	public String toString() {
		return "CombinatorialDerivation [" + super.toString()
				+ (this.getStrategy() != null ? ", strategy=" + this.getStrategy() : "") + ", template="
				+ this.getTemplateURI()
				+ (this.getVariableComponents().size() > 0 ? ", variableComponents=" + this.getVariableComponents()
						: "")
				+ "]";
	}
}

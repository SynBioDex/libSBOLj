
package org.sbolstandard.core2;

import static org.sbolstandard.core2.URIcompliance.createCompliantURI;
import static org.sbolstandard.core2.URIcompliance.isChildURIcompliant;

import java.net.URI;
import java.util.HashMap;
import java.util.HashSet;
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
	 *            identity of the combinatorial derivation
	 * @param template
	 *            template of the combinatorial derivation
	 * @throws SBOLValidationException
	 *             if an SBOL validation rule violation occurred in the following
	 *             constructor or method:
	 *             <ul>
	 *             <li>{@link TopLevel#TopLevel(URI)}, or</li>
	 *             </ul>
	 */
	CombinatorialDerivation(URI identity, URI template) throws SBOLValidationException {
		super(identity);

		setTemplate(template);
		this.variableComponents = new HashMap<>();
	}

	/**
	 * @param combinatorialDerivation
	 * @throws SBOLValidationException
	 *             if an SBOL validation rule violation occurred in any of the
	 *             following constructors or methods:
	 *             <ul>
	 *             <li>{@link TopLevel#TopLevel(TopLevel)},</li>
	 *             <li>{@link #addVariableComponent(VariableComponent)},</li>
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
	private void addVariableComponent(VariableComponent variableComponent) throws SBOLValidationException {
		variableComponent.setSBOLDocument(this.getSBOLDocument());
		variableComponent.setCombinatorialDerivation(this);
		ComponentDefinition template = this.getTemplate();
		if (template != null) {
			if (template.getComponent(variableComponent.getVariableURI())==null) {
				throw new SBOLValidationException("sbol-13005",this);
			}
		}
		if (strategy == StrategyType.ENUMERATE) {
			if (variableComponent.getOperator().equals(OperatorType.ONEORMORE) ||
					variableComponent.getOperator().equals(OperatorType.ZEROORMORE)) {
				throw new SBOLValidationException("sbol-12903",this);
			}
		}
		for (VariableComponent varComp : this.getVariableComponents()) {
			if (varComp.getVariableURI().equals(variableComponent.getVariableURI())) {
				throw new SBOLValidationException("sbol-12907",this);
			}
		}
		for (URI cdURI : variableComponent.getVariantDerivationURIs()) {
			if (this.getSBOLDocument() != null && this.getSBOLDocument().isComplete()) {
				CombinatorialDerivation cd = this.getSBOLDocument().getCombinatorialDerivation(cdURI);
				if (cd!=null &&	this.getIdentity().equals(cd.getIdentity())) {
					throw new SBOLValidationException("sbol-13015", variableComponent);
				}
				Set<URI> visited = new HashSet<>();
				visited.add(this.getIdentity());
				try {
					SBOLValidate.checkCombinatorialDerivationCycle(this.getSBOLDocument(), cd, visited);
				} catch (SBOLValidationException e) {
					throw new SBOLValidationException("sbol-13015", variableComponent);
				}
			}
		}
		addChildSafely(variableComponent, variableComponents, "variableComponent");
	}

	/**
	 * Adds the given variable component to the list of variable components.
	 * 
	 * @param variableComponent
	 */
	private void addVariableComponentNoCheck(VariableComponent variableComponent) throws SBOLValidationException {
		variableComponent.setSBOLDocument(this.getSBOLDocument());
		variableComponent.setCombinatorialDerivation(this);
		ComponentDefinition template = this.getTemplate();
		if (template !=null) {
			if (template.getComponent(variableComponent.getVariableURI())==null) {
				throw new SBOLValidationException("sbol-13005",this);
			}
		}
		if (strategy == StrategyType.ENUMERATE) {
			if (variableComponent.getOperator().equals(OperatorType.ONEORMORE) ||
					variableComponent.getOperator().equals(OperatorType.ZEROORMORE)) {
				throw new SBOLValidationException("sbol-12903",this);
			}
		}
		for (VariableComponent varComp : this.getVariableComponents()) {
			if (varComp.getVariableURI().equals(variableComponent.getVariableURI())) {
				throw new SBOLValidationException("sbol-12907",this);
			}
		}
		for (URI cdURI : variableComponent.getVariantDerivationURIs()) {
			if (this.getSBOLDocument() != null && this.getSBOLDocument().isComplete()) {
				CombinatorialDerivation cd = this.getSBOLDocument().getCombinatorialDerivation(cdURI);
				if (cd!=null &&	this.getIdentity().equals(cd.getIdentity())) {
					throw new SBOLValidationException("sbol-13015", variableComponent);
				}
			}
		}
		addChildSafely(variableComponent, variableComponents, "variableComponent");
	}
	
	/**
	 * Removes the given variable component from the list of variable components.
	 * 
	 * @param variableComponent
	 *            the given variable component
	 * @return {@code true} if the matching variable component was removed
	 *         successfully, {@code false} otherwise.
	 */
	public boolean removeVariableComponent(VariableComponent variableComponent) {
		return removeChildSafely(variableComponent, variableComponents);
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
	 * identity, followed by the given variable component's display ID, and ends
	 * with this combinatorial derivation's version.
	 * 
	 * @param displayId
	 *            the display ID of the variable component to be retrieved
	 * @return the matching variable component if present, or {@code null}
	 *         otherwise.
	 */
	public VariableComponent getVariableComponent(String displayId) {
		try {
			return variableComponents
					.get(createCompliantURI(this.getPersistentIdentity().toString(), displayId, this.getVersion()));
		} catch (SBOLValidationException e) {
			return null;
		}
	}

	/**
	 * Returns the set of variable components owned by this combinatorial
	 * derivation.
	 *
	 * @return the set of variable components owned by this combinatorial
	 *         derivation.
	 */
	public Set<VariableComponent> getVariableComponents() {
		Set<VariableComponent> variableComponents = new HashSet<>();
		variableComponents.addAll(this.variableComponents.values());
		return variableComponents;
	}

	/**
	 * Removes all entries of this combinatorial derivation's list of variable
	 * components. The list will be empty after this call returns.
	 * <p>
	 * This method calls
	 * {@link #removeVariableComponent(VariableComponent variableComponent)} to
	 * iteratively remove each variable component.
	 */	
	public void clearVariableComponents(){
		Object[] valueSetArray = variableComponents.values().toArray();
		for (Object variableComponent : valueSetArray) {
			removeVariableComponent((VariableComponent) variableComponent);
		}
	}

	/**
	 * Clears the existing set of variable components first, and then adds the given
	 * set of the variable components to this combinatorial derivation.
	 *
	 * @param variableComponents The set of variable components for this combinatorial derivation.
	 * @throws SBOLValidationException if an SBOL validation rule violation occurred in any of the following methods:
	 * <ul>
	 * <li>{@link #clearVariableComponents()} or</li>
	 * <li>{@link #addVariableComponent(VariableComponent)}</li>
	 * </ul>
	 */
	public void setVariableComponents(Set<VariableComponent> variableComponents) throws SBOLValidationException {
		clearVariableComponents();
		for (VariableComponent variableComponent : variableComponents) {
			addVariableComponentNoCheck(variableComponent);
		}
	}

	void copy(CombinatorialDerivation combinatorialDerivation) throws SBOLValidationException {
		((TopLevel) this).copy((TopLevel) combinatorialDerivation);
		
		if (combinatorialDerivation.isSetStrategy()) {
			this.setStrategy(combinatorialDerivation.getStrategy());
		}
		for (VariableComponent variableComponent : combinatorialDerivation.getVariableComponents()) {
			String displayId = URIcompliance.findDisplayId(variableComponent);
			VariableComponent newVariableComponent = this.createVariableComponent(
					displayId, variableComponent.getOperator(), variableComponent.getVariableURI());
			newVariableComponent.copy(variableComponent);
		}
	}

	/**
	 * Creates a child variable component instance for this combinatorial derivation
	 * instance with the given arguments, and then adds to this combinatorial
	 * derivation's list of variable component instances.
	 *
	 * @param identity
	 *            the identifier for this instance
	 * @param operator
	 *            indicates the operator type of the variable component
	 * @param variable
	 *            the component referenced by the variable component
	 * @return a variable component instance
	 * @throws SBOLValidationException
	 *             if either of the following condition is satisfied:
	 *             <ul>
	 *             <li>an SBOL validation rule violation occurred in
	 *             {@link VariableComponent#VariableComponent(URI, OperatorType, URI)}</li>
	 *             <li>an SBOL validation rule violation occurred in
	 *             {@link #addVariableComponent(VariableComponent)}</li>
	 *             </ul>
	 */
	private VariableComponent createVariableComponent(URI identity, OperatorType operator, URI variable)
			throws SBOLValidationException {
		VariableComponent newVariableComponent = new VariableComponent(identity, operator, variable);
		this.addVariableComponent(newVariableComponent);
		return newVariableComponent;
	}

	/**
	 * Creates a child variable component for this combinatorial derivation with the
	 * given arguments, and then adds to this combinatorial derivation's list of
	 * variable components.
	 * <p>
	 * This method first creates a compliant URI for the child variable component to
	 * be created. This URI starts with this combinatorial derivation's persistent
	 * identity, followed by the given display ID and ends with this combinatorial
	 * derivation's version.
	 * 
	 * @param displayId
	 *            the display ID for the variable component to be created
	 * @param operator
	 *            the operator property for the variable component to be created
	 * @param variable
	 *            the component URI referenced by the variable component to be created
	 * @return the created variable component
	 * @throws SBOLValidationException
	 *             if any of the following SBOL validation rules was violated: 
	 *             10201, 10202, 10204, 10206, 13002, 13003, 13004, 13005.
	 */
	public VariableComponent createVariableComponent(String displayId, OperatorType operator, URI variable)
			throws SBOLValidationException {
		String URIprefix = this.getPersistentIdentity().toString();
		String version = this.getVersion();
		VariableComponent c = createVariableComponent(createCompliantURI(URIprefix, displayId, version), operator,
				variable);
		c.setPersistentIdentity(createCompliantURI(URIprefix, displayId, ""));
		c.setDisplayId(displayId);
		c.setVersion(version);
		return c;
	}
	
	/**
	 * Creates a child variable component for this combinatorial derivation with the
	 * given arguments, and then adds to this combinatorial derivation's list of
	 * variable components.
	 * <p>
	 * This method first creates a compliant URI for the child variable component to
	 * be created. This URI starts with this combinatorial derivation's persistent
	 * identity, followed by the given display ID and ends with this combinatorial
	 * derivation's version.
	 * 
	 * @param displayId
	 *            the display ID for the variable component to be created
	 * @param operator
	 *            the operator property for the variable component to be created
	 * @param variableId 
	 *            the component displayId referenced by the variable component to be created
	 * @return the created variable component
	 * @throws SBOLValidationException
	 *             if any of the following SBOL validation rules was violated: 
	 *             10201, 10202, 10204, 10206, 13002, 13003, 13004, 13005.
	 */
	public VariableComponent createVariableComponent(String displayId, OperatorType operator, String variableId)
			throws SBOLValidationException {
		URI variableURI = URIcompliance.createCompliantURI(getTemplate().getPersistentIdentity().toString(),
				variableId, getTemplate().getVersion());
		return createVariableComponent(displayId, operator, variableURI);
	}


	/**
	 * Returns the reference component definition URI.
	 *
	 * @return the reference component definition URI
	 */
	public URI getTemplateURI() {
		return template;
	}

	/**
	 * Returns the component definition identity referenced by this combinatorial
	 * derivation.
	 *
	 * @return {@code null} if the associated SBOLDocument instance is {@code null}
	 *         or no matching component definition referenced by this combinatorial
	 *         derivation exists; or the matching component definition otherwise.
	 */
	public URI getTemplateIdentity() {
		if (this.getSBOLDocument() == null)
			return null;
		if (this.getSBOLDocument().getComponentDefinition(template) == null)
			return null;
		return this.getSBOLDocument().getComponentDefinition(template).getIdentity();
	}

	/**
	 * Returns the component definition referenced by this combinatorial derivation.
	 *
	 * @return {@code null} if the associated SBOLDocument instance is {@code null}
	 *         or no matching component definition referenced by this combinatorial
	 *         derivation; or the matching component definition otherwise.
	 */
	public ComponentDefinition getTemplate() {
		if (this.getSBOLDocument() == null)
			return null;
		return this.getSBOLDocument().getComponentDefinition(template);
	}

	/**
	 * Sets the template property to the given one.
	 *
	 * @param template
	 *            the given template URI to set to
	 * @throws SBOLValidationException
	 *             if either of the following SBOL validation rules was violated:
	 *             10602, 10604, 12905.
	 */
	public void setTemplate(URI template) throws SBOLValidationException {

		if (template == null) {
			throw new SBOLValidationException("sbol-12904", this);
		}

		if (this.getSBOLDocument() != null && this.getSBOLDocument().isComplete()) {
			if (this.getSBOLDocument().getComponentDefinition(template) == null) {
				throw new SBOLValidationException("sbol-12905", this);
			}
		}

		this.template = template;
	}

	/**
	 * Returns the strategy property.
	 *
	 * @return the strategy property
	 */
	public StrategyType getStrategy() {
		return this.strategy;
	}

	/**
	 * Sets the strategy property to the given one.
	 *
	 * @param strategy
	 *            the given strategy type to set to
	 * @throws SBOLValidationException 
	 * 				on SBOL validation rule violation 12903.
	 */
	public void setStrategy(StrategyType strategy) throws SBOLValidationException {
		if (strategy.equals(StrategyType.ENUMERATE)) {
			for (VariableComponent variableComponent : variableComponents.values()) {
				if (variableComponent.getOperator().equals(OperatorType.ONEORMORE) ||
						variableComponent.getOperator().equals(OperatorType.ZEROORMORE)) {
					throw new SBOLValidationException("sbol-12903",this);
				}
			}
		}
		this.strategy = strategy;
	}

	/**
	 * Sets the strategy property of this combinatorial derivation to {@code null}.
	 */
	public void unsetStrategy() {
		this.strategy = null;
	}

	/**
	 * @throws SBOLValidationException
	 *             an SBOL validation rule violation occurred in either of the
	 *             following methods:
	 *             <ul>
	 *             <li>{@link URIcompliance#isChildURIcompliant(Identified, Identified)}.</li>
	 *             </ul>
	 */
	@Override
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
			if (getTemplateIdentity() == null || other.getTemplateIdentity() == null
					|| !getTemplateIdentity().equals(other.getTemplateIdentity())) {
				return false;
			}

		}
		if (variableComponents == null) {
			if (other.variableComponents != null)
				return false;
		} else if (!variableComponents.equals(other.variableComponents))
			return false;
		return true;
	}

	/**
	 * @throws SBOLValidationException
	 *             if an SBOL validation rule violation occurred in any of the
	 *             following constructors or methods:
	 *             <ul>
	 *             <li>{@link #deepCopy()},</li>
	 *             <li>{@link URIcompliance#createCompliantURI(String, String, String)},</li>
	 *             <li>{@link #setDisplayId(String)},</li>
	 *             <li>{@link #setVersion(String)},</li>
	 *             <li>{@link #setWasDerivedFrom(URI)},</li>
	 *             <li>{@link #setIdentity(URI)}</li>
	 *             <li>{@link VariableComponent#setDisplayId(String)}</li>
	 *             <li>{@link VariableComponent#updateCompliantURI(String, String, String)},</li>
	 *             <li>{@link #addVariableComponent(VariableComponent)},</li>
	 *             </ul>
	 */
	@Override
	CombinatorialDerivation copy(String URIprefix, String displayId, String version) throws SBOLValidationException {
		CombinatorialDerivation cloned = this.deepCopy();
		cloned.setPersistentIdentity(createCompliantURI(URIprefix, displayId, ""));
		cloned.setDisplayId(displayId);
		cloned.setVersion(version);
		URI newIdentity = createCompliantURI(URIprefix, displayId, version);

		if (!this.getIdentity().equals(newIdentity)) {
			cloned.addWasDerivedFrom(this.getIdentity());
		} else {
			cloned.setWasDerivedFroms(this.getWasDerivedFroms());
		}

		cloned.setIdentity(newIdentity);
		int count = 0;
		for (VariableComponent variableComponent : cloned.getVariableComponents()) {
			if (!variableComponent.isSetDisplayId())
				variableComponent.setDisplayId("variableComponent" + ++count);
			variableComponent.updateCompliantURI(cloned.getPersistentIdentity().toString(),
					variableComponent.getDisplayId(), version);
			cloned.removeChildSafely(variableComponent, cloned.variableComponents);
			cloned.addVariableComponent(variableComponent);
		}

		return cloned;
	}

	@Override
	/**
	 * @throws SBOLValidationException
	 *             if an SBOL validation rule violation occurred in
	 *             {@link #CombinatorialDerivation(CombinatorialDerivation)}.
	 */
	CombinatorialDerivation deepCopy() throws SBOLValidationException {
		return new CombinatorialDerivation(this);
	}

	@Override
	public String toString() {
		return "CombinatorialDerivation [" + super.toString()
				+ (this.isSetStrategy() ? ", strategy=" + this.getStrategy() : "") + ", template="
				+ this.getTemplateURI()
				+ (this.getVariableComponents().size() > 0 ? ", variableComponents=" + this.getVariableComponents()
						: "")
				+ "]";
	}
	
	/**
	 * Enumerates this combinatorial and adds the resulting ComponentDefinitions to the containing SBOLDocument.
	 * @return - The list of resulting ComponentDefinitions
	 * @throws SBOLValidationException
	 */
	public Set<ComponentDefinition> enumerate() throws SBOLValidationException {
		return enumerate(getSBOLDocument());
	}
	
	/**
	 * Enumerates this combinatorial and adds the resulting ComponentDefinitions to the given SBOLDocument.
	 * @param document - The document that component definitions will be added to.
	 * @return - The list of resulting ComponentDefinitions
	 * @throws SBOLValidationException
	 */
	public HashSet<ComponentDefinition> enumerate(SBOLDocument document) throws SBOLValidationException{
		return CombinatorialDerivation.CombinatorialExpansionUtil.enumerate(document, this);
	}
	
	/**
	 * Taken from SBOLDesigner
	 */
	static class CombinatorialExpansionUtil {

		private static ComponentDefinition createTemplateCopy(SBOLDocument doc, CombinatorialDerivation derivation)
				throws SBOLValidationException {
			ComponentDefinition template = derivation.getTemplate();

			String uniqueId = getUniqueDisplayId(null, null, template.getDisplayId() + "_GeneratedInstance",
					template.getVersion(), "CD", doc);
			//ComponentDefinition copy = (ComponentDefinition) doc.createCopy(template, uniqueId, template.getVersion());
			ComponentDefinition copy = doc.createComponentDefinition(uniqueId, template.getVersion(), template.getTypes());
			copy.setRoles(template.getRoles());
			Component prev = null;
			Component curr;
			for(Component c : template.getSortedComponents())
			{
				curr = copy.createComponent(c.getDisplayId(), c.getAccess(), c.getDefinitionURI());
				if(prev != null)
				{
					uniqueId = getUniqueDisplayId(copy, null,
							copy.getDisplayId() + "_SequenceConstraint", null, "SequenceConstraint", null);
					copy.createSequenceConstraint(uniqueId, RestrictionType.PRECEDES, prev.getIdentity(),
							curr.getIdentity());
				}
				prev = curr;
			}
			copy.addWasDerivedFrom(template.getIdentity());
			copy.addWasDerivedFrom(derivation.getIdentity());
			for (Component component : copy.getComponents()) {
				component.addWasDerivedFrom(template.getComponent(component.getDisplayId()).getIdentity());
			}

			//copy.clearSequenceAnnotations();

			return copy;
		}

		private static HashSet<ComponentDefinition> enumerate(SBOLDocument doc, CombinatorialDerivation derivation)
				throws SBOLValidationException {
			HashSet<ComponentDefinition> parents = new HashSet<>();
			parents.add(createTemplateCopy(doc, derivation));
			for (VariableComponent vc : derivation.getVariableComponents()) {
				HashSet<ComponentDefinition> newParents = new HashSet<>();
				for (ComponentDefinition parent : parents) {
					for (HashSet<ComponentDefinition> children : group(collectVariants(doc, vc), vc.getOperator())) {
						
						// create copy of parent
						String uniqueId = getUniqueDisplayId(null, null, parent.getDisplayId(),
								parent.getVersion(), "CD", doc);
						ComponentDefinition newParent = (ComponentDefinition) doc.createCopy(parent, uniqueId, "1");

						// add children
						ComponentDefinition template = derivation.getTemplate();
						addChildren(template, template.getComponent(vc.getVariableURI()), newParent, children);

						// add to newParents
						newParents.add(newParent);
					}
				}

				parents = newParents;
			}

			return parents;
		}
		

		private static void addChildren(ComponentDefinition originalTemplate, Component originalComponent,
				ComponentDefinition newParent, HashSet<ComponentDefinition> children) throws SBOLValidationException {
			Component newComponent = newParent.getComponent(originalComponent.getDisplayId());
			newComponent.addWasDerivedFrom(originalComponent.getIdentity());

			if (children.isEmpty()) {
				removeConstraintReferences(newParent, newComponent);
				for (SequenceAnnotation sa : newParent.getSequenceAnnotations()) {
					if (sa.isSetComponent() && sa.getComponentURI().equals(newComponent.getIdentity())) {
						newParent.removeSequenceAnnotation(sa);
					}
				}
				newParent.removeComponent(newComponent);
				return;
			}

			boolean first = true;
			for (ComponentDefinition child : children) {
				if (first) {
					// take over the definition of newParent's version of the
					// original component
					newComponent.setDefinition(child.getIdentity());
					first = false;
				} else {
					// create a new component
					String uniqueId = getUniqueDisplayId(newParent, null, child.getDisplayId() + "_Component",
							"1", "Component", null);
					Component link = newParent.createComponent(uniqueId, AccessType.PUBLIC, child.getIdentity());
					link.addWasDerivedFrom(originalComponent.getIdentity());

					// create a new 'prev precedes link' constraint
					Component oldPrev = getBeforeComponent(originalTemplate, originalComponent);
					if (oldPrev != null) {
						Component newPrev = newParent.getComponent(oldPrev.getDisplayId());
						if (newPrev != null) {
							uniqueId = getUniqueDisplayId(newParent, null,
									newParent.getDisplayId() + "_SequenceConstraint", null, "SequenceConstraint", null);
							newParent.createSequenceConstraint(uniqueId, RestrictionType.PRECEDES, newPrev.getIdentity(),
									link.getIdentity());
						}
					}

					// create a new 'link precedes next' constraint
					Component oldNext = getAfterComponent(originalTemplate, originalComponent);
					if (oldNext != null) {
						Component newNext = newParent.getComponent(oldNext.getDisplayId());
						if (newNext != null) {
							uniqueId = getUniqueDisplayId(newParent, null,
									newParent.getDisplayId() + "_SequenceConstraint", null, "SequenceConstraint", null);
							newParent.createSequenceConstraint(uniqueId, RestrictionType.PRECEDES, link.getIdentity(),
									newNext.getIdentity());
						}
					}
				}
			}
		}

		private static void removeConstraintReferences(ComponentDefinition newParent, Component newComponent) throws SBOLValidationException {
			Component subject = null;
			Component object = null;
			for (SequenceConstraint sc : newParent.getSequenceConstraints()) {
				if (sc.getSubject().equals(newComponent)) {
					object = sc.getObject();
					//If we know what the new subject of this sequence constraint should be, modify it
					if(subject != null) {
						sc.setSubject(subject.getIdentity());
						object = null;
						subject = null;
					}else {//else remove it
						newParent.removeSequenceConstraint(sc);
					}
				}
				if(sc.getObject().equals(newComponent)){
					subject = sc.getSubject();
					//If we know what the new object of this sequence constraint should be, modify it
					if(object != null) {
						sc.setObject(object.getIdentity());
						object = null;
						subject = null;
					}else {//else remove it
						newParent.removeSequenceConstraint(sc);
					}
				}
			}
		}

		private static Component getBeforeComponent(ComponentDefinition template, Component component) {
			for (SequenceConstraint sc : template.getSequenceConstraints()) {
				if (sc.getRestriction().equals(RestrictionType.PRECEDES) && sc.getObject().equals(component)) {
					return sc.getSubject();
				}
			}
			return null;
		}

		private static Component getAfterComponent(ComponentDefinition template, Component component) {
			for (SequenceConstraint sc : template.getSequenceConstraints()) {
				if (sc.getRestriction().equals(RestrictionType.PRECEDES) && sc.getSubject().equals(component)) {
					return sc.getObject();
				}
			}
			return null;
		}

		private static HashSet<HashSet<ComponentDefinition>> group(HashSet<ComponentDefinition> variants,
				OperatorType operator) {
			HashSet<HashSet<ComponentDefinition>> groups = new HashSet<>();

			for (ComponentDefinition CD : variants) {
				HashSet<ComponentDefinition> group = new HashSet<>();
				group.add(CD);
				groups.add(group);
			}

			if (operator == OperatorType.ONE) {
				return groups;
			}

			if (operator == OperatorType.ZEROORONE) {
				groups.add(new HashSet<>());
				return groups;
			}

			groups.clear();
			generateCombinations(groups, variants.toArray(new ComponentDefinition[0]), 0, new HashSet<>());
			if (operator == OperatorType.ONEORMORE) {
				return groups;
			}

			if (operator == OperatorType.ZEROORMORE) {
				groups.add(new HashSet<>());
				return groups;
			}

			throw new IllegalArgumentException(operator.toString() + " operator not supported");
		}

		/**
		 * Generates all combinations except the empty set.
		 */
		private static void generateCombinations(HashSet<HashSet<ComponentDefinition>> groups,
				ComponentDefinition[] variants, int i, HashSet<ComponentDefinition> set) {
			if (i == variants.length) {
				if (!set.isEmpty()) {
					groups.add(set);
				}
				return;
			}

			HashSet<ComponentDefinition> no = new HashSet<>(set);
			generateCombinations(groups, variants, i + 1, no);

			HashSet<ComponentDefinition> yes = new HashSet<>(set);
			yes.add(variants[i]);
			generateCombinations(groups, variants, i + 1, yes);
		}

		private static HashSet<ComponentDefinition> collectVariants(SBOLDocument doc, VariableComponent vc)
				throws SBOLValidationException {
			HashSet<ComponentDefinition> variants = new HashSet<>();

			//Recursively collect variants from possible nested VariantDerivations 
//			for(CombinatorialDerivation cd : vc.getVariantDerivations())
//			{
//				for (VariableComponent v : cd.getVariableComponents()) {
//					variants.addAll(collectVariants(doc, v));
//					
//				}
//			}
			// add all variants
			variants.addAll(vc.getVariants());

			// add all variants from variantCollections
			for (Collection c : vc.getVariantCollections()) {
				for (TopLevel tl : c.getMembers()) {
					if (tl instanceof ComponentDefinition) {
						variants.add((ComponentDefinition) tl);
					}
				}
			}

			// add all variants from variantDerivations
			for (CombinatorialDerivation derivation : vc.getVariantDerivations()) {
				variants.addAll(enumerate(doc, derivation));
			}

			return variants;
		}
		
		/**
		 * Returns an int which guarantees a unique URI. Pass in the parent CD (if
		 * dataType isn't a TopLevel), the displayId you want, the version (if
		 * dataType is a TopLevel), the type of object, and the SBOLDocument
		 * containing the design.
		 * 
		 * @throws SBOLValidationException
		 */
		public static String getUniqueDisplayId(ComponentDefinition comp, CombinatorialDerivation derivation,
				String displayId, String version, String dataType, SBOLDocument design) throws SBOLValidationException {
			// if can get using some displayId, then try the next number
			switch (dataType) {
			case "CD":
				for (int i = 1; true; i++) {
					if (i == 1 && design.getComponentDefinition(displayId, version) == null) {
						return displayId;
					}
					if (design.getComponentDefinition(displayId + i, version) == null) {
						return displayId + i;
					}
				}
			case "SequenceAnnotation":
				for (int i = 1; true; i++) {
					if (i == 1 && comp.getSequenceAnnotation(displayId) == null) {
						return displayId;
					}
					if (comp.getSequenceAnnotation(displayId + i) == null) {
						return displayId + i;
					}
				}
			case "SequenceConstraint":
				for (int i = 1; true; i++) {
					if (i == 1 && comp.getSequenceConstraint(displayId) == null) {
						return displayId;
					}
					if (comp.getSequenceConstraint(displayId + i) == null) {
						return displayId + i;
					}
				}
			case "Component":
				for (int i = 1; true; i++) {
					if (i == 1 && comp.getComponent(displayId) == null) {
						return displayId;
					}
					if (comp.getComponent(displayId + i) == null) {
						return displayId + i;
					}
				}
			case "Sequence":
				for (int i = 1; true; i++) {
					if (i == 1 && design.getSequence(displayId, version) == null) {
						return displayId;
					}
					if (design.getSequence(displayId + i, version) == null) {
						return displayId + i;
					}
				}
			case "Range":
				test: for (int i = 1; true; i++) {
					for (SequenceAnnotation sa : comp.getSequenceAnnotations()) {
						if (i == 1 && sa.getLocation(displayId) != null) {
							continue test;
						}
						if (sa.getLocation(displayId + i) != null) {
							continue test;
						}
					}
					// This will always return Range, Range2, Range3... etc,
					// skipping Range1
					return i == 1 ? displayId : displayId + i;
				}
			case "CombinatorialDerivation":
				for (int i = 1; true; i++) {
					if (i == 1 && design.getCombinatorialDerivation(displayId, version) == null) {
						return displayId;
					}
					if (design.getCombinatorialDerivation(displayId + i, version) == null) {
						return displayId + i;
					}
				}
			case "VariableComponent":
				for (int i = 1; true; i++) {
					if (i == 1 && derivation.getVariableComponent(displayId) == null) {
						return displayId;
					}
					if (derivation.getVariableComponent(displayId + i) == null) {
						return displayId + i;
					}
				}
			default:
				throw new IllegalArgumentException();
			}
		}
	}
}

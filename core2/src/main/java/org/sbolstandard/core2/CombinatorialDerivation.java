
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
	//private URI strategy;
	
	private StrategyType strategy;
	
	private HashMap<URI, VariableComponent> variableComponents;

	/**getComponents
	 * @param identity
	 * @throws SBOLValidationException if an SBOL validation rule violation occurred in the following
	 * constructor or method: 
	 * <ul>
	 * <li>{@link TopLevel#TopLevel(URI)}, or</li>
	 * <li>{@link #setTypes(Set)}.</li>
	 * </ul>
	 */
	public CombinatorialDerivation(URI identity, URI template, StrategyType strategy) throws SBOLValidationException {
		super(identity);

        this.template = template;

        this.strategy = strategy;
		this.variableComponents = new HashMap<>();
	}

	/**
	 * @param combinatorialDerivation
	 * @throws SBOLValidationException if an SBOL validation rule violation occurred in any of the following
	 * constructors or methods:
	 */
	private CombinatorialDerivation(CombinatorialDerivation combinatorialDerivation) throws SBOLValidationException {
		super(combinatorialDerivation);
        
        this.copy(combinatorialDerivation);
	}

	/**
	 * Adds the given variable component to the list of variable components.
	 * @throws SBOLValidationException if either of the following condition is satisfied:
	 * <ul>
	 * <li>any of the following SBOL validation rules was violated: 10604, 10605, 10803</li>
	 * <li>an SBOL validation rule violation occurred in {@link Identified#addChildSafely(Identified, java.util.Map, String, java.util.Map...)}</li>
	 * </ul>
	 */
	private void addVariableComponent(URI uri, VariableComponent variableComponent) {
		this.variableComponents.put(uri, variableComponent);
	}
	
	/**
	 * Returns the instance matching the given variable component's identity URI.
	 *
	 * @param variableComponentURI the identity URI of the variable component to be retrieved
	 * @return the matching variable component if present, or {@code null} otherwise.
	 */
	public VariableComponent getVariableComponent(URI variableComponentURI) {
		return this.variableComponents.get(variableComponentURI);
	}

	/**
	 * Returns the set of variable components owned by this combinatorial derivation.
	 *
	 * @return the set of variable components owned by this
	 *         combinatorial derivation.
	 */
	public Set<VariableComponent> getVariableComponents() {
		Set<VariableComponent> variableComponents = new HashSet<>();
		variableComponents.addAll(this.variableComponents.values());
		return variableComponents;
	}

	/**
     * Write better documentation of this
     */
	void copy(CombinatorialDerivation combinatorialDerivation) throws SBOLValidationException {
		((TopLevel)this).copy((TopLevel)combinatorialDerivation);

        Set<VariableComponent> sourceVariableComponents = combinatorialDerivation.getVariableComponents();
        
        for (VariableComponent variableComponent : sourceVariableComponents) {
			String displayId = variableComponent.getDisplayId();
			if (displayId==null) {
				displayId = URIcompliance.extractDisplayId(variableComponent.getIdentity());
			}
			
			this.createVariableComponent(variableComponent.getIdentity(), variableComponent.getVariable(),
					variableComponent.getOperator());
		}

        this.setTemplate(combinatorialDerivation.getTemplateURI());
        this.setStrategy(combinatorialDerivation.getStrategy());
	}


    private VariableComponent createVariableComponent(URI identity, URI variable, OperatorType operator) throws SBOLValidationException {
		VariableComponent newVariableComponent = new VariableComponent(identity, variable, operator);
    	this.addVariableComponent(identity, newVariableComponent);
    	return null;
	}
    
    /**
	 * Creates a child component for this component definition with the given arguments, 
	 * and then adds it to this component definition's list of components.
	 * <p>
	 * This method first creates a compliant URI for the component definition that is referenced by
	 * the child component to be created, i.e., the child component's definition property. 
	 * This URI starts with the default
	 * URI prefix, which was set in the SBOLDocument instance hosting this component definition, 
	 * followed by the given display ID and ends with {@code version}. 
	 * It then calls {@link #createComponent(String, AccessType, URI)}
	 * with this compliant component URI.
	 *
	 * @param displayId the display ID for the component to be created
	 * @param access the access property for the component to be created
	 * @param definitionId the display ID of the component definition referenced by the component to be created
	 * @param version the version for the component to be created
	 * @return the created component
	 * @throws SBOLValidationException if either of the following condition is satisfied: 
	 * <ul>
	 * <li>if either of the following SBOL validation rules was violated: 10204, 10206; or</li>
	 * <li>an SBOL validation exception occurred in {@link #createComponent(String, AccessType, URI)}</li>
	 * </ul>
	 */
	//TODO
    public VariableComponent createVariableComponent(String displayId, OperatorType operator,
			String variableId, String version) throws SBOLValidationException {
		URI variableURI = URIcompliance.createCompliantURI(this.getSBOLDocument().getDefaultURIprefix(),
				TopLevel.COMPONENT_DEFINITION, variableId, version, this.getSBOLDocument().isTypesInURIs());
		return createVariableComponent(displayId, variableURI, operator);
	}

	/**
	 * Creates a child component for this component definition with the given arguments, 
	 * and then adds it to this component definition's list of components.
	 * <p>
	 * This method calls {@link #createComponent(String, AccessType, URI)}
	 * with the given arguments and an empty string for version.
	 *
	 * @param displayId the display ID for the component to be created
	 * @param access the access property for the component to be created
	 * @param definitionId the display ID of the component definition referenced by the component to be created
	 * @return the created component
	 * @throws SBOLValidationException if an SBOL validation rule violation occurred in {@link #createComponent(String, AccessType, String, String)}
	 */
	public VariableComponent createVariableComponent(String displayId, OperatorType operator, String variableId) throws SBOLValidationException {
		return createVariableComponent(displayId, operator, variableId, "");
	}

	/**
	 * Creates a child component for this component definition with the given arguments, 
	 * and then adds to this component definition's list of components.
	 * <p>
	 * This method first creates a compliant URI for the child component to be created. 
	 * This URI starts with this component definition's persistent identity, 
	 * followed by the given display ID and ends with this component defintion's version. 
	 * 
	 * @param displayId the display ID for the component to be created
	 * @param access the access property for the component to be created
	 * @param definitionURI the URI of the component definition referenced by the component to be created
	 * @return the created component
	 * @throws SBOLValidationException if any of the following SBOL validation rules was violated:
	 * 10201, 10202, 10204, 10206, 10602, 10604, 10605, 10607, 10803.
	 */
	public VariableComponent createVariableComponent(String displayId, URI variableURI, OperatorType operator) throws SBOLValidationException {
		if (this.getSBOLDocument() != null && this.getSBOLDocument().isComplete()) {
			if (this.getVariableComponent(variableURI)==null) {
				throw new SBOLValidationException("sbol-XXXXX",this);
			}
		}
		String URIprefix = this.getPersistentIdentity().toString();
		String version = this.getVersion();
		VariableComponent c = createVariableComponent(createCompliantURI(URIprefix, displayId, version),
				variableURI, operator);
		c.setPersistentIdentity(createCompliantURI(URIprefix, displayId, ""));
		c.setDisplayId(displayId);
		c.setVersion(version);
		return c;
	}

    /**
	 * Returns the template URI for this combinatorial derivation
	 *
	 * @return the template component definition URI
	 */
	public URI getTemplateURI() {
        return this.template;
    }
	
	/**
	 * Returns the template referenced by this combinatorial derivation. 
	 *
	 * @return {@code null} if the associated SBOLDocument instance is {@code null} or no matching
	 * component definition referenced by this template exits; 
	 * or the matching component definition otherwise.
	 */
	public ComponentDefinition getTemplate() {
		if (this.getSBOLDocument()==null) return null;
		return this.getSBOLDocument().getComponentDefinition(template);
	}

    public void setTemplate(String displayId, String version) throws SBOLValidationException {
		URI templateURI = URIcompliance.createCompliantURI(this.getSBOLDocument().getDefaultURIprefix(),
                                                           TopLevel.COMPONENT_DEFINITION, 
                                                           displayId, 
                                                           version, 
                                                           this.getSBOLDocument().isTypesInURIs());

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
        if(!variableComponents.isEmpty()) {
        		for(VariableComponent variableComponent : variableComponents.values()) {
        			try {
    					isChildURIcompliant(this, variableComponent);
    				}
    				catch (SBOLValidationException e) {
    					throw new SBOLValidationException(e.getRule(), variableComponent);
    				}
        		}
        }
    }

    

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode() * prime;

        for(Entry<URI, VariableComponent> entry : this.variableComponents.entrySet()) {
	        	result *= entry.getValue().hashCode();
	        	result *= entry.getKey().hashCode();
        }
        
        result *= this.template != null ? this.template.hashCode() : 1;
        result *= this.strategy != null ? this.strategy.hashCode() : 1;

		return result;
	}

	/* (non-Javadoc)
	 * @see org.sbolstandard.core2.abstract_classes.Documented#equals(java.lang.Instance)
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

        return other.getStrategy().equals(this.strategy) &&
        		other.getTemplate().equals(this.template) &&
        		other.getVariableComponents().equals(this.variableComponents);
	}

	@Override
	Identified copy(String URIprefix, String displayId, String version) throws SBOLValidationException {
		CombinatorialDerivation combinatorialDerivation = 
				this.getDocument().getCombinatorialDerivation(URIprefix, displayId, version);
		
		return new CombinatorialDerivation(combinatorialDerivation);
	}

	@Override
	Identified deepCopy() throws SBOLValidationException {
		return new CombinatorialDerivation(this);
	}

	public void setVariableComponents(Set<VariableComponent> variableComponents) {
		this.variableComponents.clear();
		
		for(VariableComponent variableComponent : variableComponents) {
			this.variableComponents.put(variableComponent.getIdentity(), variableComponent);
		}
	}
}



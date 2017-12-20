
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

	public CombinatorialDerivation(URI identity, URI template, StrategyType strategy) throws SBOLValidationException {
		super(identity);

        this.template = template;

        this.strategy = strategy;
		this.variableComponents = new HashMap<>();
	}

	private CombinatorialDerivation(CombinatorialDerivation combinatorialDerivation) throws SBOLValidationException {
		super(combinatorialDerivation);
        
        this.copy(combinatorialDerivation);
	}

	private void addVariableComponent(URI uri, VariableComponent variableComponent) {
		this.variableComponents.put(uri, variableComponent);
	}
	
	public VariableComponent getVariableComponent(URI variableComponentURI) {
		return this.variableComponents.get(variableComponentURI);
	}

	public Set<VariableComponent> getVariableComponents() {
		Set<VariableComponent> variableComponents = new HashSet<>();
		variableComponents.addAll(this.variableComponents.values());
		return variableComponents;
	}

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
    
    public VariableComponent createVariableComponent(String displayId, OperatorType operator,
			String variableId, String version) throws SBOLValidationException {
		URI variableURI = URIcompliance.createCompliantURI(this.getSBOLDocument().getDefaultURIprefix(),
				TopLevel.COMPONENT_DEFINITION, variableId, version, this.getSBOLDocument().isTypesInURIs());
		return createVariableComponent(displayId, variableURI, operator);
	}

	public VariableComponent createVariableComponent(String displayId, OperatorType operator, String variableId) throws SBOLValidationException {
		return createVariableComponent(displayId, operator, variableId, "");
	}

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

	public URI getTemplateURI() {
        return this.template;
    }
	
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




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
 * @version 2.1
 */
public class CombinatorialDerivation extends TopLevel {

	private URI template;
	private URI strategy;
	private HashMap<URI, VariableComponent> variableComponents;

	/**
	 * @param identity
	 * @throws SBOLValidationException if an SBOL validation rule violation occurred in the following
	 * constructor or method: 
	 * <ul>
	 * <li>{@link TopLevel#TopLevel(URI)}, or</li>
	 * <li>{@link #setTypes(Set)}.</li>
	 * </ul>
	 */
	public CombinatorialDerivation(URI identity, URI template) throws SBOLValidationException {
		super(identity);

        this.template = template;

        this.strategy = null;
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


	private void addVariableComponent(URI uri, VariableComponent variableComponent) {
		this.variableComponents.put(uri, variableComponent);		
	}

	public HashMap<URI, VariableComponent> getVariableComponents() {
		return this.variableComponents;
	}

	/**
     * Write better documentation of this
     */
	void copy(CombinatorialDerivation combinatorialDerivation) throws SBOLValidationException {
		((TopLevel)this).copy((TopLevel)combinatorialDerivation);

		this.variableComponents = new HashMap<>();
        HashMap<URI, VariableComponent> sourceVariableComponents = combinatorialDerivation.getVariableComponents();
     

		for (URI variableComponentUri : sourceVariableComponents.keySet()) {
			this.addVariableComponent(variableComponentUri, 
					(VariableComponent)sourceVariableComponents.get(variableComponentUri).deepCopy());
		}

        this.setTemplate(combinatorialDerivation.getTemplate());
        this.setStrategy(combinatorialDerivation.getStrategy());
	}


    public URI getTemplate() {
        return this.template;
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

    public URI getStrategy() {
        return this.strategy;
    }

    public void setStrategy(URI strategy) {
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
        
        result *= this.template.hashCode();
        result *= this.strategy.hashCode();

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

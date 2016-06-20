package org.sbolstandard.core2;

/**
 * Represents an SBOL validation rule.
 * 
 * @author Zhen Zhang
 * @version 2.1
 */
class SBOLValidationRule {

	private String ruleClass;
	private String id;
	private String condition;
	private String description;
	private String reference;

	SBOLValidationRule(String ruleClass) {
		this.ruleClass = ruleClass;
		id = null;
		condition = null;
		description = null;
	}

	String getRuleClass() {
		return ruleClass;
	}

	String getId() {
		return id;
	}

	void setId(String id) {
		this.id = id;
	}

	String getCondition() {
		return condition;
	}

	void setCondition(String condition) {
		this.condition = condition;
	}

	String getDescription() {
		return description;
	}

	void setDescription(String description) {
		this.description = description;
	}
	
	void setReference(String ref) {
		this.reference = ref;
	}

	String getReference() {
		return reference;
	}
	
	@Override
	public String toString() {
		return "model class: " + ruleClass + "\n" 
			+  "id: " + this.id + "\n"
			+  "condition: " + this.condition + "\n"
			+  "description: " + this.description + "\n"
			+  "reference: " + this.reference+ "\n";
	}
}

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

	void setModelClass(String modelClass) {
		this.ruleClass = modelClass;
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
	
	boolean isComplete() {
//		if (this.getModelClass() == null) {
//			return false;
//		}
		if (this.getId() == null) {
			return false;
		}
		else if (this.getCondition() == null) {
			return false;
		}
		else if (this.getDescription() == null) {
			return false;
		}
		else {
			return true;
		}
	}

	void setField(String field, String strBuff) {
		if (field.equals("id")) {
			//currentRule.setId(strBuff);
		}
		else if (field.equals("condition")) {
			//currentRule.setCondition(strBuff);
		}
		else if (field.equals("description")) {
			//currentRule.setDescription(strBuff);
		}
		else if (field.equals("reference")) {
			//currentRule.setReference(strBuff);
		}
	}	
}

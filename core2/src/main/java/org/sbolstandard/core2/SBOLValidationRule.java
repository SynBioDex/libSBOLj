package org.sbolstandard.core2;

public class SBOLValidationRule {

	private String ruleClass;
	private String id;
	private String condition;
	private String description;
	private String reference;

	public SBOLValidationRule(String ruleClass) {
		this.ruleClass = ruleClass;
		id = null;
		condition = null;
		description = null;
	}

	public String getRuleClass() {
		return ruleClass;
	}

	public void setModelClass(String modelClass) {
		this.ruleClass = modelClass;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCondition() {
		return condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public String toString() {
		return "model class: " + ruleClass + "\n" 
			+  "id: " + this.id + "\n"
			+  "condition: " + this.condition + "\n"
			+  "description: " + this.description + "\n"
			+  "reference: " + this.reference+ "\n";
	}
	
	public boolean isComplete() {
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

	public void setReference(String ref) {
		this.reference = ref;
	}

	public void setField(String field, String strBuff) {
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

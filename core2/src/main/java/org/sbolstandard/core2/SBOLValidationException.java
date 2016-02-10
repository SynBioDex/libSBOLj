package org.sbolstandard.core2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Zhen Zhang
 * @author Tramy Nguyen
 * @author Nicholas Roehner
 * @author Matthew Pocock
 * @author Goksel Misirli
 * @author Chris Myers
 * @version 2.0-beta
 */

public class SBOLValidationException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private final List<Identified> objects;

	private static String ruleBegin = "^\\[(\\w+)\\]\\s*$";
	private static String ruleId = "^id:\\s(sbol-\\d+)\\s*$";
	private static String ruleCondition = "^condition:\\s(\\w+\\s*\\w*)\\s*$";
	private static String ruleDescriptionBegin = "^description:\\s(.+)\\s*$";
	private static String ruleDescriptionBody = "^(?!reference:)(.+)$";//"^\\b(?!reference)\\b(?!:)(.+)$";
	// (?!...) is negative lookahead.
	//	Word boundary \b is equivalent to (?<=\w)(?!\w)|(?=\w)(?<!\w).It means that a position that's preceded by a word 
	//	character and not followed by one, or a position that's followed by a word character and not 
	//	preceded by one.
	private static String ruleReference = "^\\breference\\b:\\s(.+)";
	private static SBOLValidationRule currentRule;
	private static Map<String, SBOLValidationRule> validationRules;

	/**
	 * Creates a new exception instance with the given message and objects causing the problem.
	 * @param message
	 * @param objects
	 */
	SBOLValidationException(String message, Identified ... objects) {
		this(message, Arrays.asList(objects));
	}

	/**
	 * Creates a new exception instance with the given message and objects causing the problem.
	 * 
	 * @param message
	 * @param objects
	 */
	SBOLValidationException(String message, java.util.Collection<? extends Identified> objects) {
		super(formatMessage(message, objects));
		this.objects = Collections.unmodifiableList(new ArrayList<>(objects));
	}

	/**
	 * Creates a new exception instance with the given message and objects causing the problem.
	 * 
	 * @param message
	 * @param cause
	 * @param objects
	 */
	SBOLValidationException(String message, Throwable cause, Identified ... objects) {
		super(message, cause);
		this.objects = Collections.unmodifiableList(Arrays.asList(objects));
	}

	/**
	 * Creates a new exception instance with the given cause but no specific objects for the problem.
	 * 
	 * @param cause
	 */	
	SBOLValidationException(Throwable cause) {
		super(cause);
		this.objects = Collections.emptyList();
	}

//	/**
//	 * @param message
//	 * @param annotations
//	 */
//	SBOLValidationException(String message, Annotation ... annotations) {
//		this(message, Arrays.asList(annotations));
//	}
//
//	/**
//	 * @param message
//	 * @param annotationist
//	 */
//	public SBOLValidationException(String message, List<Annotation> annotations) {
//		super(formatMessage(message, annotations));
//		this.objects = null;//Collections.unmodifiableList(new ArrayList<>(objects));
//	}


	/**
	 * Returns the list of objects relevant for the validation exception. This list may be empty if the exact object
	 * for the validation exception is not known. In those cases, the {@link #getCause() cause} of the exception can 
	 * provide more information.
	 * 
	 * @return a collection of Identified instances
	 */
	java.util.Collection<Identified> getObjects() {
		return objects;
	}

	private static String formatMessage(String message, java.util.Collection<? extends Identified> objects) {
		final StringBuilder sb = new StringBuilder(message);
		if (message.startsWith("sbol-")) {
			if (validationRules == null) {
				validationRules = new LinkedHashMap<String, SBOLValidationRule>();
				InputStreamReader f = new InputStreamReader(SBOLValidationRule.class.
						getResourceAsStream("/validation/rules.txt"));
				try {
					parse(new BufferedReader(f));
					String key = message.trim();
					SBOLValidationRule rule = validationRules.get(key);
					if (rule == null) {
						throw new RuntimeException("Rule ID does not exist.");
					}
					sb.append(": " + rule.getDescription() + "\n");
					if (!objects.isEmpty()) {
						sb.append(": ");
						boolean first = true;
						for (Identified obj : objects) {
							if (first) {
								first = false;
							}
							else {
								sb.append(", ");
							}
							if (obj.getIdentity() != null) {
								sb.append(obj.getIdentity());
							}
						}
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		else {		
			//final StringBuilder sb = new StringBuilder(message);
			if (!objects.isEmpty()) {
				sb.append(": ");
				boolean first = true;
				for (Identified obj : objects) {
					if (first) {
						first = false;
					}
					else {
						sb.append(", ");
					}
					if (obj.getIdentity() != null) {
						sb.append(obj.getIdentity());
					}
				}
			}
			//return sb.toString();		
		}
		return sb.toString();
	}
	
//	/**
//	 * @param message
//	 * @param annotations
//	 * @return
//	 */
//	private static String formatMessage(String message, List<Annotation> annotations) {
//				final StringBuilder sb = new StringBuilder(message);
//		if (message.startsWith("sbol-")) {
//			if (validationRules == null) {
//				validationRules = new LinkedHashMap<String, SBOLValidationRule>();
//				InputStreamReader f = new InputStreamReader(SBOLValidationRule.class.
//						getResourceAsStream("/validation/rules.txt"));
//				try {
//					parse(new BufferedReader(f));
//					String key = message.trim();
//					SBOLValidationRule rule = validationRules.get(key);
//					if (rule == null) {
//						throw new RuntimeException("Rule ID does not exist.");
//					}
//					sb.append(": " + rule.getDescription() + "\n");
//					if (!annotations.isEmpty()) {
//						sb.append(": ");
//						boolean first = true;
//						for (Annotation obj : annotations) {
//							if (first) {
//								first = false;
//							}
//							else {
//								sb.append(", ");
//							}
//							if (obj != null) {
//								sb.append(obj.toString());
//							}
//						}
//					}
//				} catch (IOException e) {
//					e.printStackTrace();
//				}
//			}
//		}
//		else {
//			//final StringBuilder sb = new StringBuilder(message);
//			if (!annotations.isEmpty()) {
//				sb.append(": ");
//				boolean first = true;
//				for (Annotation obj : annotations) {
//					if (first) {
//						first = false;
//					}
//					else {
//						sb.append(", ");
//					}
//					if (obj != null) {
//						sb.append(obj.toString());
//					}
//				}
//			}
//			//return sb.toString();
//		}
//		return sb.toString();
//	}
	
		
	/**
	 * @param br
	 * @throws IOException
	 */
	private static void parse(BufferedReader br) throws IOException {		
		String line;
		String ruleDescription = "";
		while((line = br.readLine())!= null) {
			//System.out.println(line);
			// WARNING: DO NOT modify the ordering of the if-else clause below!
			if (line.matches(ruleBegin)) {
				Matcher mRuleBegin = Pattern.compile(ruleBegin).matcher(line);
				if (mRuleBegin != null && mRuleBegin.matches()) { // need to call matches method in order to call the group method.
					currentRule = new SBOLValidationRule(mRuleBegin.group(1));
					//System.out.println("currentRule.ruleClass: " + currentRule.getRuleClass());
					ruleDescription = "";
				}
			}
			else if (line.matches(ruleId)) {
				Matcher mRuleId = Pattern.compile(ruleId).matcher(line);
				if (mRuleId != null && mRuleId.matches()) { // need to call matches method in order to call the group method.
					currentRule.setId(mRuleId.group(1));
					//System.out.println("currentRule.ruleId: " + currentRule.getId());
				}
			}
			else if (line.matches(ruleCondition)) {
				Matcher mRuleCondition = Pattern.compile(ruleCondition).matcher(line);
				if (mRuleCondition != null && mRuleCondition.matches()) { // need to call matches method in order to call the group method.
					currentRule.setCondition(mRuleCondition.group(1));
					//System.out.println("currentRule.ruleCondition: " + currentRule.getCondition());
				}
			}
			else if (line.matches(ruleDescriptionBegin)) {
				Matcher mRuleDescription = Pattern.compile(ruleDescriptionBegin).matcher(line);
				if (mRuleDescription != null && mRuleDescription.matches()) { // need to call matches method in order to call the group method.
					//System.out.println("currentRule.ruleDescriptionBegin: " + mRuleDescription.group(1));
					ruleDescription = ruleDescription.trim() + " " + mRuleDescription.group(1);
					
				}
			}
			else if (line.matches(ruleDescriptionBody)) { // WARNING: Do NOT move this if clause to other places.
				Matcher mRuleDescriptionBody = Pattern.compile(ruleDescriptionBody).matcher(line);
				if (mRuleDescriptionBody != null && mRuleDescriptionBody.matches()) { // need to call matches method in order to call the group method.
					//System.out.println("currentRule.ruleDescriptionBody: " + mRuleDescriptionBody.group(1));
					ruleDescription = ruleDescription.trim() + " " + mRuleDescriptionBody.group(1);
				}
			}
			else if (line.matches(ruleReference)) {
				currentRule.setDescription(ruleDescription);
				System.out.println("here: " + ruleDescription);
				Matcher mRuleReference = Pattern.compile(ruleReference).matcher(line);
				if (mRuleReference != null && mRuleReference.matches()) { // need to call matches method in order to call the group method.
					//System.out.println("currentRule.ruleReference: " + mRuleReference.group(1));
					currentRule.setReference(mRuleReference.group(1));
					validationRules.put(currentRule.getId(), currentRule);
					currentRule = null;
				}		
			}
		}
		br.close();
	}

	private void printAllRules() {
		for (String key : validationRules.keySet()) {
			System.out.println(validationRules.get(key));
		}
	}

}


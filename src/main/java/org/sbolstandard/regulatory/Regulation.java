/**
 */
package org.sbolstandard.regulatory;

import org.sbolstandard.core.SBOLNamedObject;
import org.sbolstandard.core.SBOLRootObject;
import org.sbolstandard.core.SequenceAnnotation;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Regulation</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.sbolstandard.regulatory.Regulation#getLeftAnnotation <em>Left Annotation</em>}</li>
 *   <li>{@link org.sbolstandard.regulatory.Regulation#getRightAnnotation <em>Right Annotation</em>}</li>
 *   <li>{@link org.sbolstandard.regulatory.Regulation#getType <em>Type</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.sbolstandard.regulatory.regulatoryPackage#getRegulation()
 * @model
 * @generated
 */
public interface Regulation extends SBOLNamedObject, SBOLRootObject {
	void setRegulation(
			SequenceAnnotation left, 
			RegulationType type, 
			SequenceAnnotation right);
	public SequenceAnnotation getLeftAnnotation();	
	public RegulationType getRegulationType();	
	public SequenceAnnotation getRightAnnotation();
} 

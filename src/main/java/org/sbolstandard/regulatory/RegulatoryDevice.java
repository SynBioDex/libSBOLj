/**
 */
package org.sbolstandard.regulatory;

import java.util.List;

import org.sbolstandard.core.Device;
import org.sbolstandard.core.SBOLNamedObject;
import org.sbolstandard.core.SBOLRootObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Regulatory Interactions</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.sbolstandard.regulatory.RegulatoryDevice#getRegulations <em>Regulations</em>}</li>
 *   <li>{@link org.sbolstandard.regulatory.RegulatoryDevice#getRegulationTypes <em>Regulation Types</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.sbolstandard.regulatory.regulatoryPackage#getRegulatoryInteractions()
 * @model
 * @generated
 */
public interface RegulatoryDevice 
		extends SBOLNamedObject, Device  {
	/**
	 * Returns the value of the '<em><b>Regulations</b></em>' containment reference list.
	 * The list contents are of type {@link org.sbolstandard.regulatory.Regulation}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Regulations</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Regulations</em>' containment reference list.
	 * @see org.sbolstandard.regulatory.regulatoryPackage#getRegulatoryInteractions_Regulations()
	 * @model containment="true"
	 * @generated
	 */
	List<Regulation> getRegulations();

	/**
	 * Returns the value of the '<em><b>Regulation Types</b></em>' reference list.
	 * The list contents are of type {@link org.sbolstandard.regulatory.RegulationType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Regulation Types</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Regulation Types</em>' reference list.
	 * @see org.sbolstandard.regulatory.regulatoryPackage#getRegulatoryInteractions_RegulationTypes()
	 * @model
	 * @generated
	 */
	//List<RegulationType> getRegulationTypes();

} // RegulatoryInteractions

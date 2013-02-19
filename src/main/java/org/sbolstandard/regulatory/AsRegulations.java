package org.sbolstandard.regulatory;

import org.sbolstandard.core.Device;
import org.sbolstandard.core.extension.ExtendedAs;

import java.util.List;

/**
 * @author Matthew Pocock
 */
public interface AsRegulations extends ExtendedAs<Device> {
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

}

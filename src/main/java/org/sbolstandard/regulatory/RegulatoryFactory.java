package org.sbolstandard.regulatory;

import org.sbolstandard.regulatory.impl.RegulationImpl;
import org.sbolstandard.regulatory.impl.RegulationTypeImpl;
import org.sbolstandard.regulatory.impl.RegulatoryDeviceImpl;

public class RegulatoryFactory {
	/**
	 * Creates a new empty {@link RegulatoryDevice} instance.
	 */
	public static RegulatoryDevice createRegulatoryDevice() {
		return new RegulatoryDeviceImpl();
	}

	/**
	 * Creates a new empty {@link Regulation} instance.
	 */
	public static Regulation createRegulation() {
		return new RegulationImpl();
	}

	/**
	 * Creates a new empty {@link Regulation} instance.
	 */
	public static RegulationType createRegulationType() {
		return new RegulationTypeImpl();
	}
}

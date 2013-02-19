package org.sbolstandard.regulatory.impl;

import org.sbolstandard.core.Device;
import org.sbolstandard.regulatory.AsRegulations;
import org.sbolstandard.regulatory.Regulation;

import java.util.List;

/**
 * Simple implementation of AsRegulations.
 *
 * @author Matthew Pocock
 */
public class AsRegulationsImpl implements AsRegulations {
    private final Device extended;
    private final List<Regulation> regulations;

    public AsRegulationsImpl(Device extended, List<Regulation> regulations) {
        this.extended = extended;
        this.regulations = regulations;
    }

    public Device getExtended() {
        return extended;
    }

    public List<Regulation> getRegulations() {
        return regulations;
    }
}

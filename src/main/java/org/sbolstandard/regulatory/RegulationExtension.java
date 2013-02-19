package org.sbolstandard.regulatory;

import org.sbolstandard.core.Device;
import org.sbolstandard.core.extension.ExtendedAs;
import org.sbolstandard.core.extension.ExtensionProvider;
import org.sbolstandard.regulatory.impl.AsRegulationsImpl;

import java.util.ArrayList;

/**
 * Regulations associated with a Device.
 *
 * @author Matthew Pocock
 */
public class RegulationExtension implements ExtensionProvider<Device> {
    // get an instance of this extension, implemented using a private Holder class to solve thread-safety and lazy
    // issues
    public static RegulationExtension getInstance() {
        return Holder.instance;
    }

    // the Holder class that stores our actual instance
    private static class Holder {
        private static RegulationExtension instance = new RegulationExtension();
    }

    @Override
    public AsRegulations extend(final Device device) {
        // todo: replace stub with something that tracks regulations associated with the device
        return new AsRegulationsImpl(device, new ArrayList<Regulation>());
    }
}

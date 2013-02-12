package org.sbolstandard.core;

public interface PrimitiveDevice  
	extends Device  {

    /**
     * Elements that are intended as engineering components in synthetic biology.
     *
     * For example, standard biological parts, BioBricks, oligo components,
     * vector plasmids, genomes, or any other DNA segment of interest as a building
     * block of biological systems.
     *
     * @return 0 or more <code>DnaComponent</code>[s] that are in this Device
     */
    public java.util.Collection<DnaComponent> getComponents();

    /**
     * Defined DNA segment for engineering biological systems, which belongs to
     * this Device.
     *
     * Any one of the following, standard biological parts, BioBricks, oligo components,
     * vector plasmids, genomes, or any other DNA segment of interest as a building
     * block of biological systems.
     *
     * @param component a <code>DnaComponent</code> that should be a member of this device
     */
    public void addComponent(DnaComponent component);
	public void removeComponent(DnaComponent component);

}

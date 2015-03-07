package org.jug.montpellier.core.api;

public interface Cartridge {

    /**
     * Returns the label to be displayed
     * @return
     */
    public String label();

    /**
     * Returns the route Uri (can be related to the current context or absolute)
     * @return
     */
    public String routeUri();

    /**
     * Returns the cartridge position (starting from 0). It's the preferred position and if multiple cartridges define the same
     * position then the {@link CartridgeSupport} makes the final decision
     * @return
     */
    public int position();
}

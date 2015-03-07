package org.jug.montpellier.core.api;


import org.apache.felix.ipojo.annotations.Component;
import org.apache.felix.ipojo.annotations.Instantiate;
import org.apache.felix.ipojo.annotations.Provides;

import java.util.List;


public interface CartridgeConsumer {

    public List<Cartridge> cartridges();

}

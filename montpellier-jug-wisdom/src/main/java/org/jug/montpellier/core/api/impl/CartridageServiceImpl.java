package org.jug.montpellier.core.api.impl;

import org.apache.felix.ipojo.annotations.Component;
import org.apache.felix.ipojo.annotations.Instantiate;
import org.apache.felix.ipojo.annotations.Provides;
import org.apache.felix.ipojo.annotations.Requires;
import org.jug.montpellier.core.api.Cartridge;
import org.jug.montpellier.core.api.CartridgeSupport;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
@Provides(specifications = CartridgeSupport.class)
@Instantiate
public class CartridageServiceImpl implements CartridgeSupport {

    @Requires(specification = Cartridge.class)
    List<Cartridge> cartridges;


    @Override
    public List<Cartridge> cartridges() {
        List<Cartridge> clone = new ArrayList<>(cartridges);
        clone.sort((Cartridge cartridge1, Cartridge cartridge2) -> Integer.compare(cartridge1.position(), cartridge2.position()));
        return Collections.unmodifiableList(clone);
    }
}

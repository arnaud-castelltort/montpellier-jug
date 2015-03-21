package org.jug.montpellier.core.api.impl;

import org.apache.felix.ipojo.annotations.*;
import org.jug.montpellier.core.api.Cartridge;
import org.jug.montpellier.core.api.CartridgeSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

@Component
@Provides(specifications = CartridgeSupport.class)
@Instantiate
public class CartridageSupportImpl implements CartridgeSupport {

    private static final Logger LOG = LoggerFactory.getLogger(CartridageSupportImpl.class);

    TreeSet<Cartridge> cartridges = new TreeSet<>((Cartridge cartridge1, Cartridge cartridge2) -> Integer.compare(cartridge1.position(), cartridge2.position()));


    @Override
    public List<Cartridge> cartridges() {
        return new ArrayList<Cartridge>(cartridges);
    }

    @Bind(specification = Cartridge.class, aggregate = true)
    public void bindCartridges(Cartridge cartridge) {
        LOG.info("Adding cratridge " + cartridge);
        if (cartridge != null) {
            cartridges.add(cartridge);
        }
    }


    @Unbind(specification = Cartridge.class, aggregate = true)
    public void unbindCartridges(Cartridge cartridge) {
        LOG.info("Removing cratridge " + cartridge);
        if (cartridge != null) {
            cartridges.remove(cartridge);
        }
    }
}

package org.jug.montpellier.core.api.impl;

import org.apache.felix.ipojo.annotations.Component;
import org.apache.felix.ipojo.annotations.Instantiate;
import org.apache.felix.ipojo.annotations.Provides;
import org.apache.felix.ipojo.annotations.Requires;
import org.jug.montpellier.core.api.Cartridge;
import org.jug.montpellier.core.api.CartridgeConsumer;
import org.jug.montpellier.core.api.CartridgeService;

import java.util.List;
import java.util.stream.Collectors;

@Component
@Provides(specifications = CartridgeConsumer.class)
@Instantiate
public class CartridageServiceImpl implements CartridgeConsumer {

    @Requires(specification = CartridgeService.class)
    List<CartridgeService> cartridgeServices;


    @Override
    public List<Cartridge> cartridges() {
        return cartridgeServices.stream().map((c) -> c.cartridge()).collect(Collectors.toList());
    }
}

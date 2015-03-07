package org.jug.montpellier.core.controller;

import org.jug.montpellier.core.api.Cartridge;
import org.jug.montpellier.core.api.CartridgeSupport;
import org.wisdom.api.DefaultController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JugController extends DefaultController {

    public static final String CARTRIDGES = "cartridges";

    public class ParameterBuilder {

        private Map<String, Object> parameters = new HashMap<String, Object>();

        public ParameterBuilder add(String key, Object value) {
            parameters.put(key, value);
            return this;
        }

        public ParameterBuilder setCartridges(CartridgeSupport cartridgeSupport) {
            parameters.put(CARTRIDGES, cartridgeSupport.cartridges());
            return this;
        }

        public Map<String, Object> build() {
            return parameters;
        }
    }

}

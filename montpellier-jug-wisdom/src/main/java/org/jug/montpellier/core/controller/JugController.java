package org.jug.montpellier.core.controller;

import org.apache.felix.ipojo.annotations.Component;
import org.apache.felix.ipojo.annotations.Requires;
import org.jug.montpellier.core.api.CartridgeConsumer;
import org.wisdom.api.DefaultController;

import java.lang.reflect.Parameter;
import java.util.HashMap;
import java.util.Map;

public class JugController extends DefaultController {

    public class ParameterBuilder {

        private Map<String, Object> parameters = new HashMap<String, Object>();

        public ParameterBuilder add(String key, Object value) {
            parameters.put(key, value);
            return this;
        }

        public Map<String, Object> build() {
            return parameters;
        }
    }

}

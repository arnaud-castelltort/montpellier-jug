package org.jug.montpellier.core.controller;

import org.wisdom.api.DefaultController;

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

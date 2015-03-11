package org.jug.montpellier.core.controller;

import org.jug.montpellier.core.api.CartridgeSupport;
import org.wisdom.api.DefaultController;
import org.wisdom.api.http.Result;
import org.wisdom.api.templates.Template;

import java.util.HashMap;
import java.util.Map;

public class JugController extends DefaultController {

    public static final String CARTRIDGES = "cartridges";

    public JugController(CartridgeSupport cartridgeSupport) {
        this.cartridgeSupport = cartridgeSupport;
    }

    private final CartridgeSupport cartridgeSupport;

    public Result renderRoot(Template template, Object...params) {

        Map<String, Object> paramsMap = new HashMap<>();

        paramsMap.put(CARTRIDGES, cartridgeSupport.cartridges());

        for (int i = 0; i < params.length; i+=2)
            paramsMap.put((String)params[i], params[i+1]);


        return ok(render(template, paramsMap));
    }

}

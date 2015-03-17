package org.jug.montpellier.core.controller;

import org.jug.montpellier.core.api.CartridgeSupport;
import org.jug.montpellier.core.api.NextEventSupport;
import org.jug.montpellier.core.api.PartnerSupport;
import org.wisdom.api.DefaultController;
import org.wisdom.api.http.Result;
import org.wisdom.api.templates.Template;

import java.util.HashMap;
import java.util.Map;

public class JugController extends DefaultController {

    public static final String CARTRIDGES = "cartridges";
    public static final String NEXT_EVENT = "nextEvent";
    public static final String PARTNERS = "partners";

    /**
     * Use this contructor to display a normal page with full menu
     * @param cartridgeSupport
     */
    public JugController(CartridgeSupport cartridgeSupport) {
        this.cartridgeSupport = cartridgeSupport;
        this.nextEventSupport = null;
        this.partnerSupport = null;
    }

    /**
     * Use this contructor to display a normal page with full menu and the next event at the top of your page
     * @param cartridgeSupport all pages to display in menu
     * @param nextEventSupport the next event to display
     */
    public JugController(CartridgeSupport cartridgeSupport, NextEventSupport nextEventSupport, PartnerSupport partnerSupport) {
        this.cartridgeSupport = cartridgeSupport;
        this.nextEventSupport = nextEventSupport;
        this.partnerSupport = partnerSupport;
    }

    private final CartridgeSupport cartridgeSupport;
    private final NextEventSupport nextEventSupport;
    private final PartnerSupport partnerSupport;

    public Result renderRoot(Template template, Object...params) {

        Map<String, Object> paramsMap = new HashMap<>();

        paramsMap.put(CARTRIDGES, cartridgeSupport.cartridges());

        for (int i = 0; i < params.length; i+=2)
            paramsMap.put((String)params[i], params[i+1]);

        if(nextEventSupport != null) {
            paramsMap.put(NEXT_EVENT, nextEventSupport.getNextEvent());
        }

        if(partnerSupport != null) {
            paramsMap.put(PARTNERS, partnerSupport.getPartners());
        }

        return ok(render(template, paramsMap));
    }

}

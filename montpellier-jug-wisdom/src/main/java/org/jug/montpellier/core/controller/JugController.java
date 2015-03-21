package org.jug.montpellier.core.controller;

import org.jug.montpellier.cartridges.news.models.News;
import org.jug.montpellier.core.api.CartridgeSupport;
import org.jug.montpellier.forms.services.PropertySheet;
import org.jug.montpellier.core.api.NextEventSupport;
import org.jug.montpellier.core.api.PartnerSupport;
import org.wisdom.api.DefaultController;
import org.wisdom.api.http.Renderable;
import org.wisdom.api.http.Result;
import org.wisdom.api.templates.Template;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JugController extends DefaultController {

    public static final String CARTRIDGES = "cartridges";
    public static final String PROPERTY_SHEET = "propertysheet";
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

    public Templatable template(Template template) {
        return new Templatable(template);
    }

    public class Templatable {
        private Map<String, Object> paramsMap = new HashMap<>();
        private Template template;

        public Templatable(Template template) {
            this.template = template;
        }

        public Templatable withPropertySheet(Renderable propertySheetRenderable) {
            paramsMap.put(PROPERTY_SHEET, propertySheetRenderable.content());
            return this;
        }

        public Result render() {
            paramsMap.put(CARTRIDGES, cartridgeSupport.cartridges());

            if (nextEventSupport != null) {
                paramsMap.put(NEXT_EVENT, nextEventSupport.getNextEvent());
            }

            if (partnerSupport != null) {
                paramsMap.put(PARTNERS, partnerSupport.getPartners());
            }
            return JugController.ok(JugController.this.render(template, paramsMap));
        }

        public Templatable withParam(String paramKey, Object paramValue) {
            paramsMap.put(paramKey, paramValue);
            return this;
        }
    }
}

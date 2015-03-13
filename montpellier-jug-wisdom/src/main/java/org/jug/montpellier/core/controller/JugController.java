package org.jug.montpellier.core.controller;

import org.jug.montpellier.cartridges.news.models.News;
import org.jug.montpellier.core.api.CartridgeSupport;
import org.jug.montpellier.forms.services.PropertySheet;
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

    public JugController(CartridgeSupport cartridgeSupport) {
        this.cartridgeSupport = cartridgeSupport;
    }

    private final CartridgeSupport cartridgeSupport;

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
            return JugController.ok(JugController.this.render(template, paramsMap));
        }

        public Templatable withParam(String paramKey, Object paramValue) {
            paramsMap.put(paramKey, paramValue);
            return this;
        }
    }
}

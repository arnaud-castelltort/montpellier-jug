package org.jug.montpellier.forms.services.editors.base;

import org.jug.montpellier.forms.services.PropertyDefinition;
import org.jug.montpellier.forms.apis.Editor;
import org.jug.montpellier.forms.apis.EditorService;
import org.wisdom.api.Controller;
import org.wisdom.api.http.Renderable;
import org.wisdom.api.templates.Template;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Eric Taix on 11/03/2015.
 */
public class BooleanEditor extends BaseEditor implements Editor {

    private static final String TRUE = "true";
    private static final String FALSE = "false";
    private final Template template;
    private Boolean value;

    public BooleanEditor(Template template, EditorService service) {
        super(service);
        this.template = template;
    }

    @Override
    public Object getValue() {
        return value;
    }

    @Override
    public void setValue(Object value) {
        this.value = (Boolean) value;
    }

    @Override
    public String getAsText() {
        return value ? TRUE : FALSE;
    }

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        setValue(TRUE.equals(text));
    }

    @Override
    public Renderable getCustomEditor(Controller controller, PropertyDefinition property) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("property", property);
        return template.render(controller, parameters);
    }
}

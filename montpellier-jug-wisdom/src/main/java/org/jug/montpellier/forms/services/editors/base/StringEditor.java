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
public class StringEditor extends BaseEditor implements Editor {

    private final Template template;
    private String value;

    public StringEditor(Template template, EditorService factory) {
        super(factory);
        this.template = template;
    }

    @Override
    public void setValue(Object value) {
        setAsText((String)value);
    }

    @Override
    public Object getValue() {
        return getAsText();
    }

    @Override
    public String getAsText() {
        return value;
    }

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        this.value = text;
    }

    @Override
    public Renderable getCustomEditor(Controller controller, PropertyDefinition property) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("property", property);
        return template.render(controller, parameters);
    }
}
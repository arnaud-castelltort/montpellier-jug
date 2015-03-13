package org.jug.montpellier.forms.services.editors.base;

import org.jug.montpellier.forms.services.Editor;
import org.wisdom.api.Controller;
import org.wisdom.api.http.Renderable;
import org.wisdom.api.templates.Template;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Eric Taix on 11/03/2015.
 */
public class StringEditor implements Editor {

    private final Template template;
    private String value;

    public StringEditor(Template template) {
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
    public Renderable getCustomEditor(Controller controller) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("value", getAsText());
        return template.render(controller, parameters);
    }
}
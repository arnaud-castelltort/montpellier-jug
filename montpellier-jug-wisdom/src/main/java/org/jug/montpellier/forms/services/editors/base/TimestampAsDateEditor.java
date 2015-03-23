package org.jug.montpellier.forms.services.editors.base;

import org.jug.montpellier.forms.apis.Editor;
import org.jug.montpellier.forms.apis.EditorService;
import org.jug.montpellier.forms.services.PropertyDefinition;
import org.wisdom.api.Controller;
import org.wisdom.api.http.Renderable;
import org.wisdom.api.templates.Template;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Eric Taix on 11/03/2015.
 */
public class TimestampAsDateEditor extends BaseEditor implements Editor {

    private final Template template;
    private Timestamp value;

    public TimestampAsDateEditor(Template template, EditorService service) {
        super(service);
        this.template = template;
    }

    @Override
    public void setValue(Object value) {
        this.value = (Timestamp) value;
    }

    @Override
    public Object getValue() {
        return value;
    }

    @Override
    public String getAsText() {
        // A valid full-date as defined in [RFC 3339] - ie input type='date'
        return new SimpleDateFormat("yyyy-MM-dd").format(value);
    }

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        this.value = Timestamp.valueOf(text);
    }

    @Override
    public Renderable getCustomEditor(Controller controller, PropertyDefinition property) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("property", property);
        return template.render(controller, parameters);
    }
}
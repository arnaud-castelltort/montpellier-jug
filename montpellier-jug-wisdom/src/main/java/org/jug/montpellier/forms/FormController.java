package org.jug.montpellier.forms;

import org.wisdom.api.DefaultController;
import org.wisdom.api.http.Renderable;
import org.wisdom.api.templates.Template;

import java.util.Map;

/**
 * Created by Eric Taix on 09/03/2015.
 */
public class FormController extends DefaultController {

    public Renderable<?> form(Template template, Map<String, Object> parameters) {
        return template.render(this, parameters);
    }
}

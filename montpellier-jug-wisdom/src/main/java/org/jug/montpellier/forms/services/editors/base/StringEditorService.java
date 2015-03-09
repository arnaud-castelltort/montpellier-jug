package org.jug.montpellier.forms.services.editors.base;

import org.apache.felix.ipojo.annotations.Component;
import org.apache.felix.ipojo.annotations.Instantiate;
import org.apache.felix.ipojo.annotations.Provides;
import org.jug.montpellier.forms.services.Editor;
import org.jug.montpellier.forms.services.EditorService;
import org.wisdom.api.Controller;
import org.wisdom.api.annotations.View;
import org.wisdom.api.http.Renderable;
import org.wisdom.api.templates.Template;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Eric Taix on 08/03/2015.
 */
@Component
@Provides(specifications = EditorService.class)
@Instantiate
public class StringEditorService implements EditorService {

    @View("editors/string")
    Template template;


    @Override
    public Class<? extends Object> getEditedType() {
        return String.class;
    }

    @Override
    public Editor createFormEditor(Object model) {
        StringEditor editor = new StringEditor();
        editor.setValue(model);
        return editor;
    }

    class StringEditor implements Editor {

        private String value;

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

        @Override
        public boolean supportsCustomEditor() {
            return true;
        }
    }
}

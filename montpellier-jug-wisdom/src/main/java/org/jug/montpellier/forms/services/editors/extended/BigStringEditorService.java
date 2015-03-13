package org.jug.montpellier.forms.services.editors.extended;

import org.apache.felix.ipojo.annotations.Component;
import org.apache.felix.ipojo.annotations.Instantiate;
import org.apache.felix.ipojo.annotations.Provides;
import org.jug.montpellier.forms.services.Editor;
import org.jug.montpellier.forms.services.EditorService;
import org.jug.montpellier.forms.services.editors.base.StringEditor;
import org.wisdom.api.annotations.View;
import org.wisdom.api.templates.Template;

/**
 * Created by Eric Taix on 08/03/2015.
 */
@Component
@Provides(specifications = EditorService.class)
@Instantiate
public class BigStringEditorService implements EditorService {

    @View("editors/bigstring")
    Template template;


    @Override
    public Class<? extends Object> getEditedType() {
        return null;
    }

    @Override
    public Editor createFormEditor(Object model) {
        StringEditor editor = new StringEditor(template);
        editor.setValue(model);
        return editor;
    }

}

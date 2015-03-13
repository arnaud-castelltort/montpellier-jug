package org.jug.montpellier.forms.services.editors.base;

import org.apache.felix.ipojo.annotations.Component;
import org.apache.felix.ipojo.annotations.Instantiate;
import org.apache.felix.ipojo.annotations.Provides;
import org.jug.montpellier.forms.services.Editor;
import org.jug.montpellier.forms.services.EditorService;
import org.wisdom.api.annotations.View;
import org.wisdom.api.templates.Template;

/**
 * Created by Eric Taix on 08/03/2015.
 */
@Component
@Provides(specifications = EditorService.class)
@Instantiate
public class LongEditorService implements EditorService {

    @View("editors/base/long")
    Template template;

    @Override
    public Class<? extends Object> getEditedType() {
        return  Long.class;
    }

    @Override
    public Editor createFormEditor(Object model) {
        LongEditor editor = new LongEditor(template, this);
        editor.setValue(model);
        return editor;
    }

}

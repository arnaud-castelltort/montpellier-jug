package org.jug.montpellier.forms.services.editors.specific;

import org.apache.felix.ipojo.annotations.Component;
import org.apache.felix.ipojo.annotations.Instantiate;
import org.apache.felix.ipojo.annotations.Provides;
import org.jug.montpellier.forms.apis.Editor;
import org.jug.montpellier.forms.apis.EditorService;
import org.wisdom.api.annotations.View;
import org.wisdom.api.templates.Template;

/**
 * Created by fteychene on 04/06/2015
 */
@Component
@Provides(specifications = EditorService.class)
@Instantiate
public class MultiStringEditorService implements EditorService {

    @View("editors/specific/multistring")
    Template editorTemplate;
    @View("views/base/string")
    Template viewTemplate;

    @Override
    public Class<? extends Object> getEditedType() {
        return String[].class;
    }

    @Override
    public Editor createFormEditor(Object model) {
        MultiStringEditor editor = new MultiStringEditor(editorTemplate, viewTemplate, this);
        editor.setValue(model);
        return editor;
    }
}

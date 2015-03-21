package org.jug.montpellier.forms.services.editors.specific;

import org.apache.felix.ipojo.annotations.Component;
import org.apache.felix.ipojo.annotations.Instantiate;
import org.apache.felix.ipojo.annotations.Provides;
import org.apache.felix.ipojo.annotations.Requires;
import org.jug.montpellier.forms.apis.Editor;
import org.jug.montpellier.forms.apis.EditorService;
import org.jug.montpellier.forms.services.editors.base.StringEditor;
import org.montpellierjug.store.jooq.tables.daos.SpeakerDao;
import org.wisdom.api.annotations.View;
import org.wisdom.api.templates.Template;

/**
 * Created by Eric Taix on 08/03/2015.
 */
@Component
@Provides(specifications = EditorService.class)
@Instantiate
public class SpeakerChooserEditorService implements EditorService {

    @View("editors/specific/speakerchooser")
    Template template;

    @Requires
    private SpeakerDao speakerDao;

    @Override
    public Class<? extends Object> getEditedType() {
        return null;
    }

    @Override
    public Editor createFormEditor(Object model) {
        SpeakerChooserEditor editor = new SpeakerChooserEditor(template, this, speakerDao);
        editor.setValue(model);
        return editor;
    }

}

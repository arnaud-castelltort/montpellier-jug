package org.jug.montpellier.forms.services.editors.base;

import org.jug.montpellier.forms.services.Editor;
import org.jug.montpellier.forms.services.EditorService;

/**
 * Created by Eric Taix on 13/03/2015.
 */
public abstract class BaseEditor implements Editor {

    private final EditorService factory;

    public BaseEditor(EditorService factory) {
        this.factory = factory;
    }
    @Override
    public EditorService service() {
        return factory;
    }
}

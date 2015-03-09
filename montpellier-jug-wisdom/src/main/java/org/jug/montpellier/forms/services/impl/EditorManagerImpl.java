package org.jug.montpellier.forms.services.impl;

import org.apache.felix.ipojo.annotations.*;
import org.jug.montpellier.forms.services.EditorManager;
import org.jug.montpellier.forms.services.Editor;
import org.jug.montpellier.forms.services.EditorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Eric Taix on 08/03/2015.
 */
@Component
@Provides(specifications = EditorManager.class)
@Instantiate
public class EditorManagerImpl implements EditorManager {

    private static Logger logger = LoggerFactory.getLogger(EditorManagerImpl.class);

   // @Requires
    private Map<Class, EditorService> formEditorFactories = new HashMap<>();

    @Override
    public Editor createEditor(Object model) {
        EditorService factory =  formEditorFactories.get(model.getClass());
        if (factory != null) {
            return factory.createFormEditor(model);
        }
        return null;
    }

    /**
     * Binds a new FormEditorFactory
     */
    @Bind(specification = EditorService.class, aggregate = true)
    public synchronized void bindFormEditorFactory(EditorService factory) {
        logger.info("Adding FormEditorFactory from " + factory);
        if (factory != null) {
            formEditorFactories.put(factory.getEditedType(), factory);
        }
    }

    /**
     * Unbinds a FormEditorFactory.
     */
    @Unbind(specification = EditorService.class, aggregate = true)
    public synchronized void unbindFormEditorFactory(EditorService factory) {
        logger.info("Removing FormEditorFactory from " + factory);
        if (factory != null) {
            formEditorFactories.remove(factory.getEditedType());
        }
    }
}

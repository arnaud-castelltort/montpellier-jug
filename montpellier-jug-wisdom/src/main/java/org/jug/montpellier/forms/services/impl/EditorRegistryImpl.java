package org.jug.montpellier.forms.services.impl;

import org.apache.felix.ipojo.annotations.*;
import org.jug.montpellier.forms.services.Editor;
import org.jug.montpellier.forms.services.EditorRegistry;
import org.jug.montpellier.forms.services.EditorService;
import org.jug.montpellier.forms.services.RenderableProperty;
import org.jug.montpellier.forms.services.editors.base.NotImplementedEditorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Eric Taix on 08/03/2015.
 */
@Component
@Provides(specifications = EditorRegistry.class)
@Instantiate
public class EditorRegistryImpl implements EditorRegistry {

    private static Logger logger = LoggerFactory.getLogger(EditorRegistryImpl.class);

    private Map<Class, EditorService> formEditorByEditedType = new HashMap<>();
    private Map<Class, EditorService> formEditorByClass = new HashMap<>();

    @Override
    public Editor createEditor(Object field, Class<?> fieldClass, RenderableProperty renderableProperty) throws ClassNotFoundException {
        EditorService factory = formEditorByEditedType.get(fieldClass);
        if (renderableProperty != null && !renderableProperty.editorService().equals(NotImplementedEditorService.class)) {
            factory = formEditorByClass.get(renderableProperty.editorService());
        }
        if (factory != null) {
            return factory.createFormEditor(field);
        }
        return null;
    }

    /**
     * Binds a new FormEditorFactory
     */
    @Bind(specification = EditorService.class, aggregate = true)
    public synchronized void bindFormEditorFactory(EditorService factory) {
        logger.info("Adding EditorFactory " + factory);
        if (factory != null) {
            if (factory.getEditedType() != null) {
                formEditorByEditedType.put(factory.getEditedType(), factory);
            }
            formEditorByClass.put(factory.getClass(), factory);
        }
    }

    /**
     * Unbinds a FormEditorFactory.
     */
    @Unbind(specification = EditorService.class, aggregate = true)
    public synchronized void unbindFormEditorFactory(EditorService factory) {
        logger.info("Removing EditorFactory " + factory);
        if (factory != null) {
            formEditorByEditedType.remove(factory.getEditedType());
            formEditorByClass.remove(factory.getClass());
        }
    }
}

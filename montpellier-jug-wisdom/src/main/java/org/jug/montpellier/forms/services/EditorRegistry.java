package org.jug.montpellier.forms.services;

/**
 * Manages FormEditor and property type relationship. FormEditor are registered by OSGi injection
 * Created by Eric Taix on 08/03/2015.
 */
public interface EditorRegistry {

    /**
     * Try to create a {@link Editor} instance according
     * to the object type. May return null if no suitable Editor could be found
     */
    public Editor createEditor(Object field, Class<?> fieldClass, RenderableProperty fieldRenderableProperty) throws ClassNotFoundException;
}

package org.jug.montpellier.forms.services;

/**
 * Created by Eric Taix on 08/03/2015.
 */
public interface EditorService {

    /**
     * Returns the class which can be edited by the FormEditor
     * @return
     */
    Class<? extends Object> getEditedType();

    /**
     * Creates the FormEditor instance for a specific model instance
     * @param model
     * @return
     */
    Editor createFormEditor(Object model);

}

package org.jug.montpellier.forms.services;

import org.jug.montpellier.forms.models.PropertyDefinition;
import org.wisdom.api.Controller;
import org.wisdom.api.http.Renderable;

/**
 * Created by Eric Taix on 08/03/2015.
 */
public interface Editor {


    public EditorService service();

    /**
     * Gets the property value.
     *
     * @return The value of the property.  Primitive types such as "int" will
     * be wrapped as the corresponding object type such as "java.lang.Integer".
     */

    public Object getValue();

    public void setValue(Object value);

    /**
     * Gets the property value as text.
     *
     * @return The property value as a human editable string.
     * <p>   Returns null if the value can't be expressed as an editable string.
     * <p>   If a non-null value is returned, then the PropertyEditor should
     * be prepared to parse that string back in setAsText().
     */
    public String getAsText();

    /**
     * Set the property value by parsing a given String.  May raise
     * java.lang.IllegalArgumentException if either the String is
     * badly formatted or if this kind of property can't be expressed
     * as text.
     *
     * @param text The string to be parsed.
     */
    public void setAsText(String text) throws java.lang.IllegalArgumentException;

    /**
     * A PropertyEditor may choose to make available a full custom Renderable
     * that edits its property value.
     *
     * @return A java.lang.String which determines the editor to use
     */
    public Renderable getCustomEditor(Controller controller, PropertyDefinition property);

}

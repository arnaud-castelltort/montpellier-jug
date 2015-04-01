package org.jug.montpellier.forms.apis;

import org.jug.montpellier.forms.models.PropertyValue;
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
     * Set the value
     * @param value
     */
    public void setValue(Object value);

    /**
     * A PropertyEditor makes available a full custom Renderable
     * that edits its property value.
     *
     * @return A java.lang.String which determines the editor to use
     */
    public Renderable getEditor(Controller controller, PropertyValue property);

    /**
     * A PropertyEditor makes available a full custom Renderable
     * that displays its property value.
     *
     * @return A java.lang.String which determines the editor to use
     */
    public Renderable getView(Controller controller, PropertyValue property);

}

package org.jug.montpellier.forms.services.impl;

import org.apache.felix.ipojo.annotations.Component;
import org.apache.felix.ipojo.annotations.Instantiate;
import org.apache.felix.ipojo.annotations.Provides;
import org.apache.felix.ipojo.annotations.Requires;
import org.jug.montpellier.forms.services.RenderableProperty;
import org.jug.montpellier.forms.services.PropertySheet;
import org.jug.montpellier.forms.models.FormProperty;
import org.jug.montpellier.forms.services.EditorManager;
import org.jug.montpellier.forms.services.Editor;
import org.wisdom.api.Controller;
import org.wisdom.api.annotations.View;
import org.wisdom.api.http.Renderable;
import org.wisdom.api.templates.Template;

import java.beans.*;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Eric Taix on 07/03/2015.
 */
@Component
@Provides(specifications = PropertySheet.class)
@Instantiate
public class PropertySheetImpl implements PropertySheet {

    @Requires
    EditorManager editorManager;
    @View("editors/propertysheet")
    Template template;

    public PropertySheetImpl() {
    }

    @Override
    public Renderable getRenderable(Controller controller, Object object) throws IntrospectionException, InvocationTargetException, IllegalAccessException, ClassNotFoundException {
        if (object != null) {
            BeanInfo beanInfo = Introspector.getBeanInfo(object.getClass());
            PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
            List<FormProperty> properties = new ArrayList<>();

            Field[] fields = object.getClass().getDeclaredFields();
            for (Field field : fields) {
                field.setAccessible(true);
                RenderableProperty renderableProperty = field.getAnnotation(RenderableProperty.class);
                Editor editor = editorManager.createEditor(field.get(object), field.getType(), renderableProperty);
                if (editor != null) {
                    FormProperty formProperty = new FormProperty();
                    formProperty.name = renderableProperty != null && renderableProperty.displayLabel() != null && !renderableProperty.displayLabel().isEmpty() ? renderableProperty.displayLabel() : field.getName();
                    formProperty.description = renderableProperty != null && renderableProperty.description() != null && !renderableProperty.description().isEmpty() ? renderableProperty.description() : "";
                    formProperty.value = editor.getValue();
                    formProperty.valueAsText = editor.getAsText();
                    formProperty.editor = editor.getCustomEditor(controller).content();
                    properties.add(formProperty);
                }
            }

            Map<String, Object> parameters = new HashMap<>();
            parameters.put("properties", properties);
            return template.render(controller, parameters);
        }
        return null;
    }

    @Override
    public Object getContent(Controller controller, Object object) throws IllegalAccessException, IntrospectionException, InvocationTargetException, ClassNotFoundException {
        return getRenderable(controller, object).content();
    }
}

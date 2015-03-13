package org.jug.montpellier.forms.services.impl;

import org.apache.felix.ipojo.annotations.Component;
import org.apache.felix.ipojo.annotations.Instantiate;
import org.apache.felix.ipojo.annotations.Provides;
import org.apache.felix.ipojo.annotations.Requires;
import org.jug.montpellier.forms.services.EditorRegistry;
import org.jug.montpellier.forms.services.RenderableProperty;
import org.jug.montpellier.forms.services.PropertySheet;
import org.jug.montpellier.forms.models.PropertyDefinition;
import org.jug.montpellier.forms.services.Editor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.wisdom.api.Controller;
import org.wisdom.api.annotations.View;
import org.wisdom.api.http.Renderable;
import org.wisdom.api.templates.Template;

import java.beans.*;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by Eric Taix on 07/03/2015.
 */
@Component
@Provides(specifications = PropertySheet.class)
@Instantiate
public class PropertySheetImpl implements PropertySheet {

    private static final Logger LOG = LoggerFactory.getLogger(PropertySheetImpl.class);

    @Requires
    EditorRegistry editorRegistry;
    @View("editors/propertysheet")
    Template template;

    public PropertySheetImpl() {
    }

    @Override
    public Renderable getRenderable(Controller controller, Object object) throws IntrospectionException, InvocationTargetException, IllegalAccessException, ClassNotFoundException {
        if (object != null) {
            List<PropertyDefinition> defs = Arrays.asList(object.getClass().getDeclaredFields()).stream().map((Field field) -> {
                field.setAccessible(true);
                try {
                    RenderableProperty renderableProperty = field.getAnnotation(RenderableProperty.class);
                    Editor editor = editorRegistry.createEditor(field.get(object), field.getType(), renderableProperty);
                    if (editor != null) {
                        PropertyDefinition propertyDefinition = new PropertyDefinition();
                        propertyDefinition.name = field.getName();
                        propertyDefinition.displayname = renderableProperty != null && renderableProperty.displayLabel() != null && !renderableProperty.displayLabel().isEmpty() ? renderableProperty.displayLabel() : field.getName();
                        propertyDefinition.description = renderableProperty != null && renderableProperty.description() != null && !renderableProperty.description().isEmpty() ? renderableProperty.description() : "";
                        propertyDefinition.value = editor.getValue();
                        propertyDefinition.valueAsText = editor.getAsText();
                        propertyDefinition.editor = editor.getCustomEditor(controller, propertyDefinition).content();
                        propertyDefinition.editorName = editor.service().getClass().getSimpleName().toLowerCase();
                        propertyDefinition.visible = renderableProperty != null ? renderableProperty.visible() : propertyDefinition.visible;
                        return propertyDefinition;
                    }
                } catch (Exception ex) {
                    LOG.warn("Unable to retrieve Editor for field " + field + " due to an error", ex);
                }
                return null;

            }).collect(Collectors.toList());

            Map<String, Object> parameters = new HashMap<>();
            parameters.put("properties", defs);
            return template.render(controller, parameters);
        }
        return null;
    }

    @Override
    public Object getContent(Controller controller, Object object) throws IllegalAccessException, IntrospectionException, InvocationTargetException, ClassNotFoundException {
        return getRenderable(controller, object).content();
    }
}

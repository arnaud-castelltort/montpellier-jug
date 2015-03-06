package services;


import org.apache.felix.ipojo.annotations.Component;
import org.apache.felix.ipojo.annotations.Instantiate;
import org.apache.felix.ipojo.annotations.Provides;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by manland on 06/03/15.
 */
@Component
@Provides(specifications = PropertiesForm.class)
@Instantiate
public class PropertiesFormImpl implements PropertiesForm {

    @Override
    public List<PropertyFrom> get(Class theClass) {
        List<PropertyFrom> properties = new ArrayList<PropertyFrom>();
        PropertyFrom propertyForm;
        for(Field field :  theClass.getFields()) {
            propertyForm = new PropertyFrom();
            propertyForm.name = field.getName();
            propertyForm.type = field.getType().getName();
            properties.add(propertyForm);
        }
        return properties;
    }

    @Override
    public List<PropertyFrom> get(Object theObject) {
        List<PropertyFrom> properties = new ArrayList<PropertyFrom>();
        PropertyFrom propertyForm;
        for(Field field :  theObject.getClass().getFields()) {
            propertyForm = new PropertyFrom();
            propertyForm.name = field.getName();
            propertyForm.type = field.getType().getName();
            try {
                propertyForm.value = field.get(theObject).toString();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            properties.add(propertyForm);
        }
        return properties;
    }

}

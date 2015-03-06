package services;

import java.util.List;

/**
 * Created by manland on 06/03/15.
 */
public interface PropertiesForm {

    List<PropertyFrom> get(Class theClass);

    List<PropertyFrom> get(Object theObject);

}

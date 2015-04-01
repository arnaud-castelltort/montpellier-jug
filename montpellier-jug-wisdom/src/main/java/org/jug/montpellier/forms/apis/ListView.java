package org.jug.montpellier.forms.apis;

import org.wisdom.api.Controller;
import org.wisdom.api.http.Renderable;

import java.beans.IntrospectionException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

/**
 * Created by Eric Taix on 24/03/15.
 */
public interface ListView {

    public <T extends Object> Renderable getRenderable(Controller controller, List<T> object, Class<T> objectClass) throws Exception;

}

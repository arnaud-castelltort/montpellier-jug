package org.jug.montpellier.forms.services;

import org.wisdom.api.Controller;
import org.wisdom.api.http.Renderable;

import java.beans.IntrospectionException;
import java.lang.reflect.InvocationTargetException;

/**
 * Created by Eric Taix on 07/03/2015.
 */
public interface PropertySheet {

    public Renderable getRenderable(Controller controller, Object object) throws IntrospectionException, InvocationTargetException, IllegalAccessException;

    public Object getContent(Controller controller, Object object) throws IllegalAccessException, IntrospectionException, InvocationTargetException;
}

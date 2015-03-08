package org.jug.montpellier.core.forms;

import org.apache.felix.ipojo.annotations.Component;
import org.apache.felix.ipojo.annotations.Instantiate;
import org.apache.felix.ipojo.annotations.Provides;
import org.apache.felix.ipojo.annotations.Requires;
import org.jug.montpellier.core.api.CartridgeSupport;
import org.jug.montpellier.core.controller.JugController;
import org.jug.montpellier.core.forms.annotations.Form;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.wisdom.api.Controller;
import org.wisdom.api.annotations.View;
import org.wisdom.api.http.Result;
import org.wisdom.api.interception.Interceptor;
import org.wisdom.api.interception.RequestContext;
import org.wisdom.api.templates.Template;
import services.PropertiesForm;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;

/**
 * This interceptor get the returned object from the controller, build appropriated PropertyEditor and finally call
 * the template to edit the object.<br/>
 * Created by Eric Taix on 07/03/2015.
 */
@Component
@Provides(specifications = Interceptor.class)
@Instantiate
public class FormInterceptor extends Interceptor<Form> {

    private static Logger logger = LoggerFactory.getLogger(FormInterceptor.class);

    @View("form")
    Template template;
    @Requires
    PropertiesForm propertiesForm;
    @Requires
    CartridgeSupport cartridgeSupport;

    @Override
    public Result call(Form configuration, RequestContext context) throws Exception {
        Result result = context.proceed();
        Object object = result.getRenderable().content();
        Controller controller = context.route().getControllerObject();
//        BeanInfo beanInfo = Introspector.getBeanInfo(object.getClass());
//        PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
//        propertyDescriptors[0].isHidden();
        logger.info("Processing automatic form for object: {}", object);
        return ok(template.render(controller, new JugController.ParameterBuilder().add("properties", propertiesForm.get(object)).setCartridges(cartridgeSupport).build()));
    }

    @Override
    public Class<Form> annotation() {
        return Form.class;
    }

    public static Result ok(Object object) {
        return status(Result.OK).render(object);
    }

    public static Result status(int statusCode) {
        return new Result(statusCode);
    }
}
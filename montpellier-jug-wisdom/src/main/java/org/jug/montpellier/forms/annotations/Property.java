package org.jug.montpellier.forms.annotations;

import org.wisdom.api.annotations.Interception;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by Eric Taix on 07/03/2015.
 */
@Interception
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Property {

    /**
     * Defines the label to display instead of the property's name
     */
    String displayLabel() default "";
    /**
     * Defines the description (if any) to display bellow the input
     */
    String description() default "";

    boolean required() default false;
    boolean readOnly() default false;
    boolean visible() default true;
    int position() default 0;
    Class<?> propertyEditor() default Object.class;

}

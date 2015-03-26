package org.jug.montpellier.forms.annotations;

import org.jug.montpellier.forms.apis.EditorService;
import org.jug.montpellier.forms.services.editors.base.NotImplementedEditorService;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by Eric Taix on 07/03/2015.
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface ListView {

    String[] labels();

    String[] columns();

}

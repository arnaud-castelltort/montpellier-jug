/*
 * #%L
 * Wisdom-Framework
 * %%
 * Copyright (C) 2013 - 2014 Wisdom Framework
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */
package org.jug.montpellier.admin.controllers;

import org.apache.felix.ipojo.annotations.Requires;
import org.jug.montpellier.admin.models.Model1;
import org.jug.montpellier.core.api.CartridgeSupport;
import org.jug.montpellier.core.controller.JugController;
import org.jug.montpellier.cartridges.news.models.News;
import org.jug.montpellier.forms.services.PropertySheet;
import org.wisdom.api.annotations.Controller;
import org.wisdom.api.annotations.Path;
import org.wisdom.api.annotations.Route;
import org.wisdom.api.annotations.View;
import org.wisdom.api.http.HttpMethod;
import org.wisdom.api.http.Result;
import org.wisdom.api.templates.Template;

import java.beans.IntrospectionException;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Controller
@Path("/admin")
public class AdminController extends JugController {

    @Requires
    CartridgeSupport cartridgeSupport;

    @View("admin")
    Template template;

    @Requires
    PropertySheet propertySheet;

    @Route(method = HttpMethod.GET, uri = "/")
    public Result home() {
        return ok(render(template, new ParameterBuilder().setCartridges(cartridgeSupport).build()));
    }

    @Route(method = HttpMethod.GET, uri = "/news")
    public Result news() {
        News n = new News();
        n.setTitle("In 2 month: DevoxxFR");
        n.setContent("Début avril se tiendra la conférence DevoxxFR");
        n.setDate(new Date(2015,03,8));
        n.setValid(true);
        return ok(n);
    }

    @Route(method = HttpMethod.GET, uri = "/model1")
    public Result model1() throws IllegalAccessException, IntrospectionException, InvocationTargetException {
        Model1 n = new Model1();
        n.setFirstName("Chrystèle");
        n.setMale(false);
        return ok(render(template, new ParameterBuilder().add("propertysheet", propertySheet.getContent(this, n)).setCartridges(cartridgeSupport).build()));
        //return ok(propertySheet.getRenderable(this, n));
    }
}

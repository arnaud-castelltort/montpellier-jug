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
import org.jug.montpellier.admin.models.Speaker;
import org.jug.montpellier.core.api.CartridgeSupport;
import org.jug.montpellier.core.controller.JugController;
import org.jug.montpellier.forms.apis.PropertySheet;
import org.montpellierjug.store.jooq.tables.daos.SpeakerDao;
import org.wisdom.api.annotations.*;
import org.wisdom.api.annotations.Controller;
import org.wisdom.api.http.HttpMethod;
import org.wisdom.api.http.Result;
import org.wisdom.api.templates.Template;

import java.beans.IntrospectionException;
import java.lang.reflect.InvocationTargetException;

@Controller
@Path("/admin/speaker")
public class AdminSpeakerController extends JugController implements org.wisdom.api.Controller {

    @View("admin")
    Template template;

    @Requires
    PropertySheet propertySheet;

    @Requires
    SpeakerDao speakerDao;

    public AdminSpeakerController(@Requires CartridgeSupport cartridgeSupport) {
        super(cartridgeSupport);
    }

    @Route(method = HttpMethod.GET, uri = "/")
    public Result home() {
        return template(template).render();
    }


    @Route(method = HttpMethod.GET, uri = "/{id}")
    public Result speaker(@Parameter("id") Long id) throws InvocationTargetException, ClassNotFoundException, IntrospectionException, IllegalAccessException {
        Speaker editedSpeaker = Speaker.build(speakerDao.findById(id));
        return template(template).withPropertySheet(propertySheet.getRenderable(this, editedSpeaker)).render();

    }

    @Route(method = HttpMethod.POST, uri = "/{id}")
    public Result saveSpeaker(@Parameter("id") Long id, @Body Speaker speaker) throws InvocationTargetException, ClassNotFoundException, IntrospectionException, IllegalAccessException {
        speakerDao.update(speaker.into(new org.montpellierjug.store.jooq.tables.pojos.Speaker()));
        return redirect("/admin/speaker/" + id);
    }

}

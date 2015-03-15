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
import org.jug.montpellier.cartridges.news.models.News;
import org.jug.montpellier.core.api.CartridgeSupport;
import org.jug.montpellier.core.controller.JugController;
import org.jug.montpellier.forms.services.PropertySheet;
import org.montpellierjug.store.jooq.tables.daos.SpeakerDao;
import org.wisdom.api.annotations.*;
import org.wisdom.api.http.HttpMethod;
import org.wisdom.api.http.Result;
import org.wisdom.api.templates.Template;

import java.beans.IntrospectionException;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.List;
import java.util.Random;

@Controller
@Path("/admin")
public class AdminSpeakerController extends JugController {

    @View("admin")
    Template template;

    @Requires
    PropertySheet propertySheet;

    @Requires
    SpeakerDao speakerDao;

    private Random r = new Random();

    public AdminSpeakerController(@Requires CartridgeSupport cartridgeSupport) {
        super(cartridgeSupport);
    }

    @Route(method = HttpMethod.GET, uri = "/")
    public Result home() {
        return template(template).render();
    }


    @Route(method = HttpMethod.GET, uri = "/news")
    public Result news() throws IllegalAccessException, IntrospectionException, InvocationTargetException, ClassNotFoundException {
        News n = new News();
        n.setTitle("In 2 month: DevoxxFR");
        n.setContent("Début avril se tiendra la conférence DevoxxFR");
        n.setDate(new Date(2015, 03, 8));
        n.setValid(true);
        return template(template).withPropertySheet(propertySheet.getRenderable(this, n)).render();
    }

    @Route(method = HttpMethod.GET, uri = "/speaker")
    public Result model1() throws IllegalAccessException, IntrospectionException, InvocationTargetException, ClassNotFoundException {
        Speaker n = new Speaker();
        n.setFullname("Eric Taix");
        n.setActivity("Senior Developer");
        n.setCompan("ITK at Clapiers");
        n.setUrl("http://www.itkweb.fr");
        n.setDescription("Eric was born long years ago. He fall in software development at 12 y old (remember the ZX81?). He loves to learn new things, to meet friends and to share.");
        n.setEmail("eric.taix@gmail.com");
        n.setJugmember(true);
        n.setMemberfct("Secrétaire");
        n.setPhotourl("http://maphoto.org/eric.taix");

        return template(template).withPropertySheet(propertySheet.getRenderable(this, n)).render();
    }

    @Route(method = HttpMethod.GET, uri = "/speaker/random")
    public Result all() throws InvocationTargetException, ClassNotFoundException, IntrospectionException, IllegalAccessException {
        List<org.montpellierjug.store.jooq.tables.pojos.Speaker> speakers = speakerDao.findAll();
        Speaker editedSpeaker = new Speaker();
        editedSpeaker.from(speakers.get(r.nextInt(speakers.size() - 1)));
        return template(template).withPropertySheet(propertySheet.getRenderable(this, editedSpeaker)).render();
    }

    @Route(method = HttpMethod.GET, uri = "/speaker/{id}")
    public Result speaker(@Parameter("id") Long id) throws InvocationTargetException, ClassNotFoundException, IntrospectionException, IllegalAccessException {
        Speaker editedSpeaker = Speaker.build(speakerDao.findById(id));
        return template(template).withPropertySheet(propertySheet.getRenderable(this, editedSpeaker)).render();
    }

    @Route(method = HttpMethod.POST, uri = "/speaker/{id}")
    public Result saveSpeaker(@Parameter("id") Long id, @Body Speaker speaker) throws InvocationTargetException, ClassNotFoundException, IntrospectionException, IllegalAccessException {
        speakerDao.update(speaker.into(new org.montpellierjug.store.jooq.tables.pojos.Speaker()));
        return redirect("/admin/speaker/"+id);
    }

}

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
package org.jug.montpellier.cartridges.home.controllers;

import org.apache.felix.ipojo.annotations.Requires;
import org.jooq.DSLContext;
import org.jug.montpellier.core.api.CartridgeSupport;
import org.jug.montpellier.core.api.NextEventSupport;
import org.jug.montpellier.core.api.PartnerSupport;
import org.jug.montpellier.core.api.model.NextEvent;
import org.jug.montpellier.core.controller.JugController;
import org.montpellierjug.store.jooq.tables.Event;
import org.wisdom.api.annotations.Controller;
import org.wisdom.api.annotations.Path;
import org.wisdom.api.annotations.Route;
import org.wisdom.api.annotations.View;
import org.wisdom.api.http.HttpMethod;
import org.wisdom.api.http.Result;
import org.wisdom.api.templates.Template;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

@Controller
@Path("/")
public class HomeController extends JugController {

    @View("home")
    Template template;

    @Requires
    DSLContext dslContext;

    public HomeController(@Requires CartridgeSupport cartridgeSupport, @Requires NextEventSupport nextEventSupport, @Requires PartnerSupport partnerSupport) {
        super(cartridgeSupport, nextEventSupport, partnerSupport);
    }

    @Route(method = HttpMethod.GET, uri = "")
    public Result welcome() {
        List<org.montpellierjug.store.jooq.tables.pojos.Event> eventsPojo = dslContext
                .select().from(Event.EVENT)
                .where(Event.EVENT.OPEN.equal(false))
                .and(Event.EVENT.DATE.lessThan(new Timestamp(Calendar.getInstance().getTime().getTime())))
                .orderBy(Event.EVENT.DATE.desc())
                .limit(3)
                .fetchInto(org.montpellierjug.store.jooq.tables.pojos.Event.class);
        List<NextEvent> events = new ArrayList<>();
        for(org.montpellierjug.store.jooq.tables.pojos.Event eventPojo : eventsPojo) {
            events.add(NextEvent.fromPojo(eventPojo));
        }
        return renderRoot(template, "events", events);
    }

}

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
package org.jug.montpellier.events.controller;

import model.News;
import org.apache.felix.ipojo.annotations.Requires;
import org.jug.montpellier.core.api.CartridgeConsumer;
import org.wisdom.api.DefaultController;
import org.wisdom.api.annotations.Controller;
import org.wisdom.api.annotations.Path;
import org.wisdom.api.annotations.Route;
import org.wisdom.api.annotations.View;
import org.wisdom.api.http.HttpMethod;
import org.wisdom.api.http.Result;
import org.wisdom.api.templates.Template;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@Path("/events")
public class EventsController extends DefaultController {

    @Requires
    CartridgeConsumer cartridgeConsumer;

    @View("events")
    Template template;

    @Route(method = HttpMethod.GET, uri = "/")
    public Result news() {
        Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("cartridges", cartridgeConsumer.cartridges());
        return ok(render(template, parameters));
    }

}

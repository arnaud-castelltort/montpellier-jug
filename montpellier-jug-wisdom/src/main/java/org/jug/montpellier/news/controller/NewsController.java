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
package org.jug.montpellier.news.controller;

import org.jug.montpellier.news.models.News;
import org.apache.felix.ipojo.annotations.Requires;
import org.jug.montpellier.core.api.CartridgeSupport;
import org.jug.montpellier.core.controller.JugController;
import org.wisdom.api.annotations.Controller;
import org.wisdom.api.annotations.Path;
import org.wisdom.api.annotations.Route;
import org.wisdom.api.annotations.View;
import org.wisdom.api.http.HttpMethod;
import org.wisdom.api.http.Result;
import org.wisdom.api.templates.Template;

import java.util.Arrays;
import java.util.List;

@Controller
@Path("/news")
public class NewsController extends JugController {

    @Requires
    CartridgeSupport cartridgeSupport;

    @View("news")
    Template template;
    
    List<News> buildNews() {
        return Arrays.asList(
            new News("Nouveau site", "Trop bien ;)")
        );
    }
    
    @Route(method = HttpMethod.GET, uri = "/")
    public Result news() {
        return ok(render(template, new ParameterBuilder().add("news", buildNews()).setCartridges(cartridgeSupport).build()));
    }

}

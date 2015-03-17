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
package sample;

import java.util.*;

import org.apache.felix.ipojo.annotations.Requires;
import org.jug.montpellier.core.api.CartridgeSupport;
import org.jug.montpellier.core.api.NextEventSupport;
import org.jug.montpellier.core.api.PartnerSupport;
import org.jug.montpellier.core.controller.JugController;
import org.jug.montpellier.cartridges.news.models.News;
import org.wisdom.api.annotations.*;
import org.wisdom.api.http.HttpMethod;
import org.wisdom.api.http.Result;
import org.wisdom.api.templates.Template;
import services.PropertiesForm;

@Controller
@Path("/deprecated")
public class WelcomeController extends JugController {


    /**
     * Injects a template named 'welcome'.
     */
    @View("welcome")
    Template welcome;

    public WelcomeController(@Requires CartridgeSupport cartridgeSupport, @Requires NextEventSupport nextEventSupport, @Requires PartnerSupport partnerSupport) {
        super(cartridgeSupport, nextEventSupport, partnerSupport);
    }

    /**
     * The action method returning the welcome page. It handles
     * HTTP GET request on the "/" URL.
     *
     * @return the welcome page
     */
    @Route(method = HttpMethod.GET, uri = "/")
    public Result welcome() {
        return renderRoot(welcome, "welcome","Welcome to Wisdom Framework");
    }

    /**
     * NEWS PART => To be move into the right controller
     */

    @View("news")
    Template news;

    List<News> news() {
        return Arrays.asList(
                new News("Nouveau site", "Trop trop bien ;)")
        );
    }

    /**
     * FORM NEWS PART
     */

    @View("form")
    Template formTemplate;

    @Requires
    PropertiesForm propertiesForm;

    @Route(method = HttpMethod.GET, uri = "/addnews")
    public Result addNewsGet() {
        return ok(render(formTemplate, "properties", propertiesForm.get(News.class), "urlSubmit", "/addnews"));
    }

    @Route(method = HttpMethod.GET, uri = "/addnews2")
    public Result addNewsGet2() {
        News news = new News();
        news.content = "This is a new's content";
        news.title = "The top news of the world!";
        news.valid = true;
        return ok(news);
    }
    @Route(method = HttpMethod.POST, uri = "/addnews2")
    public Result addNewsPost2(@Body News myNews) {
        List<News> allNews = new ArrayList<News>(news());
        allNews.add(myNews);
        return ok(render(news, "news", allNews));
    }

    @Route(method = HttpMethod.POST, uri = "/addnews")
    public Result addNewsPost(@Body News myNews) {
        List<News> allNews = new ArrayList<News>(news());
        allNews.add(myNews);
        return ok(render(news, "news", allNews));
    }

    @Route(method = HttpMethod.GET, uri = "/modnews/{id}")
    public Result modNewsGet(@Parameter("id") int id) {

        return ok(render(formTemplate, "properties", propertiesForm.get(news().get(id)), "urlSubmit", "/modnews/" + id));
    }

    @Route(method = HttpMethod.POST, uri = "/modnews/{id}")
    public Result modNewsPost(@Body News myNews, @Parameter("id") int id) {
        List<News> allNews = new ArrayList<News>(news());
        allNews.remove(id);
        allNews.add(myNews);
        return ok(render(news, "news", allNews));
    }

}

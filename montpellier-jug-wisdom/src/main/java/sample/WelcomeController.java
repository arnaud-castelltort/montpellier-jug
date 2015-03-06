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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import model.News;

import org.apache.felix.ipojo.annotations.Requires;
import org.wisdom.api.DefaultController;
import org.wisdom.api.annotations.*;
import org.wisdom.api.http.HttpMethod;
import org.wisdom.api.http.Result;
import org.wisdom.api.templates.Template;
import services.PropertiesForm;

/**
 * Your first Wisdom Controller.
 */
@Controller
public class WelcomeController extends DefaultController {

    /**
     * Injects a template named 'welcome'.
     */
    @View("welcome")
    Template welcome;
    
    /**
     * The action method returning the welcome page. It handles
     * HTTP GET request on the "/" URL.
     *
     * @return the welcome page
     */
    @Route(method = HttpMethod.GET, uri = "/")
    public Result welcome() {
        return ok(render(welcome, "welcome", "Welcome to Wisdom Framework!"));
    }
    
    /**
     * NEWS PART
     */
    
    @View("news")
    Template news;

    List<News> news() {
        return Arrays.asList(
            new News("Nouveau site", "Trop bien ;)")
        );
    }
    
    @Route(method = HttpMethod.GET, uri = "/news")
    public Result defaultView() {
        return ok(render(news, "news", news()));
    }

    /**
     * FORM NEWS PART
     */

    @View("layouts/form")
    Template formTemplate;

    @Requires
    PropertiesForm propertiesForm;

    @Route(method = HttpMethod.GET, uri = "/addnews")
    public Result addNewsGet() {
        return ok(render(formTemplate, "properties", propertiesForm.get(News.class), "urlSubmit", "/addnews"));
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

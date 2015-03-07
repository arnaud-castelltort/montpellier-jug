package org.jug.montpellier.speakers.controller;

import org.apache.felix.ipojo.annotations.Requires;
import org.montpellierjug.store.jooq.tables.daos.SpeakerDao;
import org.springframework.util.MimeTypeUtils;
import org.wisdom.api.DefaultController;
import org.wisdom.api.annotations.Controller;
import org.wisdom.api.annotations.Path;
import org.wisdom.api.annotations.Route;
import org.wisdom.api.http.HttpMethod;
import org.wisdom.api.http.Result;
import org.wisdom.api.http.Results;
import scala.Char;

import javax.activation.MimeType;
import java.nio.charset.Charset;

/**
 * Created by chelebithil on 06/03/15.
 */
@Controller
@Path("/api/speaker")
public class SpeakerController extends DefaultController{

    @Requires
    SpeakerDao speakerDao;

    @Route(method = HttpMethod.GET, uri = "/")
    public Result all(){

        return json().render(speakerDao.findAll()).with(Charset.defaultCharset());
    }

}

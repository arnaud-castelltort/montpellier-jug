package org.jug.montpellier.cartridges.speakers.controllers;

import org.apache.felix.ipojo.annotations.Requires;
import org.montpellierjug.store.jooq.tables.daos.SpeakerDao;
import org.wisdom.api.DefaultController;
import org.wisdom.api.annotations.Controller;
import org.wisdom.api.annotations.Path;
import org.wisdom.api.annotations.Route;
import org.wisdom.api.http.HttpMethod;
import org.wisdom.api.http.Result;
import org.wisdom.api.security.Authenticated;
import org.wisdom.oauth2.OAuth2WisdomAuthenticator;
import org.wisdom.oauth2.controller.Role;

import java.nio.charset.Charset;

/**
 * Created by chelebithil on 06/03/15.
 */
@Authenticated(OAuth2WisdomAuthenticator.NAME)
@Controller
@Path("/api/speaker")
public class SpeakerController extends DefaultController{

    @Requires
    SpeakerDao speakerDao;

    @Route(method = HttpMethod.GET, uri = "/")
  //  @Role("admin")
    public Result all(){

        return json().render(speakerDao.findAll()).with(Charset.defaultCharset());
    }

}

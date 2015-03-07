package org.jug.montpellier.news.services;

import org.apache.felix.ipojo.annotations.Component;
import org.apache.felix.ipojo.annotations.Instantiate;
import org.apache.felix.ipojo.annotations.Provides;
import org.apache.felix.ipojo.annotations.Requires;
import org.jug.montpellier.core.api.Cartridge;
import org.jug.montpellier.core.api.CartridgeService;
import org.jug.montpellier.news.controller.NewsController;
import org.wisdom.api.router.Router;
import sample.WelcomeController;

@Component
@Provides(specifications = CartridgeService.class)
@Instantiate
public class NewsCartridge implements CartridgeService {

    @Requires
    private Router router;

    private Cartridge definition = new Cartridge("News", router.getReverseRouteFor(NewsController.class, "news"));

    @Override
    public Cartridge cartridge() {
        return definition;
    }
}

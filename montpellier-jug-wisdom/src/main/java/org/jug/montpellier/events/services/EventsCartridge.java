package org.jug.montpellier.events.services;

import org.apache.felix.ipojo.annotations.Component;
import org.apache.felix.ipojo.annotations.Instantiate;
import org.apache.felix.ipojo.annotations.Provides;
import org.apache.felix.ipojo.annotations.Requires;
import org.jug.montpellier.core.api.Cartridge;
import org.jug.montpellier.core.api.CartridgeService;
import org.jug.montpellier.events.controller.EventsController;
import org.jug.montpellier.news.controller.NewsController;
import org.wisdom.api.router.Router;

@Component
@Provides(specifications = CartridgeService.class)
@Instantiate
public class EventsCartridge implements CartridgeService {

    @Requires
    private Router router;

    private Cartridge definition = new Cartridge("Events", router.getReverseRouteFor(EventsController.class, "news"));

    @Override
    public Cartridge cartridge() {
        return definition;
    }
}

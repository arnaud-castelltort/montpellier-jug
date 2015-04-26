package org.jug.montpellier.cartridges.events.services;

import org.jug.montpellier.core.controller.JugController;
import org.wisdom.api.http.Result;

/**
 * Created by manland on 26/04/15.
 */
public interface EventsService {

    public Result renderEvents(JugController.Templatable templatable);

    public Result renderEvents(JugController.Templatable templatable, Long id);

}

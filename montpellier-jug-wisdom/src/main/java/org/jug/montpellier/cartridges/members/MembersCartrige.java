package org.jug.montpellier.cartridges.members;

import org.apache.felix.ipojo.annotations.Component;
import org.apache.felix.ipojo.annotations.Instantiate;
import org.apache.felix.ipojo.annotations.Provides;
import org.apache.felix.ipojo.annotations.Requires;
import org.jug.montpellier.cartridges.members.controllers.MembersController;
import org.jug.montpellier.core.api.Cartridge;
import org.wisdom.api.router.Router;

/**
 * Created by chelebithil on 26/04/15.
 */
@Component
@Provides(specifications = Cartridge.class)
@Instantiate
public class MembersCartrige implements Cartridge {

    @Requires
    Router router;

    @Override
    public String label() {
        return "Members";
    }

    @Override
    public String routeUri() {
        return router.getReverseRouteFor(MembersController.class, "all");
    }

    @Override
    public int position() {
        return 5;
    }
}

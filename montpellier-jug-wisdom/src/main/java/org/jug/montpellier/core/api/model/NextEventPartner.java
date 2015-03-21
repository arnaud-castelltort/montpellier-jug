package org.jug.montpellier.core.api.model;

import org.montpellierjug.store.jooq.tables.pojos.Eventpartner;

/**
 * Created by manland on 14/03/15.
 */
public class NextEventPartner {

    public Long id;
    public String name;

    static public NextEventPartner fromPojo(Eventpartner eventpartnerPojo) {
        NextEventPartner partner = new NextEventPartner();
        partner.id = eventpartnerPojo.getId();
        partner.name = eventpartnerPojo.getName();
        return partner;
    }

}

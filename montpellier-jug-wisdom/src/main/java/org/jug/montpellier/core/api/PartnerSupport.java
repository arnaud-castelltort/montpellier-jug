package org.jug.montpellier.core.api;

import org.montpellierjug.store.jooq.tables.pojos.Yearpartner;

import java.util.List;

/**
 * Created by manland on 16/03/15.
 */
public interface PartnerSupport {

    public List<Yearpartner> getPartners();

}

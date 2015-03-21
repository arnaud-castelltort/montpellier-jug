package org.jug.montpellier.core.api.impl;

import org.apache.felix.ipojo.annotations.Component;
import org.apache.felix.ipojo.annotations.Instantiate;
import org.apache.felix.ipojo.annotations.Provides;
import org.apache.felix.ipojo.annotations.Requires;
import org.jooq.DSLContext;
import org.jug.montpellier.core.api.PartnerSupport;
import org.montpellierjug.store.jooq.tables.Event;
import org.montpellierjug.store.jooq.tables.daos.YearpartnerDao;
import org.montpellierjug.store.jooq.tables.pojos.Yearpartner;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Component
@Provides(specifications = PartnerSupport.class)
@Instantiate
public class PartnerSupportImpl implements PartnerSupport {

    @Requires
    DSLContext dslContext;

    @Override
    public List<Yearpartner> getPartners() {
        Timestamp today = new Timestamp(Calendar.getInstance().getTime().getTime());
        List<Yearpartner> partners = dslContext
                .select().from(org.montpellierjug.store.jooq.tables.Yearpartner.YEARPARTNER)
                .where(org.montpellierjug.store.jooq.tables.Yearpartner.YEARPARTNER.STOPDATE.greaterOrEqual(today))
                .fetchInto(Yearpartner.class);
        return partners;
    }

}

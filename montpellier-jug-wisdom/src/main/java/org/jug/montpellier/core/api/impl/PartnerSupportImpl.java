package org.jug.montpellier.core.api.impl;

import org.apache.felix.ipojo.annotations.Component;
import org.apache.felix.ipojo.annotations.Instantiate;
import org.apache.felix.ipojo.annotations.Provides;
import org.apache.felix.ipojo.annotations.Requires;
import org.jug.montpellier.core.api.PartnerSupport;
import org.montpellierjug.store.jooq.tables.daos.YearpartnerDao;
import org.montpellierjug.store.jooq.tables.pojos.Yearpartner;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Component
@Provides(specifications = PartnerSupport.class)
@Instantiate
public class PartnerSupportImpl implements PartnerSupport {

    @Requires
    YearpartnerDao yearpartnerDao;

    @Override
    public List<Yearpartner> getPartners() {
        long today = Calendar.getInstance().getTime().getTime();
        List<Yearpartner> partners = new ArrayList<>();
        //TODO: make it with a request in bd !!??
        for(Yearpartner  yearpartner : yearpartnerDao.findAll()) {
            if(yearpartner.getStopdate().getTime() > today) {
                partners.add(yearpartner);
            }
        }
        return partners;
    }

}

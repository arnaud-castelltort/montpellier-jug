package org.jug.montpellier.core.api.impl;

import org.apache.felix.ipojo.annotations.*;
import org.jooq.DSLContext;
import org.jug.montpellier.core.api.Cartridge;
import org.jug.montpellier.core.api.CartridgeSupport;
import org.jug.montpellier.core.api.JugSupport;
import org.jug.montpellier.core.api.NextEventSupport;
import org.jug.montpellier.core.api.model.NextEvent;
import org.montpellierjug.store.jooq.tables.daos.EventpartnerDao;
import org.montpellierjug.store.jooq.tables.daos.SpeakerDao;
import org.montpellierjug.store.jooq.tables.daos.TalkDao;
import org.montpellierjug.store.jooq.tables.pojos.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.TreeSet;
import java.util.stream.Collectors;

/**
 * Created by Eric Taix on 22/03/15.
 */
@Component
@Provides(specifications = JugSupport.class)
@Instantiate
public class JugSupportImpl implements JugSupport {

    private static final Logger LOG = LoggerFactory.getLogger(JugSupportImpl.class);

    private TreeSet<Cartridge> cartridges = new TreeSet<>((Cartridge cartridge1, Cartridge cartridge2) -> Integer.compare(cartridge1.position(), cartridge2.position()));

    @Requires
    DSLContext dslContext;
    @Requires
    EventpartnerDao eventpartnerDao;
    @Requires
    SpeakerDao speakerDao;
    @Requires
    TalkDao talkDao;

    @Override
    public List<Cartridge> cartridges() {
        return new ArrayList<Cartridge>(cartridges);
    }

    public NextEvent getNextEvent() {
        Event event = dslContext.select()
                .from(org.montpellierjug.store.jooq.tables.Event.EVENT)
                .orderBy(org.montpellierjug.store.jooq.tables.Event.EVENT.DATE.desc())
                .limit(1)
                .fetchOneInto(org.montpellierjug.store.jooq.tables.pojos.Event.class);
        NextEvent nextEvent = null;
        if(event != null) {
            Eventpartner partner = eventpartnerDao.findById(event.getPartnerId());
            List<Speaker> speakers = talkDao.fetchByEventId(event.getId()).stream().map((Talk talk) -> {
                return speakerDao.fetchOneById(talk.getSpeakerId());
            }).collect(Collectors.toList());
            nextEvent = NextEvent.fromPojo(event, speakers, partner);
        }
        return nextEvent;
    }


    @Override
    public List<Yearpartner> getPartners() {
        Timestamp today = new Timestamp(Calendar.getInstance().getTime().getTime());
        List<Yearpartner> partners = dslContext
                .select().from(org.montpellierjug.store.jooq.tables.Yearpartner.YEARPARTNER)
                .where(org.montpellierjug.store.jooq.tables.Yearpartner.YEARPARTNER.STOPDATE.greaterOrEqual(today))
                .fetchInto(Yearpartner.class);
        return partners;
    }

    @Bind(specification = Cartridge.class, aggregate = true)
    public void bindCartridges(Cartridge cartridge) {
        LOG.info("Adding cratridge " + cartridge);
        if (cartridge != null) {
            cartridges.add(cartridge);
        }
    }

    @Unbind(specification = Cartridge.class, aggregate = true)
    public void unbindCartridges(Cartridge cartridge) {
        LOG.info("Removing cratridge " + cartridge);
        if (cartridge != null) {
            cartridges.remove(cartridge);
        }
    }
}

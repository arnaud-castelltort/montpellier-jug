package org.jug.montpellier.core.api.impl;

import org.apache.felix.ipojo.annotations.Component;
import org.apache.felix.ipojo.annotations.Instantiate;
import org.apache.felix.ipojo.annotations.Provides;
import org.apache.felix.ipojo.annotations.Requires;
import org.jooq.DSLContext;
import org.jug.montpellier.core.api.NextEventSupport;
import org.jug.montpellier.core.api.model.NextEvent;
import org.montpellierjug.store.jooq.tables.daos.EventpartnerDao;
import org.montpellierjug.store.jooq.tables.daos.SpeakerDao;
import org.montpellierjug.store.jooq.tables.daos.TalkDao;
import org.montpellierjug.store.jooq.tables.pojos.Event;
import org.montpellierjug.store.jooq.tables.pojos.Eventpartner;
import org.montpellierjug.store.jooq.tables.pojos.Speaker;
import org.montpellierjug.store.jooq.tables.pojos.Talk;

import java.util.ArrayList;
import java.util.List;

@Component
@Provides(specifications = NextEventSupport.class)
@Instantiate
public class NextEventSupportImpl implements NextEventSupport {
    @Requires
    DSLContext dslContext;

    @Requires
    EventpartnerDao eventpartnerDao;

    @Requires
    SpeakerDao speakerDao;

    @Requires
    TalkDao talkDao;

    public NextEvent getNextEvent() {
        Event event = dslContext.select()
                .from(org.montpellierjug.store.jooq.tables.Event.EVENT)
                .orderBy(org.montpellierjug.store.jooq.tables.Event.EVENT.DATE.desc())
                .limit(1)
                .fetchOneInto(org.montpellierjug.store.jooq.tables.pojos.Event.class);
        NextEvent nextEvent = null;
        if(event != null) {
            Eventpartner partner = eventpartnerDao.findById(event.getPartnerId());
            List<Talk> talks = talkDao.fetchByEventId(event.getId());
            List<Speaker> speakers = new ArrayList<>();
            for(Talk talk : talks) {
                speakers.add(speakerDao.fetchOneById(talk.getSpeakerId()));
            }
            nextEvent = NextEvent.fromPojo(event, speakers, partner);
        }
        return nextEvent;
    }
}

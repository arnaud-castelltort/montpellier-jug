package org.jug.montpellier.core.api.model;

import org.montpellierjug.store.jooq.tables.pojos.Event;
import org.montpellierjug.store.jooq.tables.pojos.Eventpartner;
import org.montpellierjug.store.jooq.tables.pojos.Speaker;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by manland on 14/03/15.
 */
public class NextEvent {

    public Long id;
    public String title;
    public String description;
    public String location;
    public String registrationurl;
    public String date;
    public NextEventPartner partner;
    public List<NextEventSpeaker> speakers;

    public static NextEvent fromPojo(Event eventPojo, List<Speaker> speakersPojo, Eventpartner partnerPojo) {
        NextEvent event = new NextEvent();
        event.id = eventPojo.getId();
        event.title = eventPojo.getTitle();
        event.description = eventPojo.getDescription();
        event.location = eventPojo.getLocation();
        event.registrationurl = eventPojo.getRegistrationurl();
        event.date = eventPojo.getDate().toString();

        event.speakers = new ArrayList<>();
        for(Speaker speaker : speakersPojo) {
            event.speakers.add(NextEventSpeaker.fromPojo(speaker));
        }

        if(partnerPojo != null) {
            event.partner = NextEventPartner.fromPojo(partnerPojo);
        }
        return event;
    }

}

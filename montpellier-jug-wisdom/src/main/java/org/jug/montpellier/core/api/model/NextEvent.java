package org.jug.montpellier.core.api.model;

import org.montpellierjug.store.jooq.tables.pojos.Event;
import org.montpellierjug.store.jooq.tables.pojos.Eventpartner;
import org.montpellierjug.store.jooq.tables.pojos.Speaker;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

/**
 * Created by manland on 14/03/15.
 */
public class NextEvent {

    public Long id;
    public String title;
    public String description;
    public String location;
    public String registrationurl;
    public Integer capicity;
    public String date;
    public NextEventPartner partner;
    public List<NextEventSpeaker> speakers;

    public static NextEvent fromPojo(Event eventPojo) {
        return fromPojo(eventPojo, null, null);
    }

    public static NextEvent fromPojo(Event eventPojo, List<Speaker> speakersPojo, Eventpartner partnerPojo) {
        NextEvent event = new NextEvent();
        event.id = eventPojo.getId();
        event.title = eventPojo.getTitle();
        event.description = eventPojo.getDescription();
        event.location = eventPojo.getLocation();
        event.registrationurl = eventPojo.getRegistrationurl();
        event.capicity = eventPojo.getCapacity();
        event.date = DateFormat.getDateInstance(DateFormat.LONG, Locale.FRANCE).format(eventPojo.getDate());

        event.speakers = new ArrayList<>();
        if(speakersPojo != null) {
            for (Speaker speaker : speakersPojo) {
                event.speakers.add(NextEventSpeaker.fromPojo(speaker));
            }
        }

        if(partnerPojo != null) {
            event.partner = NextEventPartner.fromPojo(partnerPojo);
        }
        return event;
    }

}

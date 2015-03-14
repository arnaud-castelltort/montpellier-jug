package org.jug.montpellier.core.api.model;

import org.montpellierjug.store.jooq.tables.pojos.Speaker;

/**
 * Created by manland on 14/03/15.
 */
public class NextEventSpeaker {

    public Long id;
    public String photourl;
    public String fullname;
    public String activity;
    public String compan;
    public String url;

    static public NextEventSpeaker fromPojo(Speaker speakerPojo) {
        NextEventSpeaker speaker = new NextEventSpeaker();
        speaker.id = speakerPojo.getId();
        speaker.photourl = speakerPojo.getPhotourl();
        speaker.fullname = speakerPojo.getFullname();
        speaker.activity = speakerPojo.getActivity();
        speaker.compan = speakerPojo.getCompan();
        speaker.url = speakerPojo.getUrl().startsWith("http://") ? speakerPojo.getUrl() : "http://" + speakerPojo.getUrl();
        return speaker;
    }

}

package org.jug.montpellier.models;

import org.jug.montpellier.forms.annotations.RenderableProperty;
import org.jug.montpellier.forms.services.editors.extended.BigStringEditorService;
import org.jug.montpellier.forms.services.editors.specific.SpeakerChooserEditorService;
import org.montpellierjug.store.jooq.tables.interfaces.ITalk;

/**
 * Created by Eric Taix on 21/03/2015.
 */
public class Talk implements ITalk {

    @RenderableProperty(visible = false)
    private Long id;
    @RenderableProperty(displayLabel = "Title")
    private String title;
    @RenderableProperty(displayLabel = "Teaser", editorService = BigStringEditorService.class)
    private String teaser;
    @RenderableProperty(displayLabel = "Start time")
    private String datetime;
    private Long eventid;
    private Integer orderinevent;
    @RenderableProperty(displayLabel = "Speaker", editorService = SpeakerChooserEditorService.class)
    private Long speakerid;

    public static Talk build(ITalk from) {
        Talk talk = new Talk();
        talk.from(from);
        return talk;
    }

    @Override
    public ITalk setId(Long value) {
        id = value;
        return this;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public ITalk setOrderinevent(Integer value) {
        orderinevent = value;
        return this;
    }

    @Override
    public Integer getOrderinevent() {
        return orderinevent;
    }

    @Override
    public ITalk setTeaser(String value) {
        teaser = value;
        return this;
    }

    @Override
    public String getTeaser() {
        return teaser;
    }

    @Override
    public ITalk setDatetime(String value) {
        datetime = value;
        return this;
    }

    @Override
    public String getDatetime() {
        return datetime;
    }

    @Override
    public ITalk setTitle(String value) {
        title = value;
        return this;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public ITalk setEventId(Long value) {
        eventid = value;
        return this;
    }

    @Override
    public Long getEventId() {
        return eventid;
    }

    @Override
    public ITalk setSpeakerId(Long value) {
        speakerid = value;
        return this;
    }

    @Override
    public Long getSpeakerId() {
        return speakerid;
    }

    @Override
    public void from(ITalk from) {
        setId(from.getId());
        setOrderinevent(from.getOrderinevent());
        setTeaser(from.getTeaser());
        setDatetime(from.getDatetime());
        setTitle(from.getTitle());
        setEventId(from.getEventId());
        setSpeakerId(from.getSpeakerId());
    }

    @Override
    public <E extends ITalk> E into(E into) {
        into.from(this);
        return into;
    }
}

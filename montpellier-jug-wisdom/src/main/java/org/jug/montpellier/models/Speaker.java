package org.jug.montpellier.models;

import org.jug.montpellier.forms.annotations.RenderableProperty;
import org.jug.montpellier.forms.services.editors.extended.BigStringEditorService;
import org.jug.montpellier.forms.services.editors.extended.ImageUrlEditorService;
import org.montpellierjug.store.jooq.tables.interfaces.ISpeaker;

/**
 * Created by Eric Taix on 09/03/2015.
 */
public class Speaker implements ISpeaker {

    @RenderableProperty(visible = false)
    private Long id;

    @RenderableProperty(displayLabel = "Fullname")
    private String fullname;

    @RenderableProperty(displayLabel = "Activity", description = "Main or opensource activity")
    private String activity;

    @RenderableProperty(displayLabel = "Company", description = "Company or Opensource project")
    private String compan;

    @RenderableProperty(displayLabel = "Company Web site")
    private String url;

    @RenderableProperty(displayLabel = "Personal Web site")
    private String personalurl;

    @RenderableProperty(displayLabel = "Email", description = "This email will not be shown")
    private String email;

    @RenderableProperty(displayLabel = "Tell us about you", editorService = BigStringEditorService.class)
    private String description;

    @RenderableProperty(displayLabel = "JUG member")
    private boolean jugMember;

    @RenderableProperty(displayLabel = "Role in the JUG")
    private String memberfct;

    @RenderableProperty(displayLabel = "Photo URL", editorService = ImageUrlEditorService.class)
    private String photourl;

    public static Speaker build(ISpeaker from) {
        Speaker speaker = new Speaker();
        speaker.from(from);
        return speaker;
    }

    @Override
    public ISpeaker setId(Long value) {
        this.id = value;
        return this;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public ISpeaker setActivity(String value) {
        this.activity = value;
        return this;
    }

    @Override
    public String getActivity() {
        return activity;
    }

    @Override
    public ISpeaker setCompan(String value) {
        this.compan = value;
        return this;
    }

    @Override
    public String getCompan() {
        return compan;
    }

    @Override
    public ISpeaker setDescription(String value) {
        this.description = value;
        return this;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public ISpeaker setFullname(String value) {
        this.fullname = value;
        return this;
    }

    @Override
    public String getFullname() {
        return fullname;
    }

    @Override
    public ISpeaker setJugmember(Boolean value) {
        this.jugMember = value != null ? value : false;
        return this;
    }

    @Override
    public Boolean getJugmember() {
        return jugMember;
    }

    @Override
    public ISpeaker setMemberfct(String value) {
        this.memberfct = value;
        return this;
    }

    @Override
    public String getMemberfct() {
        return memberfct;
    }

    @Override
    public ISpeaker setPhotourl(String value) {
        this.photourl = value;
        return this;
    }

    @Override
    public String getPhotourl() {
        return photourl;
    }

    @Override
    public ISpeaker setUrl(String value) {
        this.url = value;
        return this;
    }

    @Override
    public String getUrl() {
        return url;
    }

    @Override
    public ISpeaker setEmail(String value) {
        this.email = value;
        return this;
    }

    @Override
    public String getEmail() {
        return email;
    }

    @Override
    public ISpeaker setPersonalurl(String value) {
        this.personalurl = value;
        return this;
    }

    @Override
    public String getPersonalurl() {
        return personalurl;
    }

    /**
     * Each POJO MUST implements this method
     *
     * @param from
     */
    @Override
    public void from(ISpeaker from) {
        setId(from.getId());
        setActivity(from.getActivity());
        setCompan(from.getCompan());
        setDescription(from.getDescription());
        setFullname(from.getFullname());
        setJugmember(from.getJugmember());
        setMemberfct(from.getMemberfct());
        setPhotourl(from.getPhotourl());
        setUrl(from.getUrl());
        setEmail(from.getEmail());
        setPersonalurl(from.getPersonalurl());
    }

    @Override
    public <E extends ISpeaker> E into(E into) {
        into.from(this);
        return into;
    }
}

package org.jug.montpellier.admin.models;

import org.jug.montpellier.forms.annotations.Property;

/**
 * Created by Eric Taix on 09/03/2015.
 */
public class Model1 {

    @Property(displayLabel = "First name", description = "The firstname will be used to sign post")
    private String firstName;

    @Property(displayLabel = "Are you a male?")
    private boolean male;

    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }

    public boolean getMale() { return  male; }
    public void setMale(boolean male) { this.male = male; }
}

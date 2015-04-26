package org.jug.montpellier.core.api;

import org.jug.montpellier.models.Event;

public interface NextEventSupport {

    /**
     * @return the next event to be displayed in all views
     */
    public Event getNextEvent();

}

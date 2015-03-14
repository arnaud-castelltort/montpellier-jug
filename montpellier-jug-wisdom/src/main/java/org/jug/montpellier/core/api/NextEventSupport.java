package org.jug.montpellier.core.api;

import org.jug.montpellier.core.api.model.NextEvent;

public interface NextEventSupport {

    /**
     * @return the next event to be displayed in all views
     */
    public NextEvent getNextEvent();

}

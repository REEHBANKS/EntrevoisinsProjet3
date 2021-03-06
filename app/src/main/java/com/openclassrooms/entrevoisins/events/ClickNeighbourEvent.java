package com.openclassrooms.entrevoisins.events;

import com.openclassrooms.entrevoisins.model.Neighbour;

/**
 * Event fired when a user click a Neighbour
 */
public class ClickNeighbourEvent {

    /**
     * Neighbour to show
     */
    public Neighbour neighbour;

    /**
     * Constructor.
     *
     */
    public ClickNeighbourEvent(Neighbour neighbour) {
        this.neighbour = neighbour;
    }
}

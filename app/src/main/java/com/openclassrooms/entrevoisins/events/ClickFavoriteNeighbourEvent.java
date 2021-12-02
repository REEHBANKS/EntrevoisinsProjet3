package com.openclassrooms.entrevoisins.events;

import com.openclassrooms.entrevoisins.model.Neighbour;

/**
 * Event fired when a user click a Favorite Neighbour
 */

public class ClickFavoriteNeighbourEvent {

    /**
     * Favorite Neighbour to show
     */
    public Neighbour neighbour;

    /**
     * Constructor.
     * @param neighbour
     */
    public ClickFavoriteNeighbourEvent(Neighbour neighbour ) {
        this.neighbour = neighbour; }

}

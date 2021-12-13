package com.openclassrooms.entrevoisins.events;


import com.openclassrooms.entrevoisins.model.Neighbour;

/**
 * Event fired when a user deletes a Favorite Neighbour
 */

public class DeleteFavoriteNeighbourEvent {

    /**
     * Favorite Neighbour to delete
     */
    public Neighbour neighbour;

    /**
     * Constructor.
     *
     */
    public DeleteFavoriteNeighbourEvent(Neighbour neighbour){this.neighbour = neighbour; }
}

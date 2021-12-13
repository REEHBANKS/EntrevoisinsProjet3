package com.openclassrooms.entrevoisins.service;

import com.openclassrooms.entrevoisins.model.Neighbour;

import java.util.List;


/**
 * Neighbour API client
 */
public interface NeighbourApiService {

    /**
     * Get all my Neighbours
     *
     * @return {@link List}
     */
    List<Neighbour> getNeighbours();

    /**
     * Get all my favorite Neighbours
     *
     * @return {@link List}
     */
    List<Neighbour> getFavoriteNeighbours();

    /**
     * Deletes a neighbour
     *
     *
     */
    void deleteNeighbour(Neighbour neighbour);

    /**
     * Deletes a favorite neighbour
     *
     *
     */
    void deleteFavoriteNeighbour(Neighbour neighbour);

    /**
     * Create a neighbour
     *
     *
     */
    void createNeighbour(Neighbour neighbour);

    /**
     * Add a favorite neighbour
     *
     *
     */
    void addFavoriteNeighbour(Neighbour neighbour);

    boolean isNeighbourFavorite(Neighbour neighbour);


}

package com.openclassrooms.entrevoisins.service;

import com.openclassrooms.entrevoisins.model.Neighbour;

import java.util.List;


/**
 * Neighbour API client
 */
public interface NeighbourApiService {

    /**
     * Get all my Neighbours
     * @return {@link List}
     */
    List<Neighbour> getNeighbours();

    //TODO: Ici j'ai mimiqué les fonctions pour neighbour en version favoriteNeighbour
    /**
     * Get all my favorite Neighbours
     * @return {@link List}
     */
    List<Neighbour> getFavoriteNeighbours();

    /**
     * Deletes a neighbour
     * @param neighbour
     */
    void deleteNeighbour(Neighbour neighbour);

    /**
     * Deletes a favorite neighbour
     * @param neighbour
     */
    void deleteFavoriteNeighbour(Neighbour neighbour);

    /**
     * Create a neighbour
     * @param neighbour
     */
    void createNeighbour(Neighbour neighbour);

    /**
     * Add a favorite neighbour
     * @param neighbour
     */
    void addFavoriteNeighbour(Neighbour neighbour);

    boolean isNeighbourFavorite(Neighbour neighbour);


}

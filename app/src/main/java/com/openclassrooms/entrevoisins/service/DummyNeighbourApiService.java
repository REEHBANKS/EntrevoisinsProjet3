package com.openclassrooms.entrevoisins.service;

import com.openclassrooms.entrevoisins.model.Neighbour;

import java.util.ArrayList;
import java.util.List;

/**
 * Dummy mock for the Api
 */
public class DummyNeighbourApiService implements  NeighbourApiService {

    private final List<Neighbour> neighbours = DummyNeighbourGenerator.generateNeighbours();

    //TODO: Ici j'ai ajouté la liste de favoris avec toutes les fonctions qui vont avec
    private final List<Neighbour> favoriteNeighbours = new ArrayList<>();


    /**
     * {@inheritDoc}
     */
    @Override
    public List<Neighbour> getNeighbours() {
        return neighbours;
    }

    //TODO: J'ai ajouté les fonctions dont on a besoin
    @Override
    public List<Neighbour> getFavoriteNeighbours() {
        return favoriteNeighbours;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteNeighbour(Neighbour neighbour) {

        neighbours.remove(neighbour);
    }

    @Override
    public void deleteFavoriteNeighbour(Neighbour neighbour) {
        favoriteNeighbours.remove(neighbour);
    }

    /**
     * {@inheritDoc}
     * @param neighbour
     */
    @Override
    public void createNeighbour(Neighbour neighbour) {
        neighbours.add(neighbour);
    }

    @Override
    public void addFavoriteNeighbour(Neighbour neighbour) {
        if( !isNeighbourFavorite(neighbour)) {
            favoriteNeighbours.add(neighbour);
        }
    }

    @Override
    public boolean isNeighbourFavorite(Neighbour neighbour) {
        return favoriteNeighbours.contains(neighbour);
    }
}

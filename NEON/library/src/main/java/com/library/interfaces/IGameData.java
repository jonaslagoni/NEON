/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.library.interfaces;

import java.util.Collection;
import java.util.List;

/**
 * @author emil
 */
public interface IGameData {

    /**
     * Add observers
     *
     * @param observer object to receive update notification
     */
    void addObserver(IObserver observer);

    /**
     * Add a collection of placeables
     *
     * @param placables collection
     */
    void addPlaceables(Collection<IPlaceable> placables);

    /**
     * Removes a collection of placeables
     *
     * @param placables collection
     */
    void removePlaceables(Collection<IPlaceable> placables);

    /**
     * Returns a list of placeables
     *
     * @return list
     */
    List<IPlaceable> getPlaceables();

    /**
     * Add IStatusText
     *
     * @param text update object
     */
    void addStatusText(IStatusText text);

    /**
     * Remove IStatusText
     *
     * @param text update object
     */
    void removeStatusText(IStatusText text);

    /**
     * Returns a list of IStatusText
     *
     * @return list
     */
    List<IStatusText> getStatusText();

}

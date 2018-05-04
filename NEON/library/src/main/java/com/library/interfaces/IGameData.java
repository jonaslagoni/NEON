/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.library.interfaces;

import java.util.Collection;
import java.util.List;

/**
 *
 * @author emil
 */
public interface IGameData {

    /**
     * ???
     * @param observer 
     */
    void addObserver(IViewObserver observer);

    /**
     * ???
     * @param placables 
     */
    void addPlaceables(Collection<IPlaceable> placables);
    
    /**
     * 
     * @param placables 
     */
    void removePlaceables(Collection<IPlaceable> placables);

    /**
     * ???
     * @return 
     */
    List<IPlaceable> getPlaceables();

    /**
     * ???
     * @param text 
     */
    void addStatusText(IStatusText text);

    /**
     * 
     * @param text 
     */
    void removeStatusText(IStatusText text);

    /**
     * 
     * @return 
     */
    List<IStatusText> getStatusText();

}

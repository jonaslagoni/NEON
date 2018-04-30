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

    void addObserver(IViewObserver observer);

    void addPlaceables(Collection<IPlaceable> placables);

    void removePlaceables(Collection<IPlaceable> placables);

    List<IPlaceable> getPlaceables();

    void addStatusText(IStatusText text);

    void removeStatusText(IStatusText text);

    List<IStatusText> getStatusText();

}

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

    public void addObserver(IViewObserver observer);

    public void addPlaceables(Collection<IPlaceable> placables);

    public void removePlaceables(Collection<IPlaceable> placables);

    public List<IPlaceable> getPlaceables();

    public void addStatusText(IStatusText text);

    public void removeStatusText(IStatusText text);

    public List<IStatusText> getStatusText();

}

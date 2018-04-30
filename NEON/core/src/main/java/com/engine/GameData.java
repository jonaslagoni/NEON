package com.engine;

import com.library.interfaces.IStatusText;
import com.library.interfaces.IGameData;
import com.library.interfaces.IPlaceable;
import java.util.*;
import com.library.interfaces.IViewObserver;

public class GameData implements IGameData {

    private final List<IPlaceable> placeables = new ArrayList<>();
    private final List<IViewObserver> observers = new ArrayList<>();
    private final List<IStatusText> statusTexts = new ArrayList<>();

    @Override
    public void addObserver(IViewObserver observer) {
        observers.add(observer);
    }

    @Override
    public List<IPlaceable> getPlaceables() {
        return placeables;
    }

    @Override
    public void addStatusText(IStatusText text) {
        statusTexts.add(text);
        observers.forEach(IViewObserver::updateView);
    }

    @Override
    public void removeStatusText(IStatusText text) {
        statusTexts.remove(text);
        observers.forEach(IViewObserver::updateView);
    }

    @Override
    public List<IStatusText> getStatusText() {
        return statusTexts;
    }

    @Override
    public void addPlaceables(Collection<IPlaceable> placables) {
        placeables.addAll(placables);
        observers.forEach(IViewObserver::updateView);
    }

    @Override
    public void removePlaceables(Collection<IPlaceable> placables) {
        placeables.removeAll(placables);
        observers.forEach(IViewObserver::updateView);
    }
}

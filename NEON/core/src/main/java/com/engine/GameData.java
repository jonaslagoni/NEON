package com.engine;

import com.library.interfaces.IGameData;
import com.library.interfaces.IObserver;
import com.library.interfaces.IPlaceable;
import com.library.interfaces.IStatusText;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class GameData implements IGameData {

    private final List<IPlaceable> placeables = new ArrayList<>();
    private final List<IObserver> observers = new ArrayList<>();
    private final List<IStatusText> statusTexts = new ArrayList<>();

    @Override
    public void addObserver(IObserver observer) {
        observers.add(observer);
    }

    @Override
    public List<IPlaceable> getPlaceables() {
        return placeables;
    }

    @Override
    public void addStatusText(IStatusText text) {
        statusTexts.add(text);
        observers.forEach(IObserver::update);
    }

    @Override
    public void removeStatusText(IStatusText text) {
        statusTexts.remove(text);
        observers.forEach(IObserver::update);
    }

    @Override
    public List<IStatusText> getStatusText() {
        return statusTexts;
    }

    @Override
    public void addPlaceables(Collection<IPlaceable> placables) {
        placeables.addAll(placables);
        observers.forEach(IObserver::update);
    }

    @Override
    public void removePlaceables(Collection<IPlaceable> placables) {
        placeables.removeAll(placables);
        observers.forEach(IObserver::update);
    }
}

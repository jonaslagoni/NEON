package com.engine;

import com.library.interfaces.IGameData;
import com.library.interfaces.IPlaceable;
import com.library.interfaces.ViewObserver;
import java.util.*;

public class GameData implements IGameData {

    private final List<IPlaceable> placeables = new ArrayList<>();
    private final List<ViewObserver> observers = new ArrayList<>();

    @Override
    public void addObserver(ViewObserver observer) {
        observers.add(observer);
    }

    @Override
    public List<IPlaceable> getPlaceables() {
        return placeables;
    }

    @Override
    public void addPlaceables(Collection<IPlaceable> placables) {
        placeables.addAll(placables);
        observers.forEach(t -> t.updateView());
    }

    @Override
    public void removePlaceables(Collection<IPlaceable> placables) {
        placeables.removeAll(placables);
        observers.forEach(t -> t.updateView());
    }
}

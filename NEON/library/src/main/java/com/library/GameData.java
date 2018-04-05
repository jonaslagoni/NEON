package com.library;

import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.viewport.Viewport;

import java.util.*;

public class GameData {

    /**
     * A multiplexer allows multiple input processors More information on
     * inputMultiplexer: https://github.com/libgdx/libgdx/wiki/Event-handling
     */
    private final InputMultiplexer multiplexer = new InputMultiplexer();
    private final List<TowerType> placeables = new ArrayList<>();
    private Skin skin;
    private Viewport viewport;

    public GameData(Skin skin, Viewport viewport) {
        this.skin = skin;
        this.viewport = viewport;
    }

    public InputMultiplexer getMultiplexer() {
        return multiplexer;
    }

    public void addInputProcessor(InputProcessor inputProcessor) {
        multiplexer.addProcessor(inputProcessor);
    }

    public void removeInputProcessor(InputProcessor inputProcessor) {
        multiplexer.removeProcessor(inputProcessor);
    }

    public void addPlaceable(TowerType title) {
        placeables.add(title);
    }

    public List<TowerType> getPlaceables() {
        return placeables;
    }

    public Skin getSkin() {
        return skin;
    }

    public Viewport getViewport() {
        return viewport;
    }

}

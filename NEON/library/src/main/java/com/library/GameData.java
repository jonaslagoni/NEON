package com.library;

import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.library.interfaces.ITargetingService;
import com.neon.engine.Neon;
import com.targeting.TargetingService;

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
    private Neon game;

    public GameData(Skin skin, Viewport viewport, Neon game) {
        this.skin = skin;
        this.viewport = viewport;
        this.game = game;
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

    public void endGame() {
        game.endGame();
    }

    public void addService(Class<ITargetingService> aClass, TargetingService targetingService) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}

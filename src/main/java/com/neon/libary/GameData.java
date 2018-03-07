package com.neon.libary;

import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.neon.libary.interfaces.Controller;
import com.neon.libary.interfaces.Factory;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class GameData {

    /**
     * A multiplexer allows multiple input processors
     * More information on inputMultiplexer: https://github.com/libgdx/libgdx/wiki/Event-handling
     */
    private final InputMultiplexer multiplexer = new InputMultiplexer();
    private final List<Controller> controllers = new LinkedList<>();
    private final Map<String, Factory> placeables = new HashMap<>();
    private Skin skin;
    private Viewport viewport;

    @SuppressWarnings("WeakerAccess")
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

    public void addController(Controller controller) {
        controllers.add(controller);
    }

    public void removeController(Controller controller) {
        controllers.remove(controller);
    }

    public List<Controller> getControllers() {
        return controllers;
    }

    public void addPlaceable(String title, Factory drawable) {
        placeables.put(title, drawable);
    }

    public Map<String, Factory> getPlaceables() {
        return placeables;
    }

    public Skin getSkin() {
        return skin;
    }

    public Viewport getViewport() {
        return viewport;
    }
}

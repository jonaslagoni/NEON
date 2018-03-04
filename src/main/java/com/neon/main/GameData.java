package com.neon.main;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.neon.main.entities.Drawable;

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

    private Map<String, Drawable> placeables = new HashMap<>();

    @SuppressWarnings("WeakerAccess")
    public GameData() {
        Gdx.input.setInputProcessor(multiplexer);
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

    public void addPlaceable(String title, Drawable drawable) {
        placeables.put(title, drawable);
    }

    public Map<String, Drawable> getPlaceables() {
        return placeables;
    }
}

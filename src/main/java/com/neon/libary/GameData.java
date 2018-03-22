package com.neon.libary;

import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.neon.libary.interfaces.Controller;
import com.neon.libary.interfaces.Factory;
import com.neon.libary.interfaces.Service;
import java.util.ArrayList;

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
    private final List<TowerType> placeables = new ArrayList<>();
    /**
     * TODO Eventually replace with a premade service system.
     */
    private final Map<Class<?>, Service> services = new HashMap<>();
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

    public void addController(Controller controller) {
        controllers.add(controller);
    }

    public void removeController(Controller controller) {
        controllers.remove(controller);
    }

    public List<Controller> getControllers() {
        return controllers;
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

    public <T extends Service> void addService(Class<T> iface, Service service) {
        services.put(iface, service);
    }

    @SuppressWarnings("unchecked")
    public <E extends Service> E getService(Class<E> iface) {
        return (E) services.get(iface);
    }
}

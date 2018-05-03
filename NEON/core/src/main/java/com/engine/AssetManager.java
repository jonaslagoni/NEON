/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.engine;

import com.library.interfaces.IAssetManager;
import com.library.interfaces.IObserver;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author emil
 */
public class AssetManager implements IAssetManager {

    private final static Logger LOGGER = Logger.getLogger(AssetManager.class.getName());
    private final Map<String, byte[]> assets = new ConcurrentHashMap<>();
    private final List<IObserver> observers = new ArrayList<>();

    @Override
    public void addObserver(IObserver observer) {
        observers.add(observer);
    }

    @Override
    public void unloadAsset(String name) {
        assets.remove(name);
        observers.forEach(IObserver::update);
    }

    @Override
    public byte[] getTexture(String name) {
        return assets.get(name);
    }

    @Override
    public Map<String, byte[]> getTextures() {
        return assets;
    }

    @Override
    public void loadAsset(String name, InputStream stream) {
        try (DataInputStream dataStream = new DataInputStream(stream)) {
            byte[] bytes = new byte[dataStream.available()];
            dataStream.readFully(bytes);
            assets.put(name, bytes);
            observers.forEach(IObserver::update);
        } catch (IOException ex) {
            LOGGER.log(Level.SEVERE, ex.toString(), ex);
        }
    }
}

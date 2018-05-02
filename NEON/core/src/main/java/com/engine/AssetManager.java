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

/**
 * @author emil
 */
public class AssetManager implements IAssetManager {

    private final Map<String, byte[]> assets = new ConcurrentHashMap<>();
    private List<IObserver> observers = new ArrayList<>();

    public void addObserver(IObserver observer) {
        observers.add(observer);
    }

    @Override
    public void unloadAsset(String name) {
        assets.remove(name);
        observers.forEach(IObserver::update);
    }

    public byte[] getTexture(String name) {
        return assets.get(name);
    }

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
            ex.printStackTrace();
        }
    }
}

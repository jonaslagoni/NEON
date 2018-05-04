/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.library.interfaces;

import java.io.InputStream;
import java.util.Map;

/**
 * @author emil
 */
public interface IAssetManager {

    /**
     * Load asset by name from the asset folder containing pictures
     * of each asset
     * @param name of the asset
     * @param stream datainputstream
     */
    void loadAsset(String name, InputStream stream);

    /**
     * Unload asset when the games stops
     * @param name of the asset
     */
    void unloadAsset(String name);

    Map<String, byte[]> getTextures();

    void addObserver(IObserver observer);

}

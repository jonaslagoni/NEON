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
     * Load asset from the inputstream and save it with the name
     * @param name of the asset
     * @param stream inputstream
     */
    public void loadAsset(String name, InputStream stream);

    /**
     * Unload asset when the game stops
     * @param name of the asset
     */
    public void unloadAsset(String name);

    /**
     * Returns a byte array containing bit representation of texture
     * @param name
     * @return byte array 
     */
    byte[] getTexture(String name);

    /**
     * Returns the hashmap containing all textures
     * @return map
     */
    Map<String, byte[]> getTextures();

    /**
     * Add observer
     * @param observer 
     */
    void addObserver(IObserver observer);

}

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

    void loadAsset(String name, InputStream stream);

    void unloadAsset(String name);

    byte[] getTexture(String name);

    Map<String, byte[]> getTextures();

    void addObserver(IObserver observer);

}

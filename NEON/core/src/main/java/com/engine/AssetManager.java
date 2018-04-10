/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.engine;

import com.badlogic.gdx.files.FileHandle;
import com.library.interfaces.IAssetManager;
import java.util.HashMap;
import com.badlogic.gdx.graphics.Texture;
import java.io.File;
import java.util.Map;

/**
 *
 * @author emil
 */
public class AssetManager implements IAssetManager {

    private static final Map<String, Texture> ASSETS = new HashMap<>();

    @Override
    public void unloadAsset(String name) {
        ASSETS.remove(name);
    }

    public Texture getTexture(String name) {
        return ASSETS.get(name);
    }

    @Override
    public void loadAsset(String name, File file) {
        ASSETS.put(name, new Texture(new FileHandle(file)));
    }
}

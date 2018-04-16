/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.engine;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Pixmap;
import com.library.interfaces.IAssetManager;
import java.util.HashMap;
import com.badlogic.gdx.graphics.Texture;
import java.io.DataInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author emil
 */
public class AssetManager implements IAssetManager {

    private final Map<String, Texture> assets = new HashMap<>();

    @Override
    public void unloadAsset(String name) {
        assets.remove(name);
    }

    public Texture getTexture(String name) {
        return assets.get(name);
    }

    @Override
    public void loadAsset(String name, InputStream stream) {
        try (DataInputStream dataStream = new DataInputStream(stream)) {
            byte[] bytes = new byte[dataStream.available()];
            dataStream.readFully(bytes);
            Gdx.app.postRunnable(() -> {
                Pixmap pixmap = new Pixmap(bytes, 0, bytes.length);
                Texture texture = new Texture(pixmap);
                assets.put(name, texture);
            });
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}

package com.engine;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class AssetManagerTest {

    private AssetManager assetManager;

    @BeforeEach
    void setUp() {
        assetManager = new AssetManager();
        try (InputStream stream = getClass().getClassLoader().getResourceAsStream("red.png")) {
            assetManager.loadAsset("red", stream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    void unloadAsset() {
        assetManager.unloadAsset("red");
        Map<String, byte[]> textures = assetManager.getTextures();
        assertTrue(textures.isEmpty());
    }

    @Test
    void getTextures() {
        Map<String, byte[]> textures = assetManager.getTextures();
        assertEquals(4096, textures.get("red").length);
    }

    @Test
    void loadAsset() {
        try (InputStream stream = getClass().getClassLoader().getResourceAsStream("blue.png")) {
            assetManager.loadAsset("blue", stream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Map<String, byte[]> textures = assetManager.getTextures();
        assertEquals(4096, textures.get("blue").length);
    }
}
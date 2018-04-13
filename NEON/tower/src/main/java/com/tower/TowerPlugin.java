package com.tower;

import com.library.GameData;
import com.library.TowerType;
import com.library.World;
import com.library.interfaces.IAssetManager;
import com.library.interfaces.Plugin;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TowerPlugin implements Plugin {

    private static final String ASSET_FOLDER = "assets/";
    private static final String FILE_TYPE = ".png";
    private static final String[] ASSETS = {
        "pea-towe1", "pea-towe2",
        "pea-towe3", "pea-towe4",
        "rail-tower1", "rail-tower2",
        "rail-tower3", "rail-tower4",
        "laser-tower1", "laser-tower2",
        "laser-tower3", "laser-tower4",
        "melee-tower1", "melee-tower2",
        "melee-tower3", "melee-tower4",
        "range-tower1", "range-tower2",
        "rocket-tower1", "rocket-tower2",
        "rocket-tower3", "rocket-tower4",
        "splash-tower1", "splash-tower2",
        "splash-tower3", "splash-tower4",
        "strength-tower1", "strength-tower2"
    };

    private World world;
    private GameData gameData;
    private IAssetManager assetManager;

    public void setWorld(World world) {
        this.world = world;
    }

    public void removeWorld() {
        this.world = null;
    }

    @Override
    public void start() {
        for (String name : ASSETS) {
            String pathName = ASSET_FOLDER + name + FILE_TYPE;
            try (InputStream stream = getClass().getClassLoader().getResourceAsStream(pathName)) {
                byte[] bytes = new byte[stream.available()];
                stream.read(bytes);
                assetManager.loadAsset(name, bytes);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        for (TowerType t : TowerType.values()) {
            gameData.addPlaceable(t);
        }
    }

    @Override
    public void stop() {
        Arrays.stream(ASSETS).forEach(assetManager::unloadAsset);
        world.getEntities(Tower.class).forEach(world::removeEntity);
    }

    public void setAssetManager(IAssetManager assetManager) {
        this.assetManager = assetManager;
    }

    public void removeAssetManager() {
        this.assetManager = null;
    }
}

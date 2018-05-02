package com.tower;

import com.library.interfaces.*;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

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
    private static final Logger LOGGER = Logger.getLogger(TowerPlugin.class.getName());

    private IWorldService world;
    private IGameData gameData;
    private IAssetManager assetManager;
    private ILocalTowerService towerService;
    private Collection<IPlaceable> placeables;

    @Override
    public void start() {
        for (String name : ASSETS) {
            String pathName = ASSET_FOLDER + name + FILE_TYPE;
            try (InputStream stream = getClass().getClassLoader().getResourceAsStream(pathName)) {
                assetManager.loadAsset(name, stream);
            } catch (IOException ex) {
                LOGGER.log(Level.SEVERE, ex.toString(), ex);
            }
        }

        placeables = Arrays.stream(TowerType.values())
                .map(t -> new TowerPlaceable(t, towerService, t.toString()))
                .collect(Collectors.toList());

        gameData.addPlaceables(placeables);
    }

    @Override
    public void stop() {
        Arrays.stream(ASSETS).forEach(assetManager::unloadAsset);
        world.getEntities(Tower.class).forEach(world::removeEntity);
        gameData.removePlaceables(placeables);
    }

    public void setAssetManager(IAssetManager assetManager) {
        this.assetManager = assetManager;
    }

    public void removeAssetManager(IAssetManager assetManager) {
        this.assetManager = null;
    }

    public void setGameData(IGameData gameData) {
        this.gameData = gameData;
    }

    public void removeGameData(IGameData gameData) {
        this.gameData = null;
    }

    public void setWorld(IWorldService world) {
        this.world = world;
    }

    public void removeWorld(IWorldService world) {
        this.world = null;
    }

    public void setTowerService(ILocalTowerService towerService) {
        this.towerService = towerService;
    }

    public void removeTowerService(ILocalTowerService towerService) {
        this.towerService = null;
    }

}

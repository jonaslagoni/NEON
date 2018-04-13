package com.enemy;

import com.library.World;
import com.library.interfaces.IAssetManager;
import com.library.interfaces.Plugin;

import com.library.interfaces.IWorldService;
import java.io.File;
import java.util.Arrays;

public class EnemyPlugin implements Plugin {

    private IWorldService world;
    private IAssetManager assetManager;

    private static final String[] ASSETS = {
        "blue", "red", "green",
        "circle1", "circle2", "circle3",
        "circle4", "circle5", "circle6",
        "cross1", "cross2", "cross3",
        "cross4", "cross5", "cross6",
        "fidget1", "fidget2", "fidget3",
        "fidget4", "fidget5", "fidget6",
        "hexagon1", "hexagon2", "hexagon3",
        "hexagon4", "hexagon5", "hexagon6",
        "pentagon1", "pentagon2", "pentagon3",
        "pentagon4", "pentagon5", "pentagon6",
        "square1", "square2", "square3",
        "square4", "square5", "square6",
        "star1", "star2", "star3",
        "star4", "star5", "star6",
        "triangle1", "triangle2", "triangle3",
        "triangle4", "triangle5", "triangle6"
    };

    public void setWorld(World world) {
        this.world = world;
    }

    public void removeWorld() {
        this.world = null;
    }

    public void setAssetManager(IAssetManager assetManager) {
        this.assetManager = assetManager;
    }
    
    public void removeAssetManager() {
        this.assetManager = null;
    }

    @Override
    public void start() {

        /* Load assets */
        for (String name : ASSETS) {
            String pathName = "assets/" + name + ".png";
            String path = getClass().getClassLoader()
                    .getResource(pathName).getFile();
            assetManager.loadAsset(name, new File(path));
        }
    }

    @Override
    public void stop() {
        Arrays.stream(ASSETS).forEach(assetManager::unloadAsset);
        world.getEntities(Enemy.class).forEach(world::removeEntity);
    }
}

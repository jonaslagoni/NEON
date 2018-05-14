package com.map;

import com.library.Sprite;
import com.library.World;
import com.library.interfaces.IAssetManager;
import com.library.interfaces.IWorldService;
import com.library.interfaces.Plugin;
import com.library.vectors.Vector2f;

import java.io.IOException;
import java.io.InputStream;
import java.util.Random;

public class MapPlugin implements Plugin {

    private static final int NUM_OBSTACLES = 10;
    private static final int PADDING = 128;
    private static final String[] TEXTURES = {
            "rock1", "rock2", "rock3",
            "rock4", "rock5", "rock6"
    };
    private final Random random = new Random();
    private IWorldService world;
    private IAssetManager assetManager;

    public void setAssetManager(IAssetManager assetManager) {
        this.assetManager = assetManager;
    }

    public void removeAssetManager(IAssetManager assetManager) {
        this.assetManager = assetManager;
    }

    public void setWorld(IWorldService world) {
        this.world = world;
    }

    public void removeWorld(IWorldService world) {
        this.world = null;
    }

    @Override
    public void start() {

        for (String name : TEXTURES) {
            try (InputStream stream = getClass()
                    .getClassLoader()
                    .getResourceAsStream(name + ".png")) {
                assetManager.loadAsset(name, stream);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

        for (int i = 0; i < NUM_OBSTACLES; i++) {
            world.setGridCell(
                    new Vector2f(
                            (float) random.nextInt(World.WIDTH - 2 * PADDING) + PADDING,
                            (float) random.nextInt(World.HEIGHT - 2 * PADDING) + PADDING
                    ),
                    new Obstacle(new Sprite(
                            TEXTURES[random.nextInt(TEXTURES.length)],
                            new Vector2f(0, 0),
                            new Vector2f(random.nextInt(2), random.nextInt(2)),
                            World.GRID_CELL_SIZE,
                            World.GRID_CELL_SIZE
                    ))
            );
        }
    }

    @Override
    public void stop() {
        world.getEntities(Obstacle.class).forEach(world::removeEntity);
    }
}

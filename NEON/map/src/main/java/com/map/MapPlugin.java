package com.map;

import com.library.Sprite;
import com.library.World;
import com.library.interfaces.IWorldService;
import com.library.interfaces.Plugin;
import com.library.vectors.Vector2f;

import java.util.Random;

public class MapPlugin implements Plugin {

    private static final int NUM_OBSTACLES = 10;
    private static final int PADDING = 128;

    private IWorldService world;

    public void setWorld(IWorldService world) {
        this.world = world;
    }
    public void removeWorld() {
        this.world = null;
    }

    @Override
    public void start() {
        Random random = new Random();
        String[] textures = {
            "images/Map/rocks/blue1.png",
            "images/Map/rocks/blue2.png",
            "images/Map/rocks/blue3.png",
            "images/Map/rocks/blue4.png",
            "images/Map/rocks/blue5.png",
            "images/Map/rocks/blue6.png"
        };
        for (int i = 0; i < NUM_OBSTACLES; i++) {
            world.setGridCell(
                    new Vector2f((float) (random.nextInt(World.WIDTH - 2 * PADDING) + PADDING),
                            (float) random.nextInt(World.HEIGHT - 2 * PADDING) + PADDING),
                     new Obstacle(new Sprite(
                            textures[random.nextInt(textures.length)],
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

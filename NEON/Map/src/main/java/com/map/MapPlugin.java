package com.map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.neon.libary.GameData;
import com.neon.libary.Sprite;
import com.neon.libary.World;
import com.neon.libary.interfaces.Plugin;
import com.neon.libary.vectors.Vector2f;

import java.util.Random;

public class MapPlugin implements Plugin {

    private static final int NUM_OBSTACLES = 10;

    private final World world;
    @SuppressWarnings("FieldCanBeLocal")
    private final GameData gameData;

    public MapPlugin(World world, GameData gameData) {
        this.world = world;
        this.gameData = gameData;
    }

    @Override
    public void start() {
        Random random = new Random();
        Texture[] textures = {new Texture(Gdx.files.internal("images/Map/rocks/blue1.png")),
                new Texture(Gdx.files.internal("images/Map/rocks/blue2.png")),
                new Texture(Gdx.files.internal("images/Map/rocks/blue3.png")),
                new Texture(Gdx.files.internal("images/Map/rocks/blue4.png")),
                new Texture(Gdx.files.internal("images/Map/rocks/blue5.png")),
                new Texture(Gdx.files.internal("images/Map/rocks/blue6.png"))};
        for (int i = 0; i < NUM_OBSTACLES; i++) {
            world.setGridCell(
                    new Vector2f((float) (random.nextInt(World.WIDTH - 256) + 128), (float) random.nextInt(World.HEIGHT - 256) + 128),
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

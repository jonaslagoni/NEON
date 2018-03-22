package map;

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
        for (int i = 0; i < NUM_OBSTACLES; i++) {
            world.setGridCell(
                    new Vector2f((float) random.nextInt(World.WIDTH), (float) random.nextInt(World.HEIGHT)),
                    new Obstacle(new Sprite(
                            new Texture(Gdx.files.internal("images/down-button.png")),
                            new Vector2f(0, 0),
                            new Vector2f(0, 0),
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

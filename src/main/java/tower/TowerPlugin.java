package tower;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.neon.main.GameData;
import com.neon.main.Plugin;
import com.neon.main.World;
import com.neon.main.entities.Sprite;

public class TowerPlugin implements Plugin {

    private static Tower createLaserTower() {
        Sprite sprite = new Sprite(new Texture(Gdx.files.internal("images/laser-tower.png")),
                World.GRID_CELL_SIZE, World.GRID_CELL_SIZE);
        return new Tower(sprite);
    }

    @Override
    public void start(GameData gameData, World world) {
        gameData.addPlaceable("laser-tower", createLaserTower());
    }

    @Override
    public void stop(GameData gameData, World world) {
        world.getEntities(Tower.class).forEach(world::removeEntity);
    }
}

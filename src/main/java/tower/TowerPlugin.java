package tower;

import com.neon.main.GameData;
import com.neon.main.Plugin;
import com.neon.main.World;

public class TowerPlugin implements Plugin {


    @Override
    public void start(GameData gameData, World world) {
        TowerFactory factory = new TowerFactory();
        gameData.addPlaceable("laser-tower", factory);
    }

    @Override
    public void stop(GameData gameData, World world) {
        world.getEntities(Tower.class).forEach(world::removeEntity);
    }
}

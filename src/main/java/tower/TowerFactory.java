package tower;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.neon.main.Factory;
import com.neon.main.World;
import com.neon.main.entities.Drawable;
import com.neon.main.entities.Sprite;

public class TowerFactory implements Factory {

    @Override
    public Drawable create(String key) {
        switch (key) {
            case "laser-tower":
                Sprite sprite = new Sprite(new Texture(Gdx.files.internal("images/laser-tower.png")),
                        World.GRID_CELL_SIZE, World.GRID_CELL_SIZE);
                Texture[] upgrades = new Texture[3];
                upgrades[0] = new Texture(Gdx.files.internal("images/towers/Laser Tower (Constant focused single target)/Laser Tower lvl 2.png"));
                upgrades[1] = new Texture(Gdx.files.internal("images/towers/Laser Tower (Constant focused single target)/Laser Tower lvl 3.png"));
                upgrades[2] = new Texture(Gdx.files.internal("images/towers/Laser Tower (Constant focused single target)/Laser Tower lvl 4.png"));
                return new Tower(sprite, upgrades);
        }
        return null;
    }
}

package com.tower;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.library.Sprite;
import com.library.TowerType;
import com.library.World;
import com.library.vectors.Vector2f;
import static com.library.TowerType.*;

import java.util.Arrays;

class TowerFactory {

    private TowerFactory() {
    }

    public static Tower build(TowerType key) {
        switch (key) {
            case LASER_TOWER:
                return build(key,
                        "images/towers/Laser Tower (Constant focused single target)/Laser Tower lvl 1.png",
                        "images/towers/Laser Tower (Constant focused single target)/Laser Tower lvl 2.png",
                        "images/towers/Laser Tower (Constant focused single target)/Laser Tower lvl 3.png",
                        "images/towers/Laser Tower (Constant focused single target)/Laser Tower lvl 4.png"
                );
            case MELEE_GLAIVE_TOWER:
                return build(key,
                        "images/towers/Melee Glaive Tower/Melee Glaive Tower lvl 1.1.png",
                        "images/towers/Melee Glaive Tower/Melee Glaive Tower lvl 2.1.png",
                        "images/towers/Melee Glaive Tower/Melee Glaive Tower lvl 3.1.png",
                        "images/towers/Melee Glaive Tower/Melee Glaive Tower lvl 4.1.png"
                );
            case PEA_SHOOTER:
                return build(key,
                        "images/towers/Pea shooter (Single target)/Blue Pea Shooter lvl 1.png",
                        "images/towers/Pea shooter (Single target)/Blue Pea Shooter lvl 2.png",
                        "images/towers/Pea shooter (Single target)/Blue Pea Shooter lvl 3.png",
                        "images/towers/Pea shooter (Single target)/Blue Pea Shooter lvl 4.png"
                );
            case RANGE_POWERUP:
                return build(key,
                        "images/towers/Powerup/Range Powerup v1.png",
                        "images/towers/Powerup/Range Powerup v2.png"
                );
            case STRENGTH_POWERUP:
                return build(key,
                        "images/towers/Powerup/Strength Powerup non-glowing.png",
                        "images/towers/Powerup/Strength Powerup.png"
                );
            case RAILGUN_TOWER:
                return build(key,
                        "images/towers/Railgun Tower (Long distance sniper)/Railgun Tower lvl 1.png",
                        "images/towers/Railgun Tower (Long distance sniper)/Railgun Tower lvl 2.png",
                        "images/towers/Railgun Tower (Long distance sniper)/Railgun Tower lvl 3.png",
                        "images/towers/Railgun Tower (Long distance sniper)/Railgun Tower lvl 4.png"
                );
            case ROCKET_TOWER:
                return build(key,
                        "images/towers/Rocket Tower (Curve & AOE)/Orange Rocket Tower lvl 1.png",
                        "images/towers/Rocket Tower (Curve & AOE)/Orange Rocket Tower lvl 2.png",
                        "images/towers/Rocket Tower (Curve & AOE)/Orange Rocket Tower lvl 3.png",
                        "images/towers/Rocket Tower (Curve & AOE)/Orange Rocket Tower lvl 4.png"
                );
            case SPLASH_TOWER:
                return build(key,
                        "images/towers/Splash Tower (AOE)/Red Splash Tower lvl 1.png",
                        "images/towers/Splash Tower (AOE)/Red Splash Tower lvl 2.png",
                        "images/towers/Splash Tower (AOE)/Red Splash Tower lvl 3.png",
                        "images/towers/Splash Tower (AOE)/Red Splash Tower lvl 4.png"
                );
        }
        return null;
    }

    private static Tower build(TowerType key, String... files) {
        Texture[] textures = Arrays.stream(files)
                .map(Gdx.files::internal)
                .map(Texture::new)
                .toArray(Texture[]::new);
        Vector2f position = new Vector2f(0, 0);
        return new Tower(new Sprite(
                textures[0],
                position,
                new Vector2f(0, 0),
                World.GRID_CELL_SIZE,
                World.GRID_CELL_SIZE
        ), textures, 10, 4, key);
    }
}

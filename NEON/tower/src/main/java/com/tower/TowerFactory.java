package com.tower;

import com.library.Sprite;
import com.library.interfaces.IWorldService;
import com.library.vectors.Vector2f;

class TowerFactory {

    private TowerFactory() {
    }

    static Tower build(TowerType key) {
        switch (key) {
            case LASER_TOWER:
                return build(key,
                        "laser-tower1", "laser-tower2",
                        "laser-tower3", "laser-tower4"
                );
            case MELEE_GLAIVE_TOWER:
                return build(key, "melee-tower1", "melee-tower2", "melee-tower3", "melee-tower4");
            case PEA_SHOOTER:
                return build(key,
                        "pea-towe1", "pea-towe2",
                        "pea-towe3", "pea-towe4"
                );
            case RANGE_POWERUP:
                return build(key,
                        "range-tower1", "range-tower2"
                );
            case STRENGTH_POWERUP:
                return build(key,
                        "strength-tower1", "strength-tower2"
                );
            case RAILGUN_TOWER:
                return build(key,
                        "rail-tower1", "rail-tower2",
                        "rail-tower3", "rail-tower4"
                );
            case ROCKET_TOWER:
                return build(key,
                        "rocket-tower1", "rocket-tower2",
                        "rocket-tower3", "rocket-tower4"
                );
            case SPLASH_TOWER:
                return build(key,
                        "splash-tower1", "splash-tower2",
                        "splash-tower3", "splash-tower4"
                );
        }
        return null;
    }

    private static Tower build(TowerType key, String... files) {
        Vector2f position = new Vector2f(0, 0);
        return new Tower(new Sprite(
                files[0],
                position,
                new Vector2f(0, 0),
                IWorldService.GRID_CELL_SIZE,
                IWorldService.GRID_CELL_SIZE
        ), files, 10, 4, key);
    }
}

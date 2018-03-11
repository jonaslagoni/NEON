package com.neon.tower;

import com.neon.libary.GameData;
import com.neon.libary.World;
import com.neon.libary.interfaces.Controller;
import com.neon.weapon.Weapon;

public class TowerController implements Controller {

    private World world;

    TowerController(World world, GameData gameData) {
        this.world = world;
    }

    @Override
    public void update() {
        world.getEntities(Tower.class).forEach(this::updateTower);
    }

    private void updateTower(Tower tower) {

        if (!world.getEntities(Weapon.class).contains(tower.getWeapon())) {
            world.addEntity(tower.getWeapon());
        }

    }
}

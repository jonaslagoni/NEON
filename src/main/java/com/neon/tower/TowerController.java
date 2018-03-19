package com.neon.tower;

import com.neon.libary.World;
import com.neon.libary.interfaces.Controller;

class TowerController implements Controller {

    private final World world;

    TowerController(World world) {
        this.world = world;
    }

    @Override
    public void update() {
        world.getEntities(Tower.class).forEach(this::updateTower);
    }

    private void updateTower(Tower tower) {
        tower.sprite.setTexture(tower.textures[tower.level]);
    }
}

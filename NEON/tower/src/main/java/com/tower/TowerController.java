package com.tower;

import com.library.World;
import com.library.interfaces.Controller;

class TowerController implements Controller {

    private final World world;

    TowerController(World world) {
        this.world = world;
    }

    @Override
    public void update(float dt) {
        world.getEntities(Tower.class).forEach(tower -> {
            tower.sprite.setTexture(tower.textures[tower.level - 1]);
        });
    }
}

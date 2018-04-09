package com.tower;

import com.library.World;
import com.library.interfaces.Controller;

class TowerController implements Controller {

    private World world;

    public void setWorld(World world){
        this.world = world;
    }
    
    public void removeWorld(){
        this.world = null;
    }

    @Override
    public void update(float dt) {
        world.getEntities(Tower.class).forEach(tower -> {
            tower.sprite.setTexture(tower.textures[tower.level - 1]);
        });
    }
}

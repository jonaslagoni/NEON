package com.tower;

import com.library.interfaces.Controller;
import com.library.interfaces.IWorldService;

public class TowerController implements Controller {

    private IWorldService world;

    public TowerController(){
        
    }
    public void setWorld(IWorldService world){
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

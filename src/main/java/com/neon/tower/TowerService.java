package com.neon.tower;

import com.neon.libary.interfaces.Entity;
import com.neon.libary.interfaces.ITowerService;

public class TowerService implements ITowerService {

    @Override
    public void upgrade(Entity entity) {
        if (!(entity instanceof Tower)) return;
        Tower tower = (Tower) entity;
        if (tower.level < 2) {
            tower.level++;
            tower.sprite.setTexture(tower.texture[tower.level]);
        }
    }

}

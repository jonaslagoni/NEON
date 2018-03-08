package com.neon.tower;

import com.neon.libary.interfaces.Entity;
import com.neon.libary.interfaces.ITowerService;

public class TowerService implements ITowerService {

    @Override
    public void upgrade(Entity entity) {
        if (!(entity instanceof Tower)) return;
        Tower tower = (Tower) entity;
        if (tower.getLevel() < 2) {
            tower.setLevel(tower.getLevel() + 1);
            tower.getSprite().setTexture(tower.getTexture()[tower.getLevel()]);
        }
    }
}

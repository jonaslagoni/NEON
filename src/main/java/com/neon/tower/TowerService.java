package com.neon.tower;

import com.neon.libary.interfaces.Service;

public class TowerService implements Service {
    public void upgradeTower(Tower tower) {
        if (tower.getLevel() < 2) {
            tower.setLevel(tower.getLevel() + 1);
            tower.getSprite().setTexture(tower.getTexture()[tower.getLevel()]);
        }
    }
}

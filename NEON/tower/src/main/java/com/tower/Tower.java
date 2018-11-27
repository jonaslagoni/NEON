package com.tower;

import com.library.Sprite;
import com.library.interfaces.Drawable;
import com.library.interfaces.Entity;
import com.library.interfaces.WeaponEntity;

class Tower implements Drawable, Entity {

    Sprite sprite;
    String[] textures;
    int level = 1;
    private int cost;
    int maxLevel;
    WeaponEntity weapon;
    private TowerType towerType;

    Tower(Sprite sprite,
          String[] textures,
          int cost,
          int maxLevel,
          TowerType towerType) {
        this.sprite = sprite;
        this.textures = textures;
        this.cost = cost;
        this.maxLevel = maxLevel;
        this.towerType = towerType;
    }

    @Override
    public Sprite getSprite() {
        return sprite;
    }

    int getCost() {
        return cost * level;
    }

    int getLevel() {
        return level;
    }

    void increaseLevel() {
        level++;
    }

    TowerType getTowerType() {
        return towerType;
    }

    WeaponEntity getWeapon() {
        return weapon;
    }

    void setWeapon(WeaponEntity weapon) {
        this.weapon = weapon;
    }
}

package com.tower;

import com.library.Sprite;
import com.library.TowerType;
import com.library.interfaces.Drawable;
import com.library.interfaces.WeaponEntity;
import com.library.interfaces.Entity;

class Tower implements Drawable, Entity {

    Sprite sprite;
    String[] textures;
    int level = 1;
    int cost;
    int maxLevel;
    WeaponEntity weapon;
    TowerType towerType;

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

    public int getCost() {
        return cost * level;
    }

    public int getLevel() {
        return level;
    }

    public void increaseLevel() {
        level++;
    }

    public TowerType getTowerType() {
        return towerType;
    }

    public WeaponEntity getWeapon() {
        return weapon;
    }

    public void setWeapon(WeaponEntity weapon) {
        this.weapon = weapon;
    }
}

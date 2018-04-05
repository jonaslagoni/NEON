package com.tower;

import com.badlogic.gdx.graphics.Texture;
import com.library.Sprite;
import com.library.TowerType;
import com.library.interfaces.Drawable;
import com.library.interfaces.WeaponEntity;


@SuppressWarnings("WeakerAccess")
class Tower implements Drawable {

    Sprite sprite;
    Texture[] textures;
    int level = 1;
    @SuppressWarnings("WeakerAccess")
    int cost;
    int maxLevel;
    WeaponEntity weapon;
    TowerType towerType;

    Tower(Sprite sprite, Texture[] textures, int cost, int maxLevel, TowerType towerType) {
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

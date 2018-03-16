package com.neon.tower;

import com.badlogic.gdx.graphics.Texture;
import com.neon.libary.Sprite;
import com.neon.libary.interfaces.Drawable;
import com.neon.libary.interfaces.Entity;
import com.neon.libary.interfaces.ITower;
import com.neon.weapon.Weapon;

class Tower implements Drawable, ITower {

    Sprite sprite;
    int level;
    Texture[] texture;
    Entity weapon;
    int cost;
    int maxLevel;
    Tower(Sprite sprite, Texture[] texture, Weapon weapon, int cost, int maxLevel) {
        this.sprite = sprite;
        this.texture = texture;
        this.weapon = weapon;
        this.cost = cost;
        this.maxLevel = maxLevel;
        this.level = 1;
    }

    @Override
    public Sprite getSprite() {
        return sprite;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public Texture[] getTexture() {
        return texture;
    }

    public Entity getWeapon() {
        return weapon;
    }

    public void setWeapon(Entity weapon) {
        this.weapon = weapon;
    }

    public int getCost() {
        return cost * getLevel();
    }

    @Override
    public int getMaxLevel() {
        return maxLevel;
    }
}

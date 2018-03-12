package com.neon.tower;

import com.badlogic.gdx.graphics.Texture;
import com.neon.libary.Sprite;
import com.neon.libary.interfaces.Drawable;
import com.neon.libary.interfaces.Entity;
import com.neon.weapon.Weapon;

class Tower implements Drawable {

    Sprite sprite;
    int level;
    Texture[] texture;
    Entity weapon;

    Tower(Sprite sprite, Texture[] texture, Weapon weapon) {
        this.sprite = sprite;
        this.texture = texture;
        this.weapon = weapon;
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
}

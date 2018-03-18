package com.neon.tower;

import com.badlogic.gdx.graphics.Texture;
import com.neon.libary.Sprite;
import com.neon.libary.interfaces.Drawable;

class Tower implements Drawable {

    Sprite sprite;
    Texture[] textures;
    int level = 1;
    @SuppressWarnings("WeakerAccess")
    int cost;
    int maxLevel;

    Tower(Sprite sprite, Texture[] textures, int cost, int maxLevel) {
        this.sprite = sprite;
        this.textures = textures;
        this.cost = cost;
        this.maxLevel = maxLevel;
    }

    @Override
    public Sprite getSprite() {
        return sprite;
    }


    public int getCost() {
        return cost * level;
    }
}

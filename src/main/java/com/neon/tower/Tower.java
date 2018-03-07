package com.neon.tower;

import com.badlogic.gdx.graphics.Texture;
import com.neon.libary.Sprite;
import com.neon.libary.interfaces.Drawable;
import com.neon.libary.interfaces.Entity;

public class Tower implements Drawable {

    private Sprite sprite;
    private int level;
    private Texture[] texture;
    private Entity weapon;

    @SuppressWarnings("WeakerAccess")
    public Tower(Sprite sprite, Texture[] texture) {
        this.sprite = sprite;
        this.texture = texture;
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
}

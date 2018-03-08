package com.neon.tower;

import com.badlogic.gdx.graphics.Texture;
import com.neon.libary.Sprite;
import com.neon.libary.interfaces.Drawable;
import com.neon.libary.interfaces.Entity;
import static com.neon.libary.interfaces.Entity.typeIdentifier.TOWER;

public class Tower implements Drawable {

    private Sprite sprite;
    private int level;
    private Texture[] texture;
    private Entity weapon;
    private typeIdentifier type;

    Tower(Sprite sprite, Texture[] texture) {
        type = TOWER;
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

    @Override
    public typeIdentifier getType() {
        return type;
    }



    public Entity getWeapon() {
        return weapon;
    }

    public void setWeapon(Entity weapon) {
        this.weapon = weapon;
    }
}

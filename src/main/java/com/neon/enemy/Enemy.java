package com.neon.enemy;

import com.badlogic.gdx.graphics.Texture;
import com.neon.libary.MoveAbility;
import com.neon.libary.Sprite;
import com.neon.libary.interfaces.Drawable;
import com.neon.libary.interfaces.Moveable;
import com.neon.libary.interfaces.Targetable;

/**
 * Created by sam on 02-03-2018.
 */
class Enemy implements Moveable, Drawable, Targetable {

    MoveAbility moveAbility;
    Sprite sprite;
    Texture[] textures;
    int hp = 200;
    int maxHp = hp;
    float damageTimer;

    Enemy(Sprite sprite, MoveAbility moveAbility, Texture[] textures) {
        this.moveAbility = moveAbility;
        this.sprite = sprite;
        this.textures = textures;
    }

    @Override
    public MoveAbility getMoveAbility() {
        return moveAbility;
    }

    @Override
    public Sprite getSprite() {
        return sprite;
    }

}

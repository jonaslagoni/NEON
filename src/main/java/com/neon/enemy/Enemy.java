package com.neon.enemy;

import com.badlogic.gdx.graphics.Texture;
import com.neon.common.search.Path;
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
    int hp;
    int maxHp;
    float damageTimer;
    Path path;
    int counter;
    Enemy(Sprite sprite, MoveAbility moveAbility, Texture[] textures, int hp) {
        this.moveAbility = moveAbility;
        this.sprite = sprite;
        this.textures = textures;
        this.hp = hp;
        this.maxHp = hp;
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

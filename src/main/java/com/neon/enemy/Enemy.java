package com.neon.enemy;

import com.badlogic.gdx.graphics.Texture;
import com.neon.libary.MoveAbility;
import com.neon.libary.Sprite;
import com.neon.libary.interfaces.Drawable;
import com.neon.libary.interfaces.Moveable;

/**
 * Created by sam on 02-03-2018.
 */
public class Enemy implements Moveable, Drawable {

    MoveAbility moveAbility;
    Sprite sprite;
    Texture[] texture;
    int hp = 200;
    int maxHp = hp;
    float damageTimer;

    Enemy(Sprite sprite, MoveAbility moveAbility, Texture[] texture) {
        this.moveAbility = moveAbility;
        this.sprite = sprite;
        this.texture = texture;
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

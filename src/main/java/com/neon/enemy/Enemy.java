package com.neon.enemy;

import com.neon.libary.MoveAbility;
import com.neon.libary.Sprite;
import com.neon.libary.interfaces.Drawable;
import com.neon.libary.interfaces.Moveable;

/**
 * Created by sam on 02-03-2018.
 */
public class Enemy implements Moveable, Drawable {

    private MoveAbility moveAbility;
    private Sprite sprite;

    Enemy(Sprite sprite, MoveAbility moveAbility) {
        this.moveAbility = moveAbility;
        this.sprite = sprite;
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

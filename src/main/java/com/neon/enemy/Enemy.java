package com.neon.enemy;

import com.neon.main.entities.Drawable;
import com.neon.main.entities.MoveAbility;
import com.neon.main.entities.Moveable;
import com.neon.main.entities.Sprite;

/**
 * Created by sam on 02-03-2018.
 */
public class Enemy implements Moveable, Drawable {

    private MoveAbility moveAbility;
    private Sprite sprite;

    @SuppressWarnings("WeakerAccess")
    public Enemy(Sprite sprite, MoveAbility moveAbility) {
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

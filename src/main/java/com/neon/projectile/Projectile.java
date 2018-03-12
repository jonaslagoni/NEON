package com.neon.projectile;

import com.neon.libary.MoveAbility;
import com.neon.libary.Sprite;
import com.neon.libary.interfaces.Drawable;
import com.neon.libary.interfaces.Moveable;

public class Projectile implements Moveable, Drawable {

    Sprite sprite;
    MoveAbility moveAbility;

    public Projectile(Sprite sprite, MoveAbility moveAbility) {
        this.sprite = sprite;
        this.moveAbility = moveAbility;
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
package com.player;

import com.neon.libary.MoveAbility;
import com.neon.libary.Sprite;
import com.neon.libary.interfaces.Drawable;
import com.neon.libary.interfaces.Moveable;

public class Player implements Moveable, Drawable {

    MoveAbility moveAbility;
    Sprite sprite;

    Player(Sprite sprite, MoveAbility moveAbility) {
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

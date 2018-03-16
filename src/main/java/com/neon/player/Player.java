package com.neon.player;

import com.neon.libary.MoveAbility;
import com.neon.libary.Sprite;
import com.neon.libary.interfaces.Drawable;
import com.neon.libary.interfaces.Moveable;

public class Player implements Moveable, Drawable {

    MoveAbility moveAbility;
    Sprite sprite;

    Player(MoveAbility moveAbility, Sprite sprite) {
        this.moveAbility = moveAbility;
        this.moveAbility.setMove(true);
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

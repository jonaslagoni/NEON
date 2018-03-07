package com.neon.player;

import com.neon.libary.MoveAbility;
import com.neon.libary.Sprite;
import com.neon.libary.interfaces.Drawable;
import com.neon.libary.interfaces.Moveable;

public class Player implements Moveable, Drawable {

    private MoveAbility moveAbility;
    private Sprite sprite;

    @SuppressWarnings("WeakerAccess")
    public Player(MoveAbility moveAbility, Sprite sprite) {
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

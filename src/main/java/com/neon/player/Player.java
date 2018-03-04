package com.neon.player;

import com.neon.main.entities.Drawable;
import com.neon.main.entities.MoveAbility;
import com.neon.main.entities.Moveable;
import com.neon.main.entities.Sprite;

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

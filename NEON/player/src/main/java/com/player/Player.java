package com.player;

import com.library.MoveAbility;
import com.library.Sprite;
import com.library.interfaces.Drawable;
import com.library.interfaces.Moveable;
import com.library.interfaces.Targetable;

public class Player implements Moveable, Drawable {

    MoveAbility moveAbility;
    Sprite sprite;

    public Player(Sprite sprite, MoveAbility moveAbility) {
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

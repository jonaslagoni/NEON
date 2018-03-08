package com.neon.player;

import com.neon.libary.MoveAbility;
import com.neon.libary.Sprite;
import com.neon.libary.interfaces.Drawable;
import com.neon.libary.interfaces.Moveable;

import static com.neon.libary.interfaces.Entity.typeIdentifier.PLAYER;

public class Player implements Moveable, Drawable {

    private MoveAbility moveAbility;
    private Sprite sprite;
    private typeIdentifier type;

    Player(MoveAbility moveAbility, Sprite sprite) {
        type = PLAYER;
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

    @Override
    public typeIdentifier getType() {
        return type;
    }
}

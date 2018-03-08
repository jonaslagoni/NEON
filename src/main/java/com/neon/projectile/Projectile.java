package com.neon.projectile;

import com.neon.libary.MoveAbility;
import com.neon.libary.Sprite;
import com.neon.libary.interfaces.Drawable;
import static com.neon.libary.interfaces.Entity.typeIdentifier.PROJECTILE;
import com.neon.libary.interfaces.Moveable;

public class Projectile implements Moveable, Drawable {

    private Sprite sprite;
    private MoveAbility moveAbility;
    private typeIdentifier type;

    public Projectile(Sprite sprite, MoveAbility moveAbility) {
        type = PROJECTILE;
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

    @Override
    public typeIdentifier getType() {
        return type;
    }


    
}
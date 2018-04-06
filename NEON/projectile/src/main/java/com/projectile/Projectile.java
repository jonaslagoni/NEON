package com.projectile;

import com.library.MoveAbility;
import com.library.Sprite;
import com.library.interfaces.Moveable;
import com.library.interfaces.ProjectileEntity;

public class Projectile implements Moveable, ProjectileEntity {

    Sprite sprite;
    MoveAbility moveAbility;
    float damage;

    public Projectile(Sprite sprite, MoveAbility moveAbility, float damage) {
        this.sprite = sprite;
        this.moveAbility = moveAbility;
        this.moveAbility.setMove(true);
        this.damage = damage;
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
    public float getDamage() {
        return damage;
    }

}

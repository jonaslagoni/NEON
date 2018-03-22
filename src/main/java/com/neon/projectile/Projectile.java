package com.neon.projectile;

import com.neon.libary.MoveAbility;
import com.neon.libary.Sprite;
import com.neon.libary.interfaces.Moveable;
import com.neon.libary.interfaces.ProjectileEntity;

public class Projectile implements Moveable, ProjectileEntity {

    Sprite sprite;
    @SuppressWarnings("WeakerAccess")
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
    public int getDamage() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


}
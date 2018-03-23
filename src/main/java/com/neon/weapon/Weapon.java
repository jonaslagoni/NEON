package com.neon.weapon;

import com.neon.libary.ShotType;
import com.neon.libary.vectors.Vector2f;
import com.neon.libary.interfaces.WeaponEntity;

public class Weapon implements WeaponEntity {

    float fireCooldown;
    float fireRate = 0.5f;
    float range;
    ShotType shotType;
    Vector2f position;
    float damage;

    public Weapon(float range, Vector2f position, ShotType shotType, float damage) {
        this.range = range;
        this.position = position;
        this.shotType = shotType;
        this.damage = damage;
    }
    
    public ShotType getShotType(){
        return shotType;
    }
    
    @Override
    public void affectWeapon(float additiveDamage, float additiveRange, float additiveFireCooldown){
        damage += additiveDamage;
        range = Math.abs(range += additiveRange);
        if(fireRate - additiveFireCooldown >= 0.01){
            fireRate -= additiveFireCooldown;
        }
    }
}

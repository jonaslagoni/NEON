package com.weapon;


import com.library.ShotType;
import com.library.interfaces.WeaponEntity;
import com.library.vectors.Vector2f;

/**
 * An entity class that can shoot projectiles
 */
public class Weapon implements WeaponEntity {

    float fireCooldown;
    float fireRate = 0.5f;
    float range;
    @SuppressWarnings("WeakerAccess")
    ShotType shotType;
    Vector2f position;
    float damage;

    /**
     * @param range    the weapon will shoot at all target within this range
     * @param position the weapons current position in the game
     * @param shotType the type of shot to shoot with
     * @param damage   how much damage the shots should apply
     */
    public Weapon(float range, Vector2f position, ShotType shotType, float damage) {
        this.range = range;
        this.position = position;
        this.shotType = shotType;
        this.damage = damage;
    }

    /**
     * Get the type of shot
     *
     * @return type of shot
     */
    ShotType getShotType() {
        return shotType;
    }

    @Override
    public void affectWeapon(float additiveDamage, float additiveRange, float additiveFireCooldown) {
        damage += additiveDamage;
        range = Math.abs(range += additiveRange);
        if (fireRate - additiveFireCooldown >= 0.01) {
            fireRate -= additiveFireCooldown;
        }
    }
}

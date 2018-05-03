package com.weapon;

import com.library.ShotType;
import com.library.interfaces.IWeaponFactory;
import com.library.interfaces.WeaponEntity;
import com.library.vectors.Vector2f;

/**
 * Factory to instantiate new Weapon objects.
 */
public class WeaponFactory implements IWeaponFactory {

    @Override
    public WeaponEntity newWeapon(float range, Vector2f position, ShotType shotType, float damage) {
        return new Weapon(range, position, shotType, damage);
    }

}

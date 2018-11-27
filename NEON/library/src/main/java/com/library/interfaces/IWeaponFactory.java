package com.library.interfaces;

import com.library.ShotType;
import com.library.vectors.Vector2f;

public interface IWeaponFactory {

    /**
     * Creates a weapon with a range, position, shot type and amount of damage
     *
     * @param range    of weapon
     * @param position at weapon
     * @param shotType weapon shot type
     * @param damage   amount of damage
     * @return weapon
     */
    WeaponEntity newWeapon(float range, Vector2f position, ShotType shotType, float damage);
}

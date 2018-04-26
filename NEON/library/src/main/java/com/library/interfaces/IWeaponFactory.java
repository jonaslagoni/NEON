package com.library.interfaces;

import com.library.ShotType;
import com.library.vectors.Vector2f;

public interface IWeaponFactory {

    WeaponEntity newWeapon(float range, Vector2f position, ShotType shotType, float damage);
}

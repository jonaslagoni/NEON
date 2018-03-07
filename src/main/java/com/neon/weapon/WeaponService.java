package com.neon.weapon;

import com.badlogic.gdx.math.Vector2;
import com.neon.libary.interfaces.Entity;
import com.neon.libary.interfaces.Service;

public class WeaponService implements Service {
    void fireProjectile(Entity weapon, Vector2 position, float angle) {

    }

    Entity buildWeapon(String key) {
        return new Weapon();
    }
}

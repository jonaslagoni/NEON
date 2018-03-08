package com.neon.libary.interfaces;

import com.badlogic.gdx.math.Vector2;

public interface IWeaponService extends Service {

    /**
     * @param entity   weapom
     * @param position where new bullet should appear.
     * @param angle    angle projectile will fly
     */
    void fire(Entity entity, Vector2 position, float angle);
}

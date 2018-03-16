package com.neon.weapon;

import com.neon.libary.interfaces.Entity;
import com.neon.libary.vectors.Vector2f;

public class Weapon implements Entity {

    Vector2f position;
    float fireCooldown;
    float range;

    public Weapon(Vector2f position,
                  float range) {
        this.position = position;
        this.range = range;
    }
}

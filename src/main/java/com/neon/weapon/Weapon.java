package com.neon.weapon;

import com.neon.libary.interfaces.Entity;
import com.neon.libary.vectors.Vector2f;

public class Weapon implements Entity {

    float fireCooldown;
    float range;
    Vector2f position;

    public Weapon(float range, Vector2f position) {
        this.range = range;
        this.position = position;
    }
}

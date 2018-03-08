package com.neon.weapon;

import com.badlogic.gdx.math.Vector2;
import com.neon.libary.interfaces.Entity;
import static com.neon.libary.interfaces.Entity.typeIdentifier.WEAPON;

public class Weapon implements Entity {
    private Entity projectile;
    private typeIdentifier type;

    public Weapon(){
        type = WEAPON;
    }

    @Override
    public typeIdentifier getType() {
        return type;
    }



    private String projectileType;
    private Vector2 position;

    Weapon(String projectileType, Vector2 position) {
        this.projectileType = projectileType;
        this.position = position;
    }

    public String getProjectileType() {
        return projectileType;
    }

    public void setProjectileType(String projectileType) {
        this.projectileType = projectileType;
    }

    public Vector2 getPosition() {
        return position;
    }

    public void setPosition(Vector2 position) {
        this.position = position;
    }
}

package com.neon.libary.interfaces;

public interface Entity {

    enum typeIdentifier {
        PLAYER,
        TOWER,
        ENEMY,
        PROJECTILE,
        WEAPON
    }

    typeIdentifier getType();

}

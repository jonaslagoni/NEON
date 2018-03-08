package com.neon.weapon;

import com.neon.libary.interfaces.Entity;
import static com.neon.libary.interfaces.Entity.typeIdentifier.WEAPON;

@SuppressWarnings("ALL")
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

   
}

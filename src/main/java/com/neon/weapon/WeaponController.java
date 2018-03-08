package com.neon.weapon;

import com.neon.libary.World;
import com.neon.libary.interfaces.Controller;

public class WeaponController implements Controller {

    private World world;

    @Override
    public void update() {
        world.getEntities(Weapon.class).forEach(this::updateWeapon);
    }

    private void updateWeapon(Weapon weapon) {
        //world.addEntity(projectileFactory.build());
    }
}

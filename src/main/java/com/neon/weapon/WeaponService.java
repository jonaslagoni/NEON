package com.neon.weapon;

import com.badlogic.gdx.math.Vector2;
import com.neon.libary.World;
import com.neon.libary.interfaces.Entity;
import com.neon.libary.interfaces.Factory;
import com.neon.libary.interfaces.IWeaponService;

public class WeaponService implements IWeaponService, Factory {

    private World world;
    //private IProjectileService projectileService;

    WeaponService(World world) {
        this.world = world;
        //  this.projectileService = projectileFactory;
    }

    @Override
    public void fire(Entity entity, Vector2 position, float angle) {
        if (!(entity instanceof Weapon)) return;
        Weapon weapon = (Weapon) entity;

        //  projectileService.create(weapon.getProjectileType(), position, angle);
    }

    @Override
    public Entity build(String key) {
        return new Weapon("a", new Vector2());
    }
}

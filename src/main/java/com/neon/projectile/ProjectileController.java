package com.neon.projectile;

import com.neon.libary.World;
import com.neon.libary.interfaces.Controller;

public class ProjectileController implements Controller {
    private World world;

    ProjectileController(World world) {
        this.world = world;
    }

    @Override
    public void update() {
        world.getEntities(Projectile.class).forEach(this::updateProjectile);
    }

    private void updateProjectile(Projectile projectile) {
        if (World.isOutOfBounds(projectile.getSprite().getPosition())) {
            world.removeEntity(projectile);
        }
    }
}

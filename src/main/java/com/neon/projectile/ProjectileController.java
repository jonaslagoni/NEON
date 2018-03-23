package com.neon.projectile;

import com.neon.collision.CollisionService;
import com.neon.libary.GameData;
import com.neon.libary.World;
import com.neon.libary.interfaces.Controller;
import com.neon.libary.interfaces.DamageAble;
import com.neon.libary.interfaces.Entity;
import com.neon.libary.interfaces.ICollisionService;

public class ProjectileController implements Controller {

    private World world;
    private ICollisionService collisionService;

    ProjectileController(World world, GameData gameData) {
        this.world = world;
        this.collisionService = gameData.getService(ICollisionService.class);
    }

    @Override
    public void update() {
        world.getEntities(Projectile.class).forEach(this::updateProjectile);
    }

    private void updateProjectile(Projectile projectile) {
        if (World.isOutOfBounds(projectile.sprite.getPosition())) {
            world.removeEntity(projectile);
        }

        for (Entity entity : collisionService.getCollisions(projectile.sprite)){
                if(entity instanceof DamageAble) {
                    ((DamageAble) entity).setDamage(projectile.damage);
                    world.removeEntity(projectile);
                }
        }
    }


}

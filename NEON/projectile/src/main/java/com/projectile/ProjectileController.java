package com.projectile;

import com.library.GameData;
import com.library.World;
import com.library.interfaces.Controller;
import com.library.interfaces.DamageAble;
import com.library.interfaces.Entity;
import com.library.interfaces.ICollisionService;
import com.library.interfaces.IWorldService;

public class ProjectileController implements Controller {

    private IWorldService world;
    private ICollisionService collisionService;

    public void setWorld(IWorldService world) {
        this.world = world;
    }
    
    public void removeWorld() {
        this.world = null;
    }
    public void setCollisionService(ICollisionService collisionService) {
        this.collisionService = collisionService;
    }

    @Override
    public void update(float dt) {
        world.getEntities(Projectile.class).forEach(this::updateProjectile);
    }

    private void updateProjectile(Projectile projectile) {
        if (World.isOutOfBounds(projectile.sprite.getPosition())) {
            world.removeEntity(projectile);
        }

        for (Entity entity : collisionService.getCollisions(projectile.sprite)) {
            if (entity instanceof DamageAble) {
                ((DamageAble) entity).setDamage(projectile.damage);
                world.removeEntity(projectile);
            }
        }
    }

}

package com.projectile;

import com.library.interfaces.Controller;
import com.library.interfaces.DamageAble;
import com.library.interfaces.ICollisionService;
import com.library.interfaces.IWorldService;

public class ProjectileController implements Controller {

    private IWorldService world;
    private ICollisionService collisionService;
    
    public void setWorld(IWorldService world) {
        this.world = world;
    }

    public void removeWorld(IWorldService world) {
        this.world = null;
    }

    public void setCollisionService(ICollisionService collisionService) {
        this.collisionService = collisionService;
    }
    public void removeCollisionService(ICollisionService collisionService) {
        this.collisionService = null;
    }

    @Override
    public void update(float dt) {
        world.getEntities(Projectile.class).forEach(this::updateProjectile);
    }

    /**
     * If projectile is out of bounds, remove projectile and return
     * <p>
     * If projectile collides with a damageable entity, remove projectile and apply damage
     * @param projectile entity
     */
    private void updateProjectile(Projectile projectile) {
        if (IWorldService.isOutOfBounds(projectile.sprite.getPosition())) {
            world.removeEntity(projectile);
            return;
        }
        collisionService.getCollisions(projectile.sprite).stream()
                .filter(DamageAble.class::isInstance)
                .map(DamageAble.class::cast)
                .forEach(entity -> {
                    entity.damage(projectile.damage);
                    world.removeEntity(projectile);
                });
    }
}

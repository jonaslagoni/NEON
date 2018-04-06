package com.projectile;

import com.library.GameData;
import com.library.World;
import com.library.interfaces.Controller;
import com.library.interfaces.DamageAble;
import com.library.interfaces.Entity;
import com.library.interfaces.ICollisionService;

public class ProjectileController implements Controller {

    private final World world;
    private ICollisionService collisionService;

    ProjectileController(World world, GameData gameData) {
        this.world = world;
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

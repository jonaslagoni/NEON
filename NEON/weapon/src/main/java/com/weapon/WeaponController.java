package com.weapon;

import com.library.interfaces.*;
import com.library.vectors.Vector2f;

import static com.library.vectors.VectorUtils.distanceSquare;

public class WeaponController implements Controller {

    private IWorldService world;
    private ICollisionService collisionService;
    private IProjectileService projectileService;

    private boolean isCloser(Vector2f source, Vector2f first, Vector2f second) {
        return distanceSquare(first, source) < distanceSquare(first, second);
    }

    @Override
    public void update(float dt) {
        world.getEntities(Weapon.class).forEach(weapon -> updateWeapon(weapon, dt));
    }

    private void updateWeapon(Weapon weapon, float dt) {

        weapon.fireCooldown += dt;

        if (weapon.fireCooldown < weapon.fireRate) {
            return;
        }

        Drawable closest = null;

        for (Drawable target : collisionService.getCollisions(weapon.position, weapon.range)) {

            if (closest == null || isCloser(weapon.position, target.getSprite().getPosition(), closest.getSprite().getPosition())) {
                if (target instanceof Targetable) {
                    closest = target;
                }
            }
        }

        if (closest != null) {
            Vector2f position = closest.getSprite().getPosition();

            projectileService.newProjectile(weapon.position, position, weapon.getShotType(), weapon.damage);
            weapon.fireCooldown = 0;
        }
    }

    public void setCollisionService(ICollisionService collisionService) {
        this.collisionService = collisionService;
    }

    public void removeCollisionService(ICollisionService collisionService) {
        this.collisionService = null;
    }

    public void setProjectileService(IProjectileService projectileService) {
        this.projectileService = projectileService;
    }

    public void removeProjectileService(IProjectileService projectileService) {
        this.projectileService = null;
    }

    public void setWorld(IWorldService world) {
        this.world = world;
    }

    public void removeWorld(IWorldService world) {
        this.world = null;
    }

}

package com.neon.weapon;

import com.badlogic.gdx.Gdx;
import com.neon.libary.World;
import com.neon.libary.interfaces.*;
import com.neon.libary.vectors.Vector2f;
import com.neon.libary.vectors.VectorUtils;

import static com.neon.libary.vectors.VectorUtils.distanceSquare;

class WeaponController implements Controller {

    private final World world;
    private final ICollisionService collisionService;
    private final IProjectileService projectileService;
    private final ITargetingService targetingService;

    WeaponController(World world,
                     ICollisionService collisionService,
                     IProjectileService projectileService, ITargetingService targetingService) {
        this.world = world;
        this.collisionService = collisionService;
        this.projectileService = projectileService;
        this.targetingService = targetingService;
    }

    private static boolean isCloser(Vector2f source, Vector2f first, Vector2f second) {
        return distanceSquare(first, source) < distanceSquare(first, second);
    }




    @Override
    public void update() {
        world.getEntities(Weapon.class).forEach(this::updateWeapon);
    }

    private void updateWeapon(Weapon weapon) {

        weapon.fireCooldown += Gdx.graphics.getDeltaTime();

        if (weapon.fireCooldown < weapon.fireRate) return;

        Drawable closest = null;

        for (Drawable target : collisionService.getCollisions(weapon.position, weapon.range)) {
            if (closest == null || isCloser(weapon.position, target.getSprite().getPosition(), closest.getSprite().getPosition()))
                if (target instanceof Targetable)
                    closest = target;



        }


        if (closest != null) {
            projectileService.newProjectile(weapon.position, closest.getSprite().getPosition(), weapon.getShotType(), weapon.damage);

            weapon.fireCooldown = 0;
        }


/**
 * Implements the Targeting system for towers
 */
      //  if (closest != null) {
      //     projectileService.newProjectile(weapon.position, findTarget(closest, weapon));
      //      weapon.fireCooldown = 0;
      //        }
    }
    private Vector2f findTarget(Drawable targetSprite, Weapon weapon) {

            Vector2f newTarget;

            return newTarget = targetingService.calculateTargetVector(weapon.position, targetSprite.getSprite());

    }
}
    

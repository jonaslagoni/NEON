package com.neon.weapon;

import com.badlogic.gdx.Gdx;
import com.neon.libary.World;
import com.neon.libary.interfaces.*;
import com.neon.libary.vectors.Vector2f;

import static com.neon.libary.vectors.VectorUtils.distanceSquare;

class WeaponController implements Controller {

    private final World world;
    private final ICollisionService collisionService;
    private final IProjectileService projectileService;

    WeaponController(World world,
                     ICollisionService collisionService,
                     IProjectileService projectileService) {
        this.world = world;
        this.collisionService = collisionService;
        this.projectileService = projectileService;
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

        if (weapon.fireCooldown < 0.5) return;

        Drawable closest = null;

        for (Drawable target : collisionService.getCollisions(weapon.position, weapon.range)) {
            if (closest == null || isCloser(weapon.position, target.getSprite().getPosition(), closest.getSprite().getPosition()))
                if (target instanceof Targetable)
                    closest = target;
        }

        if (closest != null) {
            projectileService.newProjectile(weapon.position, closest.getSprite().getPosition());
            weapon.fireCooldown = 0;
        }
    }
}

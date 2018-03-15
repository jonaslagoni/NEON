package com.neon.weapon;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.neon.libary.MoveAbility;
import com.neon.libary.Sprite;
import com.neon.libary.World;
import com.neon.libary.interfaces.Controller;
import com.neon.libary.interfaces.Drawable;
import com.neon.libary.interfaces.ICollisionService;
import com.neon.libary.interfaces.Targetable;
import com.neon.projectile.Projectile;

import static com.neon.libary.VectorUtils.angle;
import static com.neon.libary.VectorUtils.distanceSquare;

class WeaponController implements Controller {

    private final World world;
    private final ICollisionService collisionService;

    WeaponController(World world, ICollisionService collisionService) {
        this.world = world;
        this.collisionService = collisionService;
    }

    private static Projectile newProjectile(Vector2 spawn, Vector2 target) {
        float angle = angle(target, spawn);
        return new Projectile(
                new Sprite(
                        new Texture(Gdx.files.internal("images/beam-peashooter.png")),
                        32, 32,
                        angle,
                        new Vector2(spawn)
                ),
                new MoveAbility(750)
        );
    }

    private static boolean isCloser(Vector2 source, Vector2 first, Vector2 second) {
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
            world.addEntity(newProjectile(weapon.position, closest.getSprite().getPosition()));
            weapon.fireCooldown = 0;
        }
    }
}

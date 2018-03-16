package com.neon.weapon;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.neon.libary.MoveAbility;
import com.neon.libary.Sprite;
import com.neon.libary.World;
import com.neon.libary.interfaces.Controller;
import com.neon.libary.interfaces.Drawable;
import com.neon.libary.interfaces.ICollisionService;
import com.neon.libary.interfaces.Targetable;
import com.neon.libary.vectors.Vector2f;
import com.neon.projectile.Projectile;

import static com.neon.libary.vectors.VectorUtils.angle;
import static com.neon.libary.vectors.VectorUtils.distanceSquare;

class WeaponController implements Controller {

    private final World world;
    private final ICollisionService collisionService;

    WeaponController(World world, ICollisionService collisionService) {
        this.world = world;
        this.collisionService = collisionService;
    }

    private static Projectile newProjectile(Vector2f spawn, Vector2f target) {
        return new Projectile(
                new Sprite(
                        new Texture(Gdx.files.internal("images/beam-peashooter.png")),
                        32, 32,
                        angle(target, spawn),
                        new Vector2f(spawn.getX(), spawn.getY())
                ),
                new MoveAbility(750)
        );
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
            world.addEntity(newProjectile(weapon.position, closest.getSprite().getPosition()));
            weapon.fireCooldown = 0;
        }
    }
}

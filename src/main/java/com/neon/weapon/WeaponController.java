package com.neon.weapon;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.neon.enemy.Enemy;
import com.neon.libary.MoveAbility;
import com.neon.libary.Sprite;
import com.neon.libary.World;
import com.neon.libary.interfaces.Controller;
import com.neon.libary.interfaces.Drawable;
import com.neon.libary.interfaces.ICollisionService;
import com.neon.projectile.Projectile;

import static com.neon.libary.VectorUtils.distanceSquare;

public class WeaponController implements Controller {

    private final World world;
    private final ICollisionService collisionService;

    WeaponController(World world, ICollisionService collisionService) {
        this.world = world;
        this.collisionService = collisionService;
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

        if (weapon.fireCooldown < 2) return;

        Drawable closest = null;

        for (Drawable target : collisionService.getCollisions(weapon.position, weapon.range)) {
            if (closest == null || isCloser(weapon.position,
                    target.getSprite().getPosition(),
                    closest.getSprite().getPosition()))
                closest = target;
        }

        if (closest != null && closest instanceof Enemy) {
            Vector2 position = closest.getSprite().getPosition();
            Sprite sprite = new Sprite(new Texture(Gdx.files.internal("images/checked-button.png")), 16, 16);
            sprite.setPosition(new Vector2(weapon.position));
            world.addEntity(new Projectile(sprite, new MoveAbility(new Vector2(position), 1500)));
            weapon.fireCooldown = 0;
        }
    }
}

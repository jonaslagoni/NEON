package com.neon.projectile;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.neon.libary.MoveAbility;
import com.neon.libary.Sprite;
import com.neon.libary.World;
import com.neon.libary.interfaces.IProjectileService;
import com.neon.libary.vectors.Vector2f;

import static com.neon.libary.vectors.VectorUtils.translateVelocity;

public class ProjectileService implements IProjectileService {

    private World world;

    ProjectileService(World world) {
        this.world = world;
    }

    @Override
    public void newProjectile(Vector2f spawn, Vector2f target) {
        world.addEntity(new Projectile(
                new Sprite(
                        new Texture(Gdx.files.internal("images/beam-peashooter.png")),
                        new Vector2f(spawn.getX(), spawn.getY()),
                        translateVelocity(spawn, target, new Vector2f(0, 500)),
                        32, 32
                ),
                new MoveAbility(new Vector2f(0, 0), true)
        ));
    }
}

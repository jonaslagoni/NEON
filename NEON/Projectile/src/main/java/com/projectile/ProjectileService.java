package com.projectile;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.neon.libary.MoveAbility;
import com.neon.libary.ShotType;
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
    public void newProjectile(Vector2f spawn, Vector2f target, ShotType shotType, float damage) {

        switch (shotType) {

            case GREEN_BEAM:
                world.addEntity(new Projectile(
                        new Sprite(
                                new Texture(Gdx.files.internal("images/beams/green_beam.png")),
                                new Vector2f(spawn.getX(), spawn.getY()),
                                translateVelocity(spawn, target, new Vector2f(0, 500)),
                                32, 32
                        ),
                        new MoveAbility(new Vector2f(0, 0), true),
                        damage
                ));
                break;

            case YELLOW_BEAM:
                world.addEntity(new Projectile(
                        new Sprite(
                                new Texture(Gdx.files.internal("images/beams/yellow_beam.png")),
                                new Vector2f(spawn.getX(), spawn.getY()),
                                translateVelocity(spawn, target, new Vector2f(0, 500)),
                                42, 41
                        ),
                        new MoveAbility(new Vector2f(0, 0), true),
                        damage
                ));
                break;

            case BLUE_BEAM:
                world.addEntity(new Projectile(
                        new Sprite(
                                new Texture(Gdx.files.internal("images/beams/blue_bomb.png")),
                                new Vector2f(spawn.getX(), spawn.getY()),
                                translateVelocity(spawn, target, new Vector2f(0, 500)),
                                80, 145
                        ),
                        new MoveAbility(new Vector2f(0, 0), true),
                        damage
                ));
                break;

            case RED_BEAM:
                world.addEntity(new Projectile(
                        new Sprite(
                                new Texture(Gdx.files.internal("images/beams/red_beam.png")),
                                new Vector2f(spawn.getX(), spawn.getY()),
                                translateVelocity(spawn, target, new Vector2f(0, 500)),
                                37, 41
                        ),
                        new MoveAbility(new Vector2f(0, 0), true),
                        damage
                ));
                break;

            case PINK_LASER:
                world.addEntity(new Projectile(
                        new Sprite(
                                new Texture(Gdx.files.internal("images/beams/pink_laser.png")),
                                new Vector2f(spawn.getX(), spawn.getY()),
                                translateVelocity(spawn, target, new Vector2f(0, 500)),
                                26, 50
                        ),
                        new MoveAbility(new Vector2f(0, 0), true),
                        damage
                ));
                break;

            case ROCKET_SHOT:
                world.addEntity(new Projectile(
                        new Sprite(
                                new Texture(Gdx.files.internal("images/beams/rocket_shot.png")),
                                new Vector2f(spawn.getX(), spawn.getY()),
                                translateVelocity(spawn, target, new Vector2f(0, 500)),
                                28, 98
                        ),
                        new MoveAbility(new Vector2f(0, 0), true),
                        damage
                ));
                break;

            case GREEN_BOMB_SMALLER:
                world.addEntity(new Projectile(
                        new Sprite(
                                new Texture(Gdx.files.internal("images/beams/green_bomb.png")),
                                new Vector2f(spawn.getX(), spawn.getY()),
                                translateVelocity(spawn, target, new Vector2f(0, 500)),
                                67, 127
                        ),
                        new MoveAbility(new Vector2f(0, 0), true),
                        damage
                ));
                break;

        }
    }

}

package com.projectile;

import com.library.MoveAbility;
import com.library.ShotType;
import com.library.Sprite;
import com.library.interfaces.IProjectileService;
import com.library.interfaces.IWorldService;
import com.library.vectors.Vector2f;
import static com.library.vectors.VectorUtils.translateVelocity;

public class ProjectileService implements IProjectileService {

    private IWorldService world;
    
    public void setWorld(IWorldService world) {
        this.world = world;
    }
    
    public void removeWorld(IWorldService world) {
        this.world = null;
    }

    @Override
    public void newProjectile(Vector2f spawn, Vector2f target, ShotType shotType, float damage) {
        switch (shotType) {
            case GREEN_BEAM:
                world.addEntity(new Projectile(
                        new Sprite(
                                "GREEN_BEAM",
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
                                "YELLOW_BEAM",
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
                                "BLUE_BEAM",
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
                                "RED_BEAM",
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
                                "PINK_LASER",
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
                                "ROCKET_SHOT",
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
                                "GREEN_BOMB_SMALLER",
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

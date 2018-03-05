package com.neon.enemy;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Timer;
import com.neon.main.GameData;
import com.neon.main.Plugin;
import com.neon.main.World;
import com.neon.main.entities.Entity;
import com.neon.main.entities.MoveAbility;
import com.neon.main.entities.Sprite;

public class EnemyPlugin implements Plugin {

    private static Entity createEnemy() {
        Sprite sprite = new Sprite(new Texture(Gdx.files.internal("images/enemy.png")), World.HEIGHT / 32, World.HEIGHT / 32);
        sprite.setPosition(World.WIDTH / 2, World.HEIGHT);

        MoveAbility moveAbility = new MoveAbility(10);
        moveAbility.setTargetVector(new Vector2(World.WIDTH / 2, 0));
        moveAbility.setTarget(true);

        return new Enemy(sprite, moveAbility);
    }

    @Override
    public void start(GameData gameData, World world) {
        Timer.schedule(new Timer.Task() {
            @Override
            public void run() {
                world.addEntity(createEnemy());
            }
        }, 0, 3, 10);
    }

    @Override
    public void stop(GameData gameData, World world) {
        for (Enemy enemy : world.getEntities(Enemy.class)) {
            world.removeEntity(enemy);
        }
    }
}

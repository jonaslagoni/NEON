package com.neon.enemy;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.neon.main.GameData;
import com.neon.main.Plugin;
import com.neon.main.World;
import com.neon.main.entities.MoveAbility;
import com.neon.main.entities.Sprite;

import static com.badlogic.gdx.utils.Timer.Task;
import static com.badlogic.gdx.utils.Timer.schedule;

public class EnemyPlugin implements Plugin {


    @Override
    public void start(GameData gameData, World world) {

        schedule(new Task() {
            @Override
            public void run() {
                Sprite sprite = new Sprite(
                        new Texture(Gdx.files.internal("images/enemy.png")),
                        new Vector2(World.WIDTH / 2, World.HEIGHT),
                        World.HEIGHT / 32, World.HEIGHT / 32
                );
                MoveAbility moveAbility = new MoveAbility(10);
                moveAbility.setTargetVector(new Vector2(World.WIDTH / 2, 0));
                moveAbility.setTarget(true);

                Enemy enemy = new Enemy(sprite, moveAbility);

                world.addEntity(enemy);
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

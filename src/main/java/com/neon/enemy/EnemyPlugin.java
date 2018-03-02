package com.neon.enemy;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Timer;
import com.neon.main.GameData;
import com.neon.main.Plugin;
import com.neon.main.World;
import com.neon.main.entities.MoveAbility;
import com.neon.main.entities.Position;

import java.util.Iterator;
import java.util.List;

public class EnemyPlugin implements Plugin {


    @Override
    public void start(GameData gameData, World world) {
        Timer.schedule(new Timer.Task() {
            @Override
            public void run() {
                Enemy enemy = new Enemy(
                        new Texture(Gdx.files.internal("images/enemy.png")),
                        new Position(new Vector2(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight()), 0),
                        new MoveAbility(new Vector2(Gdx.graphics.getWidth() / 2, 0), 20)
                );
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

package com.neon.enemy;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Timer;
import com.neon.libary.GameData;
import com.neon.libary.MoveAbility;
import com.neon.libary.Sprite;
import com.neon.libary.World;
import com.neon.libary.interfaces.Entity;
import com.neon.libary.interfaces.IWaveService;
import com.neon.libary.interfaces.Plugin;
import com.neon.wave.Wave;

import java.util.List;

public class EnemyPlugin implements Plugin {

    private World world;
    private GameData gameData;

    public EnemyPlugin(World world, GameData gameData) {
        this.world = world;
        this.gameData = gameData;
    }

    @Override
    public void start() {
        gameData.addController(new EnemyController(world, gameData));
    }

    @Override
    public void stop() {
        for (Enemy enemy : world.getEntities(Enemy.class)) {
            world.removeEntity(enemy);
        }
    }
}

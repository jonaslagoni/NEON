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
import com.neon.libary.interfaces.Plugin;

public class EnemyPlugin implements Plugin {

    private World world;
    private GameData gameData;

    public EnemyPlugin(World world, GameData gameData) {
        this.world = world;
        this.gameData = gameData;
    }

    private static Entity createEnemy() {

        MoveAbility moveAbility = new MoveAbility(140);
        moveAbility.setTargetVector(new Vector2(World.WIDTH / 2, 0));
        moveAbility.setTarget(true);

        Texture[] texture = {
            new Texture(Gdx.files.internal("images/Enemies/Tier2/Hexagon1.png")),
            new Texture(Gdx.files.internal("images/Enemies/Tier2/Hexagon2.png")),
            new Texture(Gdx.files.internal("images/Enemies/Tier2/Hexagon3.png")),
            new Texture(Gdx.files.internal("images/Enemies/Tier2/Hexagon4.png")),
            new Texture(Gdx.files.internal("images/Enemies/Tier2/Hexagon5.png")),
            new Texture(Gdx.files.internal("images/Enemies/Tier2/Hexagon6.png"))

        };

        Sprite sprite = new Sprite(new Texture(Gdx.files.internal("images/Enemies/Tier2/Hexagon6.png")), World.HEIGHT / 32, World.HEIGHT / 32);
        sprite.setPosition(World.WIDTH / 2, World.HEIGHT);


        return new Enemy(sprite, moveAbility, texture);
    }

    @Override
    public void start() {
        gameData.addController(new EnemyController(world, gameData));
        Timer.schedule(new Timer.Task() {
            @Override
            public void run() {
                world.addEntity(createEnemy());
            }
        }, 0, 3, 10);
    }

    @Override
    public void stop() {
        for (Enemy enemy : world.getEntities(Enemy.class)) {
            world.removeEntity(enemy);
        }

    }

}

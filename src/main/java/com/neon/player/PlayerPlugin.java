package com.neon.player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Texture;
import com.neon.libary.GameData;
import com.neon.libary.MoveAbility;
import com.neon.libary.Sprite;
import com.neon.libary.World;
import com.neon.libary.interfaces.Plugin;
import com.neon.libary.vectors.Vector2f;

public class PlayerPlugin implements Plugin {

    private InputProcessor inputProcessor;
    private PlayerController playerController;
    private Player player;
    private World world;
    private GameData gameData;
    private static final int PLAYER_SIZE = 64;

    public PlayerPlugin(World world, GameData gameData) {
        this.world = world;
        this.gameData = gameData;
    }

    @Override
    public void start() {
        player = new Player(new Sprite(
                new Texture(Gdx.files.internal("images/tower1.png")),
                new Vector2f(World.WIDTH / 2, World.HEIGHT / 2),
                new Vector2f(0, 200),
                PLAYER_SIZE,
                PLAYER_SIZE),
                new MoveAbility(new Vector2f(0, 0), false));
        world.addEntity(player);

        inputProcessor = new PlayerInputProcessor(player, gameData);
        playerController = new PlayerController(world);

        gameData.addController(playerController);
        gameData.addInputProcessor(inputProcessor);
    }

    @Override
    public void stop() {
        world.removeEntity(player);
        gameData.removeController(playerController);
        gameData.removeInputProcessor(inputProcessor);
    }
}

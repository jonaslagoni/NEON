package com.neon.player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.neon.main.GameData;
import com.neon.main.Plugin;
import com.neon.main.World;
import com.neon.main.entities.MoveAbility;
import com.neon.main.entities.Position;

public class PlayerPlugin implements Plugin {

    private InputProcessor inputProcessor;
    private PlayerController playerController;
    private Player player;

    @Override
    public void start(GameData gameData, World world) {

        player = new Player(
                new Texture(Gdx.files.internal("images/tower1.png")),
                new Position(new Vector2(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2), 0),
                new MoveAbility(new Vector2(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2), 150)
        );

        world.addEntity(player);

        inputProcessor = new PlayerInputProcessor(player);
        playerController = new PlayerController();

        gameData.addController(playerController);
        gameData.addInputProcessor(inputProcessor);

    }

    @Override
    public void stop(GameData gameData, World world) {
        world.removeEntity(player);
        gameData.removeController(playerController);
        gameData.removeInputProcessor(inputProcessor);
    }
}

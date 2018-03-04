package com.neon.player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Texture;
import com.neon.main.GameData;
import com.neon.main.Plugin;
import com.neon.main.World;
import com.neon.main.entities.MoveAbility;
import com.neon.main.entities.Sprite;

public class PlayerPlugin implements Plugin {

    private InputProcessor inputProcessor;
    private PlayerController playerController;
    private Player player;

    @Override
    public void start(GameData gameData, World world) {

        Sprite sprite = new Sprite(
                new Texture(Gdx.files.internal("images/tower1.png")),
                World.GRID_CELL_SIZE,
                World.GRID_CELL_SIZE
        );
        sprite.setPosition(World.WIDTH / 2, World.HEIGHT / 2);

        player = new Player(new MoveAbility(100), sprite);

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

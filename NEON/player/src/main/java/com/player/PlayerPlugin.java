package com.player;

import com.library.MoveAbility;
import com.library.Sprite;
import com.library.interfaces.IAssetManager;
import com.library.interfaces.IWorldService;
import com.library.interfaces.Plugin;
import com.library.vectors.Vector2f;

import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PlayerPlugin implements Plugin {

    private static final int PLAYER_SIZE = 64;
    private static final String ASSET_NAME = "player";
    private static final String ASSET_PATH = "player.png";
    private static final Logger LOGGER = Logger.getLogger(PlayerPlugin.class.getName());

    private Player player;
    private IWorldService world;
    private IAssetManager assetManager;

    public void setAssetManager(IAssetManager assetManager) {
        this.assetManager = assetManager;
    }

    public void removeAssetManager(IAssetManager assetManager) {
        this.assetManager = null;
    }

    public void setWorld(IWorldService world) {
        this.world = world;
    }

    @Override
    public void start() {
        try (InputStream stream = getClass().getClassLoader().getResourceAsStream(ASSET_PATH)) {
            assetManager.loadAsset(ASSET_NAME, stream);
        } catch (IOException ex) {
            LOGGER.log(Level.SEVERE, ex.toString(), ex);
        }
        player = new Player(new Sprite(
                ASSET_NAME,
                new Vector2f(IWorldService.WIDTH / 2, IWorldService.HEIGHT / 2),
                new Vector2f(0, 200),
                PLAYER_SIZE,
                PLAYER_SIZE),
                new MoveAbility(new Vector2f(0, 0), false));
        world.addEntity(player);

    }

    public void removeWorld(IWorldService world) {
        this.world = null;
    }

    @Override
    public void stop() {
        assetManager.unloadAsset(ASSET_NAME);
        world.removeEntity(player);
    }
}

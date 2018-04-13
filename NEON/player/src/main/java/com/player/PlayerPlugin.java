package com.player;

import com.library.MoveAbility;
import com.library.Sprite;
import com.library.World;
import com.library.interfaces.IAssetManager;
import com.library.interfaces.Plugin;
import com.library.vectors.Vector2f;
import java.io.File;

public class PlayerPlugin implements Plugin {

    private static final int PLAYER_SIZE = 64;
    private static final String ASSET_NAME = "player";
    private static final String ASSET_PATH = "player.png";

    private Player player;
    private World world;
    private IAssetManager assetManager;

    public void setAssetManager(IAssetManager assetManager) {
        this.assetManager = assetManager;
    }

    public void setWorld(World world) {
        this.world = world;
    }

    public void removeAssetManager() {
        this.assetManager = null;
    }
    
    public void removeWorld(){
        this.world = null;
    }
    
    @Override
    public void start() {
        assetManager.loadAsset(ASSET_NAME, new File(getClass()
                .getClassLoader()
                .getResource(ASSET_PATH)
                .getFile()
        ));
        player = new Player(new Sprite(
                ASSET_NAME,
                new Vector2f(World.WIDTH / 2, World.HEIGHT / 2),
                new Vector2f(0, 200),
                PLAYER_SIZE,
                PLAYER_SIZE),
                new MoveAbility(new Vector2f(0, 0), false));
        world.addEntity(player);
    }

    @Override
    public void stop() {
        assetManager.unloadAsset(ASSET_NAME);
        world.removeEntity(player);
    }
}

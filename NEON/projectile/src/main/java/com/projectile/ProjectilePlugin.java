package com.projectile;

import com.library.interfaces.IAssetManager;
import com.library.interfaces.Plugin;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ProjectilePlugin implements Plugin {

    private static final Logger LOGGER = Logger.getLogger(ProjectilePlugin.class.getName());
    private static final Map<String, String> projectiles;

    static {
        projectiles = new HashMap<>();
        projectiles.put("GREEN_BEAM", "green_beam.png");
        projectiles.put("YELLOW_BEAM", "yellow_beam.png");
        projectiles.put("BLUE_BEAM", "blue_bomb.png");
        projectiles.put("RED_BEAM", "red_beam.png");
        projectiles.put("PINK_LASER", "pink_laser.png");
        projectiles.put("ROCKET_SHOT", "rocket_shot.png");
        projectiles.put("GREEN_BOMB_SMALLER", "green_bomb.png");
    }

    private IAssetManager assetManager;

    public void setAssetManager(IAssetManager assetManager) {
        this.assetManager = assetManager;
    }

    public void removeAssetManager(IAssetManager assetManager) {
        this.assetManager = null;
    }

    @Override
    public void start() {
        for (Map.Entry<String, String> entry : projectiles.entrySet()) {
            try (InputStream stream = getClass().getClassLoader().getResourceAsStream(entry.getValue())) {
                assetManager.loadAsset(entry.getKey(), stream);
            } catch (IOException ex) {
                LOGGER.log(Level.SEVERE, ex.toString(), ex);
            }
        }
    }

    @Override
    public void stop() {
        for (Map.Entry<String, String> entry : projectiles.entrySet()) {
            assetManager.unloadAsset(entry.getKey());
        }
    }
}

package com.neon.tower;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.neon.libary.Sprite;
import com.neon.libary.World;
import com.neon.libary.interfaces.Entity;
import com.neon.libary.interfaces.Factory;

public class TowerFactory implements Factory {

    @Override
    public Entity build(String key) {
        switch (key) {
            case "laser-tower":

                Sprite sprite = new Sprite(
                        new Texture(Gdx.files.internal("images/laser-tower.png")),
                        World.GRID_CELL_SIZE,
                        World.GRID_CELL_SIZE
                );

                Texture[] upgrades = {
                        new Texture(Gdx.files.internal("images/towers/Laser Tower (Constant focused single target)/Laser Tower lvl 2.png")),
                        new Texture(Gdx.files.internal("images/towers/Laser Tower (Constant focused single target)/Laser Tower lvl 3.png")),
                        new Texture(Gdx.files.internal("images/towers/Laser Tower (Constant focused single target)/Laser Tower lvl 4.png"))
                };

                return new Tower(sprite, upgrades);
        }
        return null;
    }
}

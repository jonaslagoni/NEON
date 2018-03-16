package com.neon.tower;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.neon.libary.Sprite;
import com.neon.libary.World;
import com.neon.libary.interfaces.Entity;
import com.neon.libary.interfaces.Factory;
import com.neon.libary.vectors.Vector2f;
import com.neon.weapon.Weapon;

public class TowerFactory implements Factory {

    @Override
    public Entity build(String key) {
        switch (key) {
            case "laser-tower":

                Sprite sprite = new Sprite(
                        new Texture(Gdx.files.internal("images/laser-tower.png")),
                        World.GRID_CELL_SIZE,
                        World.GRID_CELL_SIZE, 0, new Vector2f(0, 0)
                );

                Texture[] upgrades = {
                        new Texture(Gdx.files.internal("images/towers/Laser Tower (Constant focused single target)/Laser Tower lvl 2.png")),
                        new Texture(Gdx.files.internal("images/towers/Laser Tower (Constant focused single target)/Laser Tower lvl 3.png")),
                        new Texture(Gdx.files.internal("images/towers/Laser Tower (Constant focused single target)/Laser Tower lvl 4.png"))
                };

                Weapon weapon = new Weapon(sprite.getPosition(), 512);

                return new Tower(sprite, upgrades, weapon, 10);
        }

        return null;
    }
}

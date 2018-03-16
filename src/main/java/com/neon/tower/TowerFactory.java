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
                Sprite laserSprite = new Sprite(
                        new Texture(Gdx.files.internal("images/towers/Laser Tower (Constant focused single target)/Laser Tower lvl 1.png")),
                        World.GRID_CELL_SIZE,
                        World.GRID_CELL_SIZE, 0, new Vector2f(0, 0)
                );

                Texture[] laserUpgrades = {
                        new Texture(Gdx.files.internal("images/towers/Laser Tower (Constant focused single target)/Laser Tower lvl 2.png")),
                        new Texture(Gdx.files.internal("images/towers/Laser Tower (Constant focused single target)/Laser Tower lvl 3.png")),
                        new Texture(Gdx.files.internal("images/towers/Laser Tower (Constant focused single target)/Laser Tower lvl 4.png"))
                };

                Weapon laserWeapon = new Weapon(laserSprite.getPosition(), 512);
                return new Tower(laserSprite, laserUpgrades, laserWeapon, 10, 4);
            case "melee-glaive-tower":
                Sprite meleeTower = new Sprite(
                        new Texture(Gdx.files.internal("C:/Users/Lagoni/Documents/SmartGit/NEON/images/towers/Melee Glaive Tower/Melee Glaive Tower lvl 1.1.png")),
                        World.GRID_CELL_SIZE,
                        World.GRID_CELL_SIZE
                );

                Texture[] meleeUpgrades = {
                        new Texture(Gdx.files.internal("C:/Users/Lagoni/Documents/SmartGit/NEON/images/towers/Melee Glaive Tower/Melee Glaive Tower lvl 2.1.png")),
                        new Texture(Gdx.files.internal("C:/Users/Lagoni/Documents/SmartGit/NEON/images/towers/Melee Glaive Tower/Melee Glaive Tower lvl 3.1.png")),
                        new Texture(Gdx.files.internal("C:/Users/Lagoni/Documents/SmartGit/NEON/images/towers/Melee Glaive Tower/Melee Glaive Tower lvl 4.1.png"))
                };

                Weapon meleeWeapon = new Weapon(meleeTower.getPosition(), 512);
                return new Tower(meleeTower, meleeUpgrades, meleeWeapon, 10, 4);
        }

        return null;
    }
}

package com.neon.tower;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.neon.libary.Sprite;
import com.neon.libary.World;
import com.neon.libary.interfaces.Entity;
import com.neon.libary.interfaces.Factory;
import com.neon.libary.vectors.Vector2f;
import com.neon.weapon.Weapon;

public class TowerFactory {

    
    public static Tower build(String key) {
        switch (key) {
            case "laser-tower":
                Sprite laserSprite = new Sprite(
                        new Texture(Gdx.files.internal("images/towers/Laser Tower (Constant focused single target)/Laser Tower lvl 1.png")),
                        World.GRID_CELL_SIZE,
                        World.GRID_CELL_SIZE,
                        0,
                        new Vector2f(0, 0)
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
                        new Texture(Gdx.files.internal("images/towers/Melee Glaive Tower/Melee Glaive Tower lvl 1.1.png")),
                        World.GRID_CELL_SIZE,
                        World.GRID_CELL_SIZE,
                        0,
                        new Vector2f(0, 0)
                );

                Texture[] meleeUpgrades = {
                        new Texture(Gdx.files.internal("images/towers/Melee Glaive Tower/Melee Glaive Tower lvl 2.1.png")),
                        new Texture(Gdx.files.internal("images/towers/Melee Glaive Tower/Melee Glaive Tower lvl 3.1.png")),
                        new Texture(Gdx.files.internal("images/towers/Melee Glaive Tower/Melee Glaive Tower lvl 4.1.png"))
                };

                Weapon meleeWeapon = new Weapon(meleeTower.getPosition(), 512);
                return new Tower(meleeTower, meleeUpgrades, meleeWeapon, 10, 4);
                
            case "pea-shooter":
                Sprite peaTower = new Sprite(
                        new Texture(Gdx.files.internal("images/towers/Pea shooter (Single target)/Blue Pea Shooter lvl 1.png")),
                        World.GRID_CELL_SIZE,
                        World.GRID_CELL_SIZE,
                        0,
                        new Vector2f(0, 0)
                );

                Texture[] peaUpgrades = {
                        new Texture(Gdx.files.internal("images/towers/Pea shooter (Single target)/Blue Pea Shooter lvl 2.png")),
                        new Texture(Gdx.files.internal("images/towers/Pea shooter (Single target)/Blue Pea Shooter lvl 3.png")),
                        new Texture(Gdx.files.internal("images/towers/Pea shooter (Single target)/Blue Pea Shooter lvl 4.png"))
                };

                Weapon peaWeapon = new Weapon(peaTower.getPosition(), 512);
                return new Tower(peaTower, peaUpgrades, peaWeapon, 10, 4);
                
            case "range-powerup":
                Sprite rangePowerup = new Sprite(
                        new Texture(Gdx.files.internal("images/towers/Powerup/Range Powerup v1.png")),
                        World.GRID_CELL_SIZE,
                        World.GRID_CELL_SIZE,
                        0,
                        new Vector2f(0, 0)
                );

                Texture[] rangePowerupUpgrades = {
                        new Texture(Gdx.files.internal("images/towers/Powerup/Range Powerup v2.png"))
                };

                Weapon rangePowerupWeapon = new Weapon(rangePowerup.getPosition(), 512);
                return new Tower(rangePowerup, rangePowerupUpgrades, rangePowerupWeapon, 10, 2);
                
            case "strenght-powerup":
                Sprite strenghtPowerup = new Sprite(
                        new Texture(Gdx.files.internal("images/towers/Powerup/Strength Powerup non-glowing.png")),
                        World.GRID_CELL_SIZE,
                        World.GRID_CELL_SIZE,
                        0, 
                        new Vector2f(0, 0)
                );

                Texture[] strenghtPowerupUpgrades = {
                        new Texture(Gdx.files.internal("images/towers/Powerup/Strength Powerup.png"))
                };
                
                Weapon strenghtPowerupWeapon = new Weapon(strenghtPowerup.getPosition(), 512);
                return new Tower(strenghtPowerup, strenghtPowerupUpgrades, strenghtPowerupWeapon, 10, 2);
            
            
            case "railgun-tower":
                Sprite railgunTower = new Sprite(
                        new Texture(Gdx.files.internal("images/towers/Railgun Tower (Long distance sniper)/Railgun Tower lvl 1.png")),
                        World.GRID_CELL_SIZE,
                        World.GRID_CELL_SIZE,
                        0,
                        new Vector2f(0, 0)
                );

                Texture[] railgunUpgrades = {
                        new Texture(Gdx.files.internal("images/towers/Railgun Tower (Long distance sniper)/Railgun Tower lvl 2.png")),
                        new Texture(Gdx.files.internal("images/towers/Railgun Tower (Long distance sniper)/Railgun Tower lvl 3.png")),
                        new Texture(Gdx.files.internal("images/towers/Railgun Tower (Long distance sniper)/Railgun Tower lvl 4.png"))
                };

                Weapon railgunWeapon = new Weapon(railgunTower.getPosition(), 512);
                return new Tower(railgunTower, railgunUpgrades, railgunWeapon, 10, 4);
                
            case "rocket-tower":
                Sprite rocketTower = new Sprite(
                        new Texture(Gdx.files.internal("images/towers/Rocket Tower (Curve & AOE)/Orange Rocket Tower lvl 1.png")),
                        World.GRID_CELL_SIZE,
                        World.GRID_CELL_SIZE,
                        0,
                        new Vector2f(0, 0)
                );

                Texture[] rocketUpgrades = {
                        new Texture(Gdx.files.internal("images/towers/Rocket Tower (Curve & AOE)/Orange Rocket Tower lvl 2.png")),
                        new Texture(Gdx.files.internal("images/towers/Rocket Tower (Curve & AOE)/Orange Rocket Tower lvl 3.png")),
                        new Texture(Gdx.files.internal("images/towers/Rocket Tower (Curve & AOE)/Orange Rocket Tower lvl 4.png"))
                };

                Weapon rocketWeapon = new Weapon(rocketTower.getPosition(), 512);
                return new Tower(rocketTower, rocketUpgrades, rocketWeapon, 10, 4);
                
            case "splash-tower":
                Sprite splashTower = new Sprite(
                        new Texture(Gdx.files.internal("images/towers/Splash Tower (AOE)/Red Splash Tower lvl 1.png")),
                        World.GRID_CELL_SIZE,
                        World.GRID_CELL_SIZE,
                        0,
                        new Vector2f(0, 0)
                );

                Texture[] splasUpgrades = {
                        new Texture(Gdx.files.internal("images/towers/Splash Tower (AOE)/Red Splash Tower lvl 2.png")),
                        new Texture(Gdx.files.internal("images/towers/Splash Tower (AOE)/Red Splash Tower lvl 3.png")),
                        new Texture(Gdx.files.internal("images/towers/Splash Tower (AOE)/Red Splash Tower lvl 4.png")),
                };

                Weapon splashWeapon = new Weapon(splashTower.getPosition(), 512);
                return new Tower(splashTower, splasUpgrades, splashWeapon, 10, 4);
        }

        return null;
    }
}

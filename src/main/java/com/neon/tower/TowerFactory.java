package com.neon.tower;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.neon.libary.Sprite;
import com.neon.libary.World;
import com.neon.libary.vectors.Vector2f;

import java.util.Arrays;

public class TowerFactory {
    private TowerFactory() {
    }

    public static Tower build(String key) {
        switch (key) {
            case "laser-tower":
                return build(
                        "images/towers/Laser Tower (Constant focused single target)/Laser Tower lvl 1.png",
                        "images/towers/Laser Tower (Constant focused single target)/Laser Tower lvl 2.png",
                        "images/towers/Laser Tower (Constant focused single target)/Laser Tower lvl 3.png",
                        "images/towers/Laser Tower (Constant focused single target)/Laser Tower lvl 4.png"
                );
            case "melee-glaive-tower":
                return build(
                        "images/towers/Melee Glaive Tower/Melee Glaive Tower lvl 1.1.png",
                        "images/towers/Melee Glaive Tower/Melee Glaive Tower lvl 2.1.png",
                        "images/towers/Melee Glaive Tower/Melee Glaive Tower lvl 3.1.png",
                        "images/towers/Melee Glaive Tower/Melee Glaive Tower lvl 4.1.png"
                );
            case "pea-shooter":
                return build(
                        "images/towers/Pea shooter (Single target)/Blue Pea Shooter lvl 1.png",
                        "images/towers/Pea shooter (Single target)/Blue Pea Shooter lvl 2.png",
                        "images/towers/Pea shooter (Single target)/Blue Pea Shooter lvl 3.png",
                        "images/towers/Pea shooter (Single target)/Blue Pea Shooter lvl 4.png"
                );
            case "range-powerup":
                return build(
                        "images/towers/Powerup/Range Powerup v1.png",
                        "images/towers/Powerup/Range Powerup v2.png"
                );
            case "strenght-powerup":
                return build(
                        "images/towers/Powerup/Strength Powerup non-glowing.png",
                        "images/towers/Powerup/Strength Powerup.png"
                );
            case "railgun-tower":
                return build(
                        "images/towers/Railgun Tower (Long distance sniper)/Railgun Tower lvl 1.png",
                        "images/towers/Railgun Tower (Long distance sniper)/Railgun Tower lvl 2.png",
                        "images/towers/Railgun Tower (Long distance sniper)/Railgun Tower lvl 3.png",
                        "images/towers/Railgun Tower (Long distance sniper)/Railgun Tower lvl 4.png"
                );
            case "rocket-tower":
                return build(
                        "images/towers/Rocket Tower (Curve & AOE)/Orange Rocket Tower lvl 1.png",
                        "images/towers/Rocket Tower (Curve & AOE)/Orange Rocket Tower lvl 2.png",
                        "images/towers/Rocket Tower (Curve & AOE)/Orange Rocket Tower lvl 3.png",
                        "images/towers/Rocket Tower (Curve & AOE)/Orange Rocket Tower lvl 4.png"
                );
            case "splash-tower":
                return build(
                        "images/towers/Splash Tower (AOE)/Red Splash Tower lvl 1.png",
                        "images/towers/Splash Tower (AOE)/Red Splash Tower lvl 2.png",
                        "images/towers/Splash Tower (AOE)/Red Splash Tower lvl 3.png",
                        "images/towers/Splash Tower (AOE)/Red Splash Tower lvl 4.png"
                );
        }
        return null;
    }

    private static Tower build(String... files) {
        Texture[] textures = Arrays.stream(files).map(Gdx.files::internal).map(Texture::new).toArray(Texture[]::new);
        Vector2f position = new Vector2f(0, 0);
        return new Tower(new Sprite(
                textures[0],
                position,
                new Vector2f(0, 0),
                World.GRID_CELL_SIZE,
                World.GRID_CELL_SIZE
        ), textures, 10, 4);
    }
}

package com.neon.enemy;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.neon.libary.MoveAbility;
import com.neon.libary.Sprite;
import com.neon.libary.World;
import com.neon.libary.interfaces.Entity;

/**
 * Created by sam on 12-03-2018.
 */
public class EnemyFactory {


    public static Entity createEnemy(String tier, String type) {

        MoveAbility moveAbility = new MoveAbility(140);
        moveAbility.setTargetVector(new Vector2(World.WIDTH / 2, 0));

        switch (tier) {
            case "tier1":
                switch (type) {
                    case "circle":

                        Sprite sprite = new Sprite(new Texture(Gdx.files.internal("images/Enemies/Tier1/Circle/6.png")), World.HEIGHT / 32, World.HEIGHT / 32);
                        sprite.setPosition(World.WIDTH / 2, World.HEIGHT);

                        Texture[] texture = {
                                new Texture(Gdx.files.internal("images/Enemies/Tier1/Circle/1.png")),
                                new Texture(Gdx.files.internal("images/Enemies/Tier1/Circle/2.png")),
                                new Texture(Gdx.files.internal("images/Enemies/Tier1/Circle/3.png")),
                                new Texture(Gdx.files.internal("images/Enemies/Tier1/Circle/4.png")),
                                new Texture(Gdx.files.internal("images/Enemies/Tier1/Circle/5.png")),
                                new Texture(Gdx.files.internal("images/Enemies/Tier1/Circle/6.png"))

                        };

                        return new Enemy(sprite, moveAbility, texture);
                }

            case "tier2":
                switch (type) {
                    case "hexagon":

                        Sprite sprite = new Sprite(new Texture(Gdx.files.internal("images/Enemies/Tier2/Hexagon/6.png")), World.HEIGHT / 32, World.HEIGHT / 32);
                        sprite.setPosition(World.WIDTH / 2, World.HEIGHT);

                        Texture[] texture = {
                                new Texture(Gdx.files.internal("images/Enemies/Tier2/Hexagon/1.png")),
                                new Texture(Gdx.files.internal("images/Enemies/Tier2/Hexagon/2.png")),
                                new Texture(Gdx.files.internal("images/Enemies/Tier2/Hexagon/3.png")),
                                new Texture(Gdx.files.internal("images/Enemies/Tier2/Hexagon/4.png")),
                                new Texture(Gdx.files.internal("images/Enemies/Tier2/Hexagon/5.png")),
                                new Texture(Gdx.files.internal("images/Enemies/Tier2/Hexagon/6.png"))

                        };

                        return new Enemy(sprite, moveAbility, texture);
                }

            case "tier3":
                switch (type) {
                    case "star":
                        Sprite sprite = new Sprite(new Texture(Gdx.files.internal("images/Enemies/Tier3/Star/6.png")), World.HEIGHT / 32, World.HEIGHT / 32);
                        sprite.setPosition(World.WIDTH / 2, World.HEIGHT);

                        Texture[] texture = {
                                new Texture(Gdx.files.internal("images/Enemies/Tier3/Star/1.png")),
                                new Texture(Gdx.files.internal("images/Enemies/Tier3/Star/2.png")),
                                new Texture(Gdx.files.internal("images/Enemies/Tier3/Star/3.png")),
                                new Texture(Gdx.files.internal("images/Enemies/Tier3/Star/4.png")),
                                new Texture(Gdx.files.internal("images/Enemies/Tier3/Star/5.png")),
                                new Texture(Gdx.files.internal("images/Enemies/Tier3/Star/6.png"))

                        };

                        return new Enemy(sprite, moveAbility, texture);
                }
                return null;
        }
        return null;
    }
}

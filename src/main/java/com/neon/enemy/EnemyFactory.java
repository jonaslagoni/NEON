package com.neon.enemy;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.neon.libary.MoveAbility;
import com.neon.libary.Sprite;
import com.neon.libary.World;
import com.neon.libary.interfaces.Entity;

/**
 * Created by sam on 12-03-2018.
 */
public class EnemyFactory {

    private static int START_POS_X = 960;
    private static int START_POS_Y = 2047;


    public static Entity createEnemy(String tier, int type) {

        // TODO clean tier and type switch

        MoveAbility moveAbility = new MoveAbility(140);

        switch (tier) {
            case "tier1":
                switch (type) {
                    case 1:

                        Sprite sprite1 = new Sprite(
                                new Texture(Gdx.files.internal("images/Enemies/Tier1/Circle/6.png")),
                                World.HEIGHT / 32, World.HEIGHT / 32
                        );
                        sprite1.setPosition(START_POS_X, START_POS_Y);

                        Texture[] texture1 = {
                                new Texture(Gdx.files.internal("images/Enemies/Tier1/Circle/1.png")),
                                new Texture(Gdx.files.internal("images/Enemies/Tier1/Circle/2.png")),
                                new Texture(Gdx.files.internal("images/Enemies/Tier1/Circle/3.png")),
                                new Texture(Gdx.files.internal("images/Enemies/Tier1/Circle/4.png")),
                                new Texture(Gdx.files.internal("images/Enemies/Tier1/Circle/5.png")),
                                new Texture(Gdx.files.internal("images/Enemies/Tier1/Circle/6.png"))

                        };

                        return new Enemy(sprite1, moveAbility, texture1, 200);

                    case 2:

                        Sprite sprite2 = new Sprite(new Texture(Gdx.files.internal("images/Enemies/Tier1/Triangle/6.png")), World.HEIGHT / 32, World.HEIGHT / 32);
                        sprite2.setPosition(START_POS_X, START_POS_Y);

                        Texture[] texture2 = {
                                new Texture(Gdx.files.internal("images/Enemies/Tier1/Triangle/1.png")),
                                new Texture(Gdx.files.internal("images/Enemies/Tier1/Triangle/2.png")),
                                new Texture(Gdx.files.internal("images/Enemies/Tier1/Triangle/3.png")),
                                new Texture(Gdx.files.internal("images/Enemies/Tier1/Triangle/4.png")),
                                new Texture(Gdx.files.internal("images/Enemies/Tier1/Triangle/5.png")),
                                new Texture(Gdx.files.internal("images/Enemies/Tier1/Triangle/6.png"))

                        };

                        return new Enemy(sprite2, moveAbility, texture2, 150);

                    case 3:

                        Sprite sprite3 = new Sprite(new Texture(Gdx.files.internal("images/Enemies/Tier1/Square/6.png")), World.HEIGHT / 32, World.HEIGHT / 32);
                        sprite3.setPosition(START_POS_X, START_POS_Y);

                        Texture[] texture3 = {
                                new Texture(Gdx.files.internal("images/Enemies/Tier1/Square/1.png")),
                                new Texture(Gdx.files.internal("images/Enemies/Tier1/Square/2.png")),
                                new Texture(Gdx.files.internal("images/Enemies/Tier1/Square/3.png")),
                                new Texture(Gdx.files.internal("images/Enemies/Tier1/Square/4.png")),
                                new Texture(Gdx.files.internal("images/Enemies/Tier1/Square/5.png")),
                                new Texture(Gdx.files.internal("images/Enemies/Tier1/Square/6.png"))

                        };

                        return new Enemy(sprite3, moveAbility, texture3, 250);

                }

            case "tier2":
                switch (type) {
                    case 1:

                        Sprite sprite1 = new Sprite(new Texture(Gdx.files.internal("images/Enemies/Tier2/Pentagon/6.png")), World.HEIGHT / 32, World.HEIGHT / 32);
                        sprite1.setPosition(START_POS_X, START_POS_Y);

                        Texture[] texture1 = {
                                new Texture(Gdx.files.internal("images/Enemies/Tier2/Pentagon/1.png")),
                                new Texture(Gdx.files.internal("images/Enemies/Tier2/Pentagon/2.png")),
                                new Texture(Gdx.files.internal("images/Enemies/Tier2/Pentagon/3.png")),
                                new Texture(Gdx.files.internal("images/Enemies/Tier2/Pentagon/4.png")),
                                new Texture(Gdx.files.internal("images/Enemies/Tier2/Pentagon/5.png")),
                                new Texture(Gdx.files.internal("images/Enemies/Tier2/Pentagon/6.png"))

                        };

                        return new Enemy(sprite1, moveAbility, texture1, 2000);

                    case 2:

                        Sprite sprite2 = new Sprite(new Texture(Gdx.files.internal("images/Enemies/Tier2/Hexagon/6.png")), World.HEIGHT / 32, World.HEIGHT / 32);
                        sprite2.setPosition(START_POS_X, START_POS_Y);

                        Texture[] texture2 = {
                                new Texture(Gdx.files.internal("images/Enemies/Tier2/Hexagon/1.png")),
                                new Texture(Gdx.files.internal("images/Enemies/Tier2/Hexagon/2.png")),
                                new Texture(Gdx.files.internal("images/Enemies/Tier2/Hexagon/3.png")),
                                new Texture(Gdx.files.internal("images/Enemies/Tier2/Hexagon/4.png")),
                                new Texture(Gdx.files.internal("images/Enemies/Tier2/Hexagon/5.png")),
                                new Texture(Gdx.files.internal("images/Enemies/Tier2/Hexagon/6.png"))

                        };

                        return new Enemy(sprite2, moveAbility, texture2, 2250);

                    case 3:

                        Sprite sprite3 = new Sprite(new Texture(Gdx.files.internal("images/Enemies/Tier2/Octagon/6.png")), World.HEIGHT / 32, World.HEIGHT / 32);
                        sprite3.setPosition(START_POS_X, START_POS_Y);

                        Texture[] texture3 = {
                                new Texture(Gdx.files.internal("images/Enemies/Tier2/Octagon/1.png")),
                                new Texture(Gdx.files.internal("images/Enemies/Tier2/Octagon/2.png")),
                                new Texture(Gdx.files.internal("images/Enemies/Tier2/Octagon/3.png")),
                                new Texture(Gdx.files.internal("images/Enemies/Tier2/Octagon/4.png")),
                                new Texture(Gdx.files.internal("images/Enemies/Tier2/Octagon/5.png")),
                                new Texture(Gdx.files.internal("images/Enemies/Tier2/Octagon/6.png"))

                        };

                        return new Enemy(sprite3, moveAbility, texture3, 2500);
                }

            case "tier3":
                switch (type) {
                    case 1:
                        Sprite sprite1 = new Sprite(new Texture(Gdx.files.internal("images/Enemies/Tier3/Star/6.png")), World.HEIGHT / 32, World.HEIGHT / 32);
                        sprite1.setPosition(START_POS_X, START_POS_Y);

                        Texture[] texture1 = {
                                new Texture(Gdx.files.internal("images/Enemies/Tier3/Star/1.png")),
                                new Texture(Gdx.files.internal("images/Enemies/Tier3/Star/2.png")),
                                new Texture(Gdx.files.internal("images/Enemies/Tier3/Star/3.png")),
                                new Texture(Gdx.files.internal("images/Enemies/Tier3/Star/4.png")),
                                new Texture(Gdx.files.internal("images/Enemies/Tier3/Star/5.png")),
                                new Texture(Gdx.files.internal("images/Enemies/Tier3/Star/6.png"))

                        };

                        return new Enemy(sprite1, moveAbility, texture1, 10000);

                    case 2:
                        Sprite sprite2 = new Sprite(new Texture(Gdx.files.internal("images/Enemies/Tier3/Cross/6.png")), World.HEIGHT / 32, World.HEIGHT / 32);
                        sprite2.setPosition(START_POS_X, START_POS_Y);

                        Texture[] texture2 = {
                                new Texture(Gdx.files.internal("images/Enemies/Tier3/Cross/1.png")),
                                new Texture(Gdx.files.internal("images/Enemies/Tier3/Cross/2.png")),
                                new Texture(Gdx.files.internal("images/Enemies/Tier3/Cross/3.png")),
                                new Texture(Gdx.files.internal("images/Enemies/Tier3/Cross/4.png")),
                                new Texture(Gdx.files.internal("images/Enemies/Tier3/Cross/5.png")),
                                new Texture(Gdx.files.internal("images/Enemies/Tier3/Cross/6.png"))

                        };

                        return new Enemy(sprite2, moveAbility, texture2, 11250);

                    case 3:
                        Sprite sprite3 = new Sprite(new Texture(Gdx.files.internal("images/Enemies/Tier3/Fidget/6.png")), World.HEIGHT / 32, World.HEIGHT / 32);
                        sprite3.setPosition(START_POS_X, START_POS_Y);

                        Texture[] texture3 = {
                                new Texture(Gdx.files.internal("images/Enemies/Tier3/Fidget/1.png")),
                                new Texture(Gdx.files.internal("images/Enemies/Tier3/Fidget/2.png")),
                                new Texture(Gdx.files.internal("images/Enemies/Tier3/Fidget/3.png")),
                                new Texture(Gdx.files.internal("images/Enemies/Tier3/Fidget/4.png")),
                                new Texture(Gdx.files.internal("images/Enemies/Tier3/Fidget/5.png")),
                                new Texture(Gdx.files.internal("images/Enemies/Tier3/Fidget/6.png"))

                        };

                        return new Enemy(sprite3, moveAbility, texture3, 12500);
                }
                return null;

            case "boss":
                switch (type) {
                    case 1:
                        Sprite sprite1 = new Sprite(new Texture(Gdx.files.internal("images/Enemies/Boss/Green.png")), World.HEIGHT / 16, World.HEIGHT / 16);
                        sprite1.setPosition(START_POS_X, START_POS_Y);

                        Texture[] texture1 = {
                                new Texture(Gdx.files.internal("images/Enemies/Boss/Green.png")),
                                new Texture(Gdx.files.internal("images/Enemies/Boss/Green.png")),
                                new Texture(Gdx.files.internal("images/Enemies/Boss/Green.png")),
                                new Texture(Gdx.files.internal("images/Enemies/Boss/Green.png")),
                                new Texture(Gdx.files.internal("images/Enemies/Boss/Green.png")),
                                new Texture(Gdx.files.internal("images/Enemies/Boss/Green.png"))

                        };

                        return new Enemy(sprite1, moveAbility, texture1, 100000);

                    case 2:
                        Sprite sprite2 = new Sprite(new Texture(Gdx.files.internal("images/Enemies/Boss/Red.png")), World.HEIGHT / 16, World.HEIGHT / 16);
                        sprite2.setPosition(START_POS_X, START_POS_Y);

                        Texture[] texture2 = {
                                new Texture(Gdx.files.internal("images/Enemies/Boss/Red.png")),
                                new Texture(Gdx.files.internal("images/Enemies/Boss/Red.png")),
                                new Texture(Gdx.files.internal("images/Enemies/Boss/Red.png")),
                                new Texture(Gdx.files.internal("images/Enemies/Boss/Red.png")),
                                new Texture(Gdx.files.internal("images/Enemies/Boss/Red.png")),
                                new Texture(Gdx.files.internal("images/Enemies/Boss/Red.png"))

                        };

                        return new Enemy(sprite2, moveAbility, texture2, 112500);

                    case 3:
                        Sprite sprite3 = new Sprite(new Texture(Gdx.files.internal("images/Enemies/Boss/Blue.png")), World.HEIGHT / 16, World.HEIGHT / 16);
                        sprite3.setPosition(START_POS_X, START_POS_Y);

                        Texture[] texture3 = {
                                new Texture(Gdx.files.internal("images/Enemies/Boss/Blue.png")),
                                new Texture(Gdx.files.internal("images/Enemies/Boss/Blue.png")),
                                new Texture(Gdx.files.internal("images/Enemies/Boss/Blue.png")),
                                new Texture(Gdx.files.internal("images/Enemies/Boss/Blue.png")),
                                new Texture(Gdx.files.internal("images/Enemies/Boss/Blue.png")),
                                new Texture(Gdx.files.internal("images/Enemies/Boss/Blue.png"))

                        };

                        return new Enemy(sprite3, moveAbility, texture3, 125000);
                }
                return null;
        }
        return null;
    }
}

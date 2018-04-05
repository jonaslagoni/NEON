package com.enemy;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.neon.libary.MoveAbility;
import com.neon.libary.Sprite;
import com.neon.libary.vectors.Vector2f;

import java.util.Arrays;

import static com.neon.libary.World.HEIGHT;

/**
 * Created by sam on 12-03-2018.
 */
public class EnemyFactory {

    private static final int START_POS_X = 960;
    private static final int START_POS_Y = 2047;
    private static final int MOVE_SPEED = 200;
    private static final int REGULAR_SIZE = HEIGHT / 32;
    private static final int BOSS_SIZE = REGULAR_SIZE * 2;
    private static final int tier1Value = 2;
    private static final int tier2Value = 10;
    private static final int tier3Value = 50;
    private static final int bossValue = 200;
    private static final int damage = 1;

    public static Enemy build(String tier, int type) {
        switch (tier) {
            case "tier1":
                switch (type) {
                    case 1:
                        return build(REGULAR_SIZE, 200, tier1Value, damage,
                                "images/Enemies/Tier1/Circle/1.png",
                                "images/Enemies/Tier1/Circle/2.png",
                                "images/Enemies/Tier1/Circle/3.png",
                                "images/Enemies/Tier1/Circle/4.png",
                                "images/Enemies/Tier1/Circle/5.png",
                                "images/Enemies/Tier1/Circle/6.png");
                    case 2:
                        return build(REGULAR_SIZE, 150, tier1Value, damage,
                                "images/Enemies/Tier1/Triangle/1.png",
                                "images/Enemies/Tier1/Triangle/2.png",
                                "images/Enemies/Tier1/Triangle/3.png",
                                "images/Enemies/Tier1/Triangle/4.png",
                                "images/Enemies/Tier1/Triangle/5.png",
                                "images/Enemies/Tier1/Triangle/6.png");
                    case 3:
                        return build(REGULAR_SIZE, 250, tier1Value, damage,
                                "images/Enemies/Tier1/Square/1.png",
                                "images/Enemies/Tier1/Square/2.png",
                                "images/Enemies/Tier1/Square/3.png",
                                "images/Enemies/Tier1/Square/4.png",
                                "images/Enemies/Tier1/Square/5.png",
                                "images/Enemies/Tier1/Square/6.png");
                }
            case "tier2":
                switch (type) {
                    case 1:
                        return build(REGULAR_SIZE, 2000, tier2Value, damage,
                                "images/Enemies/Tier2/Pentagon/1.png",
                                "images/Enemies/Tier2/Pentagon/2.png",
                                "images/Enemies/Tier2/Pentagon/3.png",
                                "images/Enemies/Tier2/Pentagon/4.png",
                                "images/Enemies/Tier2/Pentagon/5.png",
                                "images/Enemies/Tier2/Pentagon/6.png");
                    case 2:
                        return build(REGULAR_SIZE, 2250, tier2Value, damage,
                                "images/Enemies/Tier2/Hexagon/1.png",
                                "images/Enemies/Tier2/Hexagon/2.png",
                                "images/Enemies/Tier2/Hexagon/3.png",
                                "images/Enemies/Tier2/Hexagon/4.png",
                                "images/Enemies/Tier2/Hexagon/5.png",
                                "images/Enemies/Tier2/Hexagon/6.png");
                    case 3:
                        return build(REGULAR_SIZE, 2500, tier2Value, damage,
                                "images/Enemies/Tier2/Octagon/1.png",
                                "images/Enemies/Tier2/Octagon/2.png",
                                "images/Enemies/Tier2/Octagon/3.png",
                                "images/Enemies/Tier2/Octagon/4.png",
                                "images/Enemies/Tier2/Octagon/5.png",
                                "images/Enemies/Tier2/Octagon/6.png");
                }
            case "tier3":
                switch (type) {
                    case 1:
                        return build(REGULAR_SIZE, 10000, tier3Value, damage,
                                "images/Enemies/Tier3/Star/1.png",
                                "images/Enemies/Tier3/Star/2.png",
                                "images/Enemies/Tier3/Star/3.png",
                                "images/Enemies/Tier3/Star/4.png",
                                "images/Enemies/Tier3/Star/5.png",
                                "images/Enemies/Tier3/Star/6.png");
                    case 2:
                        return build(REGULAR_SIZE, 11250, tier3Value, damage,
                                "images/Enemies/Tier3/Cross/1.png",
                                "images/Enemies/Tier3/Cross/2.png",
                                "images/Enemies/Tier3/Cross/3.png",
                                "images/Enemies/Tier3/Cross/4.png",
                                "images/Enemies/Tier3/Cross/5.png",
                                "images/Enemies/Tier3/Cross/6.png");
                    case 3:
                        return build(REGULAR_SIZE, 12500, tier3Value, damage,
                                "images/Enemies/Tier3/Fidget/1.png",
                                "images/Enemies/Tier3/Fidget/2.png",
                                "images/Enemies/Tier3/Fidget/3.png",
                                "images/Enemies/Tier3/Fidget/4.png",
                                "images/Enemies/Tier3/Fidget/5.png",
                                "images/Enemies/Tier3/Fidget/6.png");
                }
                return null;
            case "boss":
                switch (type) {
                    case 1:
                        return build(BOSS_SIZE, 100000, bossValue, damage,
                                "images/Enemies/Boss/Green.png",
                                "images/Enemies/Boss/Green.png",
                                "images/Enemies/Boss/Green.png",
                                "images/Enemies/Boss/Green.png",
                                "images/Enemies/Boss/Green.png",
                                "images/Enemies/Boss/Green.png");

                    case 2:
                        return build(BOSS_SIZE, 112500, bossValue, damage,
                                "images/Enemies/Boss/Red.png",
                                "images/Enemies/Boss/Red.png",
                                "images/Enemies/Boss/Red.png",
                                "images/Enemies/Boss/Red.png",
                                "images/Enemies/Boss/Red.png",
                                "images/Enemies/Boss/Red.png");
                    case 3:
                        return build(BOSS_SIZE, 125000, bossValue, damage,
                                "images/Enemies/Boss/Blue.png",
                                "images/Enemies/Boss/Blue.png",
                                "images/Enemies/Boss/Blue.png",
                                "images/Enemies/Boss/Blue.png",
                                "images/Enemies/Boss/Blue.png",
                                "images/Enemies/Boss/Blue.png");
                }
                return null;
        }
        return null;
    }

    private static Enemy build(int size, int hp, int value, int damage, String... paths) {
        Texture[] textures = Arrays.stream(paths).map(Gdx.files::internal).map(Texture::new).toArray(Texture[]::new);
        return new Enemy(new Sprite(textures[textures.length - 1], new Vector2f(START_POS_X, START_POS_Y),
                new Vector2f(0, MOVE_SPEED), size, size), new MoveAbility(new Vector2f(0, 0), true),
                textures, hp, value, damage); // wat
    }
}

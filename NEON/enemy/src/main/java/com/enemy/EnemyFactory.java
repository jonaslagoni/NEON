package com.enemy;

import com.library.MoveAbility;
import com.library.Sprite;
import static com.library.World.HEIGHT;
import com.library.vectors.Vector2f;

/**
 * Created by sam on 12-03-2018.
 */
public class EnemyFactory {

    private static final int START_POS_X = 960;
    private static final int START_POS_Y = 2047;
    private static final int MOVE_SPEED = 200;
    private static final int REGULAR_SIZE = HEIGHT / 32;
    private static final int BOSS_SIZE = REGULAR_SIZE * 2;
    private static final int TIER1_VALUE = 2;
    private static final int TIER2_VALUE = 10;
    private static final int TIER3_VALUE = 50;
    private static final int BOSS_VALUE = 200;
    private static final int DAMAGE = 1;

    public static Enemy build(String tier, int type) {
        switch (tier) {
            case "tier1":
                switch (type) {
                    case 1:
                        return build(REGULAR_SIZE, 200, TIER1_VALUE, DAMAGE,
                                "circle1", "circle2", "circle3",
                                "circle4", "circle5", "circle6");
                    case 2:
                        return build(REGULAR_SIZE, 150, TIER1_VALUE, DAMAGE,
                                "triangle1", "triangle2", "triangle3",
                                "triangle4", "triangle5", "triangle6");
                    case 3:
                        return build(REGULAR_SIZE, 250, TIER1_VALUE, DAMAGE,
                                "square1", "square2", "square3",
                                "square4", "square5", "square6");
                }
            case "tier2":
                switch (type) {
                    case 1:
                        return build(REGULAR_SIZE, 2000, TIER2_VALUE, DAMAGE,
                                "pentagon1", "pentagon2", "pentagon3",
                                "pentagon4", "pentagon5", "pentagon6");
                    case 2:
                        return build(REGULAR_SIZE, 2250, TIER2_VALUE, DAMAGE,
                                "hexagon1", "hexagon2", "hexagon3",
                                "hexagon4", "hexagon5", "hexagon6");
                    case 3:
                        return build(REGULAR_SIZE, 2500, TIER2_VALUE, DAMAGE,
                                "octagon1", "octagon2", "octagon3",
                                "octagon4", "octagon5", "octagon6");
                }
            case "tier3":
                switch (type) {
                    case 1:
                        return build(REGULAR_SIZE, 10000, TIER3_VALUE, DAMAGE,
                                "star1", "star2", "star3",
                                "star4", "star5", "star6");
                    case 2:
                        return build(REGULAR_SIZE, 11250, TIER3_VALUE, DAMAGE,
                                "images/Enemies/Tier3/Cross/1.png",
                                "images/Enemies/Tier3/Cross/2.png",
                                "images/Enemies/Tier3/Cross/3.png",
                                "images/Enemies/Tier3/Cross/4.png",
                                "images/Enemies/Tier3/Cross/5.png",
                                "images/Enemies/Tier3/Cross/6.png");
                    case 3:
                        return build(REGULAR_SIZE, 12500, TIER3_VALUE, DAMAGE,
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
                        return build(BOSS_SIZE, 100000, BOSS_VALUE, DAMAGE,
                                "green-boss.png");

                    case 2:
                        return build(BOSS_SIZE, 112500, BOSS_VALUE, DAMAGE,
                                "red-boss");
                    case 3:
                        return build(BOSS_SIZE, 125000, BOSS_VALUE, DAMAGE,
                                "blue-boss");
                }
                return null;
        }
        return null;
    }

    private static Enemy build(int size, int hp, int value, int damage, String... textures) {
//        Texture[] textures = Arrays.stream(paths).map(Gdx.files::internal).map(Texture::new).toArray(Texture[]::new);
        return new Enemy(new Sprite(textures[textures.length - 1], new Vector2f(START_POS_X, START_POS_Y),
                new Vector2f(0, MOVE_SPEED), size, size), new MoveAbility(new Vector2f(0, 0), true),
                textures, hp, value, damage); // wat
    }
}

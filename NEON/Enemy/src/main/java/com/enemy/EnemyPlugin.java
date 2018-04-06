package com.enemy;

import com.library.GameData;
import com.library.World;
import com.library.interfaces.IAssetManager;
import com.library.interfaces.Plugin;
import java.util.Map;

import com.library.interfaces.WorldService;

public class EnemyPlugin implements Plugin {

    private WorldService world;
    private GameData gameData;
    private IAssetManager assetManager;
    private Map<String, String> map;

    public void setWorld(World world) {
        this.world = world;
    }

    public void removeWorld() {
        this.world = null;
    }

    @Override
    public void start() {

        map.put("blue-boss", "assets/blue.png");
        map.put("red-boss", "assets/red.png");
        map.put("green-boss", "assets/green.png");

        map.put("circle1", "assets/circle1.png");
        map.put("circle2", "assets/circle2.png");
        map.put("circle3", "assets/circle3.png");
        map.put("circle4", "assets/circle4.png");
        map.put("circle5", "assets/circle5.png");
        map.put("circle6", "assets/circle6.png");

        map.put("cross1", "assets/cross1.png");
        map.put("cross2", "assets/cross2.png");
        map.put("cross3", "assets/cross3.png");
        map.put("cross4", "assets/cross4.png");
        map.put("cross5", "assets/cross5.png");
        map.put("cross6", "assets/cross6.png");

        map.put("fidget1", "assets/fidget1.png");
        map.put("fidget2", "assets/fidget2.png");
        map.put("fidget3", "assets/fidget3.png");
        map.put("fidget4", "assets/fidget4.png");
        map.put("fidget5", "assets/fidget5.png");
        map.put("fidget6", "assets/fidget6.png");

        map.put("hexagon1", "assets/hexagon1.png");
        map.put("hexagon2", "assets/hexagon2.png");
        map.put("hexagon3", "assets/hexagon3.png");
        map.put("hexagon4", "assets/hexagon4.png");
        map.put("hexagon5", "assets/hexagon5.png");
        map.put("hexagon6", "assets/hexagon6.png");

        map.put("pentagon1", "assets/pentagon1.png");
        map.put("pentagon2", "assets/pentagon2.png");
        map.put("pentagon3", "assets/pentagon3.png");
        map.put("pentagon4", "assets/pentagon4.png");
        map.put("pentagon5", "assets/pentagon5.png");
        map.put("pentagon6", "assets/pentagon6.png");

        map.put("square1", "assets/square1.png");
        map.put("square2", "assets/square2.png");
        map.put("square3", "assets/square3.png");
        map.put("square4", "assets/square4.png");
        map.put("square5", "assets/square5.png");
        map.put("square6", "assets/square6.png");

        map.put("star1", "assets/star1.png");
        map.put("star2", "assets/star2.png");
        map.put("star3", "assets/star3.png");
        map.put("star4", "assets/star4.png");
        map.put("star5", "assets/star5.png");
        map.put("star6", "assets/star6.png");

        map.put("triangle1", "assets/triangle1.png");
        map.put("triangle2", "assets/triangle2.png");
        map.put("triangle3", "assets/triangle3.png");
        map.put("triangle4", "assets/triangle4.png");
        map.put("triangle5", "assets/triangle5.png");
        map.put("triangle6", "assets/triangle6.png");

        /* Load assets */
        map.forEach((k, v) -> assetManager.loadAsset(k, v));
    }

    @Override
    public void stop() {
        map.keySet().forEach(assetManager::unloadAsset);
        world.getEntities(Enemy.class).forEach(world::removeEntity);
    }
}

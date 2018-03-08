package com.neon.tower;

import com.neon.libary.GameData;
import com.neon.libary.World;
import com.neon.libary.interfaces.Controller;
import com.neon.libary.interfaces.IWeaponService;

public class TowerController implements Controller {

    private World world;
    private IWeaponService weaponService;

    public TowerController(World world, GameData gameData) {
        this.world = world;
        this.weaponService = gameData.getService(IWeaponService.class);
    }

    @Override
    public void update() {
        world.getEntities(Tower.class).forEach(this::updateTower);
    }

    private void updateTower(Tower tower) {

        float angle = 0;

        weaponService.fire(tower.getWeapon(), tower.getSprite().getPosition(), angle);
    }
}

package com.neon.tower;

import com.neon.libary.World;
import com.neon.libary.interfaces.Entity;
import com.neon.libary.interfaces.INeonWallet;
import com.neon.libary.interfaces.ITowerService;
import com.neon.libary.vectors.Vector2f;
import com.neon.weapon.Weapon;

public class TowerService implements ITowerService {

    private INeonWallet neonWallet;
    private World world;

    TowerService(World world, INeonWallet neonWallet) {
        this.neonWallet = neonWallet;
        this.world = world;
    }

    @Override
    public void upgrade(Entity entity) {
        if (!(entity instanceof Tower)) return;
        Tower tower = (Tower) entity;
        if (tower.level >= tower.maxLevel || !neonWallet.subtractCoins(tower.getCost()))
            return;
        tower.level++;
    }

    @Override
    public void placeTower(Vector2f pos, String key) {
        Tower tower = TowerFactory.build(key);
        if (tower == null) return;
        if (neonWallet.subtractCoins(tower.getCost())) {
            world.setGridCell(pos, tower);
            world.addEntity(new Weapon(512, tower.sprite.getPosition())); // Todo
        }
    }

}

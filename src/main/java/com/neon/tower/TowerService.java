package com.neon.tower;

import com.neon.libary.World;
import com.neon.libary.interfaces.Entity;
import com.neon.libary.interfaces.INeonService;
import com.neon.libary.interfaces.ITowerService;
import com.neon.libary.vectors.Vector2f;
import com.neon.weapon.Weapon;

class TowerService implements ITowerService {

    private INeonService neonWallet;
    private World world;

    TowerService(World world, INeonService neonWallet) {
        this.neonWallet = neonWallet;
        this.world = world;
    }

    @Override
    public void upgrade(Entity entity) {
        if (!(entity instanceof Tower)) {
            return;
        }
        Tower tower = (Tower) entity;
        if (tower.level >= tower.maxLevel || !neonWallet.subtractCoins(tower.getCost())) {
            return;
        }
        tower.level++;
    }

    @Override
    public void placeTower(Vector2f pos, String key) {
        String shotType;
        Tower tower = TowerFactory.build(key);
        if (tower == null) {
            return;
        }
        if (neonWallet.subtractCoins(tower.getCost())) {
            world.setGridCell(pos, tower);

            switch (key) {
                case "laser-tower":
                    shotType = "green_beam";
                    world.addEntity(new Weapon(512, tower.sprite.getPosition(), shotType)); // Todo
                    break;

                case "melee-glaive-tower":
                    shotType = "yellow_beam";
                    world.addEntity(new Weapon(512, tower.sprite.getPosition(), shotType)); // Todo
                    break;

                case "pea-shooter":
                    shotType = "blue_beam";
                    world.addEntity(new Weapon(512, tower.sprite.getPosition(), shotType)); // Todo
                    break;

                case "range-powerup":
                    shotType = "yellow_beam";
                    world.addEntity(new Weapon(512, tower.sprite.getPosition(), shotType)); // Todo
                    break;

                case "strenght-powerup":
                    shotType = "red_beam";
                    world.addEntity(new Weapon(512, tower.sprite.getPosition(), shotType)); // Todo
                    break;

                case "railgun-tower":
                    shotType = "pink_laser";
                    world.addEntity(new Weapon(512, tower.sprite.getPosition(), shotType)); // Todo
                    break;

                case "rocket-tower":
                    shotType = "rocket_shot";
                    world.addEntity(new Weapon(512, tower.sprite.getPosition(), shotType)); // Todo
                    break;

                case "splash-tower":
                    shotType = "green_bomb_smaller";
                    world.addEntity(new Weapon(512, tower.sprite.getPosition(), shotType)); // Todo
                    break;

            }
            
            shotType = "";
        }
    }

}

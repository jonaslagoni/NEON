package com.neon.tower;

import com.neon.libary.ShotType;
import com.neon.libary.TowerType;
import com.neon.libary.World;
import com.neon.libary.interfaces.Entity;
import com.neon.libary.interfaces.INeonService;
import com.neon.libary.interfaces.ITowerService;
import com.neon.libary.interfaces.WeaponEntity;
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
        if (tower.getLevel() >= tower.maxLevel || !neonWallet.subtractCoins(tower.getCost())) {
            return;
        }
        tower.increaseLevel();
        switch (tower.getTowerType()) {
            case LASER_TOWER:
                switch (tower.getLevel()) {
                    case 2:
                        tower.getWeapon().affectWeapon(20, 10, 0.08f);
                        break;
                    case 3:
                        tower.getWeapon().affectWeapon(40, 20, 0.12f);
                        break;
                    case 4:
                        tower.getWeapon().affectWeapon(80, 30, 0.15f);
                        break;
                }
                break;
            case MELEE_GLAIVE_TOWER:
                switch (tower.getLevel()) {
                    case 2:
                        tower.getWeapon().affectWeapon(30, 5, 0.08f);
                        break;
                    case 3:
                        tower.getWeapon().affectWeapon(60, 10, 0.12f);
                        break;
                    case 4:
                        tower.getWeapon().affectWeapon(120, 15, 0.15f);
                        break;
                }
                break;
            case PEA_SHOOTER:
                switch (tower.getLevel()) {
                    case 2:
                        tower.getWeapon().affectWeapon(40, 15, 0.05f);
                        break;
                    case 3:
                        tower.getWeapon().affectWeapon(80, 20, 0.08f);
                        break;
                    case 4:
                        tower.getWeapon().affectWeapon(160, 25, 0.12f);
                        break;
                }
                break;
            case RANGE_POWERUP:
                switch (tower.getLevel()) {
                    case 2:
                        tower.getWeapon().affectWeapon(0, 20, 0.08f);
                        break;
                }
                break;
            case STRENGTH_POWERUP:
                switch (tower.getLevel()) {
                    case 2:
                        tower.getWeapon().affectWeapon(100, 0, 0.08f);
                        break;
                }
                break;
            case RAILGUN_TOWER:
                switch (tower.getLevel()) {
                    case 2:
                        tower.getWeapon().affectWeapon(5, 20, 0.10f);
                        break;
                    case 3:
                        tower.getWeapon().affectWeapon(10, 40, 0.13f);
                        break;
                    case 4:
                        tower.getWeapon().affectWeapon(20, 60, 0.17f);
                        break;
                }
                break;
            case ROCKET_TOWER:
                switch (tower.getLevel()) {
                    case 2:
                        tower.getWeapon().affectWeapon(50, 10, 0.04f);
                        break;
                    case 3:
                        tower.getWeapon().affectWeapon(100, 20, 0.06f);
                        break;
                    case 4:
                        tower.getWeapon().affectWeapon(150, 30, 0.08f);
                        break;
                }
                break;
            case SPLASH_TOWER:
                switch (tower.getLevel()) {
                    case 2:
                        tower.getWeapon().affectWeapon(50, 10, 0.06f);
                        break;
                    case 3:
                        tower.getWeapon().affectWeapon(85, 20, 0.08f);
                        break;
                    case 4:
                        tower.getWeapon().affectWeapon(125, 30, 0.10f);
                        break;
                }
                break;
        }

    }

    @Override
    public void placeTower(Vector2f pos, TowerType key) {
        Tower tower = TowerFactory.build(key);
        if (tower == null) {
            return;
        }
        if (neonWallet.subtractCoins(tower.getCost())) {
            world.setGridCell(pos, tower);
            switch (key) {
                case LASER_TOWER:
                    WeaponEntity weapon = new Weapon(512, tower.sprite.getPosition(), ShotType.GREEN_BEAM, 10);
                    tower.setWeapon(weapon);
                    world.addEntity(weapon);
                    break;

                case MELEE_GLAIVE_TOWER:
                    WeaponEntity meleeWeapon = new Weapon(512, tower.sprite.getPosition(), ShotType.YELLOW_BEAM, 10);
                    tower.setWeapon(meleeWeapon);
                    world.addEntity(meleeWeapon);
                    break;

                case PEA_SHOOTER:
                    WeaponEntity peaWeapon = new Weapon(512, tower.sprite.getPosition(), ShotType.BLUE_BEAM, 10);
                    tower.setWeapon(peaWeapon);
                    world.addEntity(peaWeapon);
                    break;

                case RANGE_POWERUP:
                    WeaponEntity rangePowerup = new Weapon(512, tower.sprite.getPosition(), ShotType.YELLOW_BEAM, 10);
                    tower.setWeapon(rangePowerup);
                    world.addEntity(rangePowerup);
                    break;

                case STRENGTH_POWERUP:
                    WeaponEntity strengthPowerup = new Weapon(512, tower.sprite.getPosition(), ShotType.RED_BEAM, 10);
                    tower.setWeapon(strengthPowerup);
                    world.addEntity(strengthPowerup);
                    break;

                case RAILGUN_TOWER:
                    WeaponEntity railWeapon = new Weapon(512, tower.sprite.getPosition(), ShotType.PINK_LASER, 10);
                    tower.setWeapon(railWeapon);
                    world.addEntity(railWeapon);
                    break;

                case ROCKET_TOWER:
                    WeaponEntity rocketWeapon = new Weapon(512, tower.sprite.getPosition(), ShotType.ROCKET_SHOT, 10);
                    tower.setWeapon(rocketWeapon);
                    world.addEntity(rocketWeapon);
                    break;

                case SPLASH_TOWER:
                    WeaponEntity splashWeapon = new Weapon(512, tower.sprite.getPosition(), ShotType.GREEN_BOMB_SMALLER, 10);
                    tower.setWeapon(splashWeapon);
                    world.addEntity(splashWeapon);
                    break;

            }
        }
    }

}

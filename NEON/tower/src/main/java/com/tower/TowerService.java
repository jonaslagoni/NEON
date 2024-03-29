package com.tower;

import com.library.ShotType;
import com.library.interfaces.*;
import com.library.vectors.Vector2f;

public class TowerService implements ITowerService, ILocalTowerService {

    private INeonService neonWallet;
    private IWorldService world;
    private IWeaponFactory weaponFactory;

    public void setNeonWallet(INeonService neonWallet) {
        this.neonWallet = neonWallet;
    }

    public void removeNeonWallet(INeonService neonWallet) {
        this.neonWallet = null;
    }

    public void setWorld(IWorldService world) {
        this.world = world;
    }

    public void removeWorld(IWorldService world) {
        this.world = null;
    }

    public void setWeaponFactory(IWeaponFactory weaponFactory) {
        this.weaponFactory = weaponFactory;
    }

    public void removeWeaponFactory(IWeaponFactory weaponFactory) {
        this.weaponFactory = null;
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
    public void place(Vector2f pos, TowerType key) {
        Tower tower = TowerFactory.build(key);
        if (tower == null) {
            return;
        }
        if (neonWallet != null && !neonWallet.subtractCoins(tower.getCost())) {
            return;
        }
        world.setGridCell(pos, tower);
        if (weaponFactory != null) {
            switch (key) {
                case LASER_TOWER:
                    WeaponEntity weapon = weaponFactory.newWeapon(512, tower.sprite.getPosition(), ShotType.GREEN_BEAM, 10);
                    tower.setWeapon(weapon);
                    world.addEntity(weapon);
                    break;

                case MELEE_GLAIVE_TOWER:
                    WeaponEntity meleeWeapon = weaponFactory.newWeapon(512, tower.sprite.getPosition(), ShotType.YELLOW_BEAM, 10);
                    tower.setWeapon(meleeWeapon);
                    world.addEntity(meleeWeapon);
                    break;

                case PEA_SHOOTER:
                    WeaponEntity peaWeapon = weaponFactory.newWeapon(512, tower.sprite.getPosition(), ShotType.BLUE_BEAM, 10);
                    tower.setWeapon(peaWeapon);
                    world.addEntity(peaWeapon);
                    break;

                case RANGE_POWERUP:
                    WeaponEntity rangePowerup = weaponFactory.newWeapon(512, tower.sprite.getPosition(), ShotType.YELLOW_BEAM, 10);
                    tower.setWeapon(rangePowerup);
                    world.addEntity(rangePowerup);
                    break;

                case STRENGTH_POWERUP:
                    WeaponEntity strengthPowerup = weaponFactory.newWeapon(512, tower.sprite.getPosition(), ShotType.RED_BEAM, 10);
                    tower.setWeapon(strengthPowerup);
                    world.addEntity(strengthPowerup);
                    break;

                case RAILGUN_TOWER:
                    WeaponEntity railWeapon = weaponFactory.newWeapon(512, tower.sprite.getPosition(), ShotType.PINK_LASER, 10);
                    tower.setWeapon(railWeapon);
                    world.addEntity(railWeapon);
                    break;

                case ROCKET_TOWER:
                    WeaponEntity rocketWeapon = weaponFactory.newWeapon(512, tower.sprite.getPosition(), ShotType.ROCKET_SHOT, 10);
                    tower.setWeapon(rocketWeapon);
                    world.addEntity(rocketWeapon);
                    break;

                case SPLASH_TOWER:
                    WeaponEntity splashWeapon = weaponFactory.newWeapon(512, tower.sprite.getPosition(), ShotType.GREEN_BOMB_SMALLER, 10);
                    tower.setWeapon(splashWeapon);
                    world.addEntity(splashWeapon);
                    break;

            }
        }
    }
}

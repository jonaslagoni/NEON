package com.tower;

import com.library.TowerType;
import com.library.interfaces.Entity;
import com.library.interfaces.INeonService;
import com.library.interfaces.ITowerService;
import com.library.interfaces.IWorldService;
import com.library.vectors.Vector2f;

public class TowerService implements ITowerService {

    private INeonService neonWallet;
    private IWorldService world;

    public void setNeonWallet(INeonService neonWallet) {
        this.neonWallet = neonWallet;
    }

    public void removeNeonWallet() {
        this.neonWallet = null;
    }

    public void setWorld(IWorldService world) {
        this.world = world;
    }

    public void removeWorld() {
        this.world = null;
    }
    public TowerService(){
        
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
        if(neonWallet != null){
            if (neonWallet.subtractCoins(tower.getCost())) {
                world.setGridCell(pos, tower);
            }
        }else{
            world.setGridCell(pos, tower);
        }
    }
}

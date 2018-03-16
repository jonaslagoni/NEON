package com.neon.tower;

import com.neon.libary.GameData;
import com.neon.libary.World;
import com.neon.libary.interfaces.Entity;
import com.neon.libary.interfaces.INeonWallet;
import com.neon.libary.interfaces.ITowerService;
import com.neon.libary.vectors.Vector2f;

public class TowerService implements ITowerService {

    private GameData gameData;
    private INeonWallet neonWallet;
    private World world;
    
    public TowerService(GameData gameData, World world) {
        this.gameData = gameData;
        this.neonWallet = gameData.getService(INeonWallet.class);
        this.world = world;
    }
    
    @Override
    public void upgrade(Entity entity) {
        if (!(entity instanceof Tower)) return;
        Tower tower = (Tower) entity;
        if(tower.level < tower.maxLevel) {
            tower.level++;
            tower.sprite.setTexture(tower.texture[tower.level]);
        }
    }

    
    public Entity build(String key) {
        return TowerFactory.build(key);
    }

    @Override
    public void placeTower(Vector2f pos, String key) {
        Tower tower = TowerFactory.build(key);
        if(neonWallet.subtractCoins(tower.getCost())){
            world.setGridCell(pos, tower);
        }
    }

}

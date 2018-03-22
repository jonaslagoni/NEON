package com.neon.tower;

import com.neon.libary.TowerType;
import com.badlogic.gdx.graphics.Texture;
import com.neon.libary.Sprite;
import com.neon.libary.interfaces.Drawable;
import com.neon.libary.interfaces.WeaponEntity;

class Tower implements Drawable {

    Sprite sprite;
    Texture[] textures;
    int level = 1;
    @SuppressWarnings("WeakerAccess")
    int cost;
    int maxLevel;
    WeaponEntity weapon;
    TowerType towerType;
    
    Tower(Sprite sprite, Texture[] textures, int cost, int maxLevel, TowerType towerType) {
        this.sprite = sprite;
        this.textures = textures;
        this.cost = cost;
        this.maxLevel = maxLevel;
        this.towerType = towerType;
        
    }

    @Override
    public Sprite getSprite() {
        return sprite;
    }


    public int getCost() {
        return cost * level;
    }
    
    public int getLevel(){
        return level;
    }
    
    public void increaseLevel(){
        level++;
    }
    
    public TowerType getTowerType(){
        return towerType;
    }
    
    public WeaponEntity getWeapon(){
        return weapon;
    }
    
    public void setWeapon(WeaponEntity weapon){
        this.weapon = weapon;
    }
}

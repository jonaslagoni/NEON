package com.neon.enemy;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.neon.libary.MoveAbility;
import com.neon.libary.Sprite;
import com.neon.libary.World;
import com.neon.libary.interfaces.Drawable;
import com.neon.libary.interfaces.Moveable;

/**
 * Created by sam on 02-03-2018.
 */
public class Enemy implements Moveable, Drawable {

    private MoveAbility moveAbility;
    private Sprite sprite;
    private Texture[] texture;
    private int hp = 200;
    private int maxHp = hp;

    Enemy(Sprite sprite, MoveAbility moveAbility, Texture[] texture) {
        this.moveAbility = moveAbility;
        this.sprite = sprite;
        this.texture = texture;
    }

    @Override
    public MoveAbility getMoveAbility() {
        return moveAbility;
    }

    @Override
    public Sprite getSprite() {
        return sprite;

    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
        if(hp<=maxHp*0.9){
            sprite.setTexture(texture[4]);
        }
        if(hp<=maxHp*0.7){
            sprite.setTexture(texture[3]);
        }
        if(hp<=maxHp*0.5){
            sprite.setTexture(texture[2]);
        }
        if(hp<=maxHp*0.3){
            sprite.setTexture(texture[1]);
        }
        if(hp<=maxHp*0.1){
            sprite.setTexture(texture[0]);
        }        
    }


}

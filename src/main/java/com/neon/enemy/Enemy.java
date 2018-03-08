package com.neon.enemy;

import com.badlogic.gdx.graphics.Texture;
import com.neon.libary.MoveAbility;
import com.neon.libary.Sprite;
import com.neon.libary.interfaces.Drawable;
import static com.neon.libary.interfaces.Entity.typeIdentifier.ENEMY;
import com.neon.libary.interfaces.Moveable;

/**
 * Created by sam on 02-03-2018.
 */
public class Enemy implements Moveable, Drawable {

    private MoveAbility moveAbility;
    private Sprite sprite;
    private typeIdentifier type;
    private Texture[] texture;
    private int hp;

    Enemy(Sprite sprite, MoveAbility moveAbility, Texture[] texture) {
        type = ENEMY;
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


    @Override
    public typeIdentifier getType() {
        return type;

    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }
    
   
}

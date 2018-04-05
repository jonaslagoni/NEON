package com.enemy;

import com.badlogic.gdx.graphics.Texture;
import com.library.MoveAbility;
import com.library.Sprite;
import com.library.interfaces.DamageAble;
import com.library.interfaces.Drawable;
import com.library.interfaces.Moveable;
import com.library.interfaces.Targetable;
import com.library.vectors.Vector2f;

import java.util.Queue;

/**
 * Created by sam on 02-03-2018.
 */
class Enemy implements Moveable, Drawable, Targetable, DamageAble {

    MoveAbility moveAbility;
    Sprite sprite;
    Texture[] textures;
    int hp;
    int maxHp;
    float damageTimer;
    int coinValue;
    int damage;
    Queue<Vector2f> path;

    Enemy(Sprite sprite,
            MoveAbility moveAbility,
            Texture[] textures,
            int hp,
            int coinValue,
            int damage) {
        this.moveAbility = moveAbility;
        this.sprite = sprite;
        this.textures = textures;
        this.hp = hp;
        this.maxHp = hp;
        this.coinValue = coinValue;
        this.damage = damage;
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
    public void setDamage(float damage) {
        if (hp - damage <= maxHp) {
            hp -= damage;
        }
    }
}

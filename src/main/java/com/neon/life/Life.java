/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.neon.life;

import com.neon.libary.interfaces.ILifeService;

/**
 *
 * @author Ejer
 */
public class Life implements ILifeService {
    
    private int life = 20;
    
    @Override
    public int subtracLife(int i) {
        life -= i;
        return life;
    }

    @Override
    public int getLife() {
        return life;
    }

    @Override
    public void setLife() {

    }

}

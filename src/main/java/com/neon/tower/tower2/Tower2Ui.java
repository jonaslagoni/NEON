/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.neon.tower.tower2;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.neon.tower.Tower;

/**
 *
 * @author Lagoni
 */
public class Tower2Ui extends Tower {
    public Tower2Ui(BitmapFont font, Skin skin, String drawable) {
        super("Tower2", new TextButton.TextButtonStyle(skin.getDrawable("up-" + drawable), skin.getDrawable("down-" + drawable), skin.getDrawable("checked-" + drawable), font));
        
        super.setWidth(125);
        super.setHeight(25);
    }
}

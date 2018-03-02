/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.neon.tower;

import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

/**
 *
 * @author Lagoni
 */
public class Tower extends TextButton{
    public Tower (String text, Skin skin) {
        super(text, skin);
    }

    public Tower (String text, Skin skin, String styleName) {
        super(text, skin, styleName);
    }

    public Tower (String text, TextButtonStyle style) {
        super(text, style);
    }
}

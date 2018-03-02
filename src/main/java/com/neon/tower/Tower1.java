package com.neon.tower;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;

public class Tower1 extends Tower{
    
    public Tower1(BitmapFont font, Skin skin, String drawable) {
        super("Tower1", new TextButton.TextButtonStyle(skin.getDrawable(drawable), skin.getDrawable(drawable), skin.getDrawable(drawable), font));
        
        super.setWidth(75);
        super.setHeight(75);
    }

}

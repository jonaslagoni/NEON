package com.neon.tower;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.neon.main.entities.*;

public class Tower1{
    private Actor actor;
    

    public Tower1(BitmapFont font, Skin skin, String drawable) {
        
        TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.font = font;
        textButtonStyle.up = skin.getDrawable(drawable);
        TextButton button = new TextButton("Tower1", textButtonStyle);
        button.addListener(new EventListener() {
            @Override
            public boolean handle(Event event) {
                System.out.println("Does this");
                return false;
            }
        });
        button.setWidth(75);
        button.setHeight(75);
        actor = button;
    }

    /**
     * @return the actor
     */
    public Actor getActor() {
        return actor;
    }

}

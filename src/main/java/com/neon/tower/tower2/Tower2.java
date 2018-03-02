package com.neon.tower.tower2;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.neon.tower.Tower;

public class Tower2 extends Tower {

    public Tower2(BitmapFont font, Skin skin, String drawable) {
        super("", new TextButton.TextButtonStyle(skin.getDrawable("up-" + drawable), skin.getDrawable("down-" + drawable), skin.getDrawable("checked-" + drawable), font));
        super.setWidth(75);
        super.setHeight(75);
    }

}

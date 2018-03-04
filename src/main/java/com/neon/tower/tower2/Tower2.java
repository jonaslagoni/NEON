package com.neon.tower.tower2;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.neon.main.World;
import com.neon.tower.Tower;

@SuppressWarnings("WeakerAccess")
public class Tower2 extends Tower {

    public Tower2(BitmapFont font, Skin skin, String drawable) {
        super("", new TextButton.TextButtonStyle(skin.getDrawable("up-" + drawable), skin.getDrawable("down-" + drawable), skin.getDrawable("checked-" + drawable), font));
        super.setWidth(World.GRID_CELL_SIZE);
        super.setHeight(World.GRID_CELL_SIZE);
    }
}

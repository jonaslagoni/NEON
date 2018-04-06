package com.engine;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class Neon extends Game {

    SpriteBatch batch;
    Skin skin;

    @Override
    public void create() {
        skin = new Skin(Gdx.files.internal("skin.json"), new TextureAtlas(Gdx.files.internal("assets/assets.atlas")));
        batch = new SpriteBatch();
        this.setScreen(new MainMenuScreen(this));
    }

    @Override
    public void resize(int width, int height) {
    }

    /**
     * Main Loop that runs before each frame
     */
    @Override
    public void render() {
        super.render();
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void dispose() {
        batch.dispose();
    }

    public void endGame() {
        this.setScreen(new EndScreen(this));
    }
}


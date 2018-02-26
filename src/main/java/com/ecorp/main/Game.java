package com.ecorp.main;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;

import java.util.ArrayList;
import java.util.List;

public class Game implements ApplicationListener {

    private final List<Entity> entities = new ArrayList<>();
    private final Entity[][] grid = new Entity[16][16];         // 2D array

    private SpriteBatch batch;
    private ShapeRenderer shapeRenderer;

    @Override
    public void create() {

        /* Sprite batch is used to render sprites on the gpu */
        batch = new SpriteBatch();
        shapeRenderer = new ShapeRenderer();

    }

    @Override
    public void resize(int width, int height) {
    }

    /**
     * Main Loop that runs before each frame
     */
    @Override
    public void render() {
        draw();
    }

    private void draw() {
        /* Clear screen*/
        Gdx.gl.glClear(GL30.GL_COLOR_BUFFER_BIT);

        /* Draw grid */
        int gap = Gdx.graphics.getHeight() / grid.length;
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.setColor(Color.GREEN);
        for (int i = 1; i < grid.length; i++) {
            shapeRenderer.line(i * gap, 0, i * gap, grid.length * gap); // Vertical
            shapeRenderer.line(0, i * gap, grid.length * gap, i * gap); // Horizontal

        }
        shapeRenderer.end();

        /* Render all entities to screen*/
        batch.begin();
        entities.forEach(this::draw);
        batch.end();
    }

    /**
     * Draw a single entity to the screen
     *
     * @param entity Entity to be drawn
     */
    private void draw(Entity entity) {
        Texture texture = entity.getTexture();
        batch.draw(
                texture,                               // Texture
                entity.getX() - texture.getWidth() / 2,     // Position x
                entity.getY() - texture.getHeight() / 2,    // Position y
                texture.getWidth() / 2,                // Offset by x to ensure centring
                texture.getHeight() / 2,               // Offset by y to ensure centring
                texture.getWidth(),                    // Texture Width
                texture.getHeight(),                   // Texture Height
                1, 1,                                         // Texture scaling
                entity.getRotation() * MathUtils.radDeg, // Rotation
                0, 0,                                         // Position of texture in source texture
                texture.getWidth(),                    // Source Width
                texture.getHeight(),                   // Source Height
                false, false                                  // Flip
        );
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
}

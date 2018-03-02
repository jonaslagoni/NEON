 package com.neon.main;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.neon.main.entities.Drawable;
import com.neon.main.entities.Position;
import com.neon.player.PlayerPlugin;
import com.neon.ui.TowerController;
import com.neon.ui.UI;
import com.neon.ui.UiInputProcessor;

public class Game implements ApplicationListener {

    private GameData gameData;
    private UI ui;
    private World world;
    private SpriteBatch batch;
    private ShapeRenderer shapeRenderer;

    @Override
    public void create() {

        gameData = new GameData();
        ui = new UI(gameData);
        world = new World();

        /* Sprite batch is used to render sprites on the gpu */
        batch = new SpriteBatch();
        shapeRenderer = new ShapeRenderer();

        Plugin playerPlugin = new PlayerPlugin();
        playerPlugin.start(gameData, world);

        gameData.addController(new MoveController());
        gameData.addInputProcessor(new UiInputProcessor(ui));
    }

    @Override
    public void resize(int width, int height) {
    }

    /**
     * Main Loop that runs before each frame
     */
    @Override
    public void render() {
        for (Controller controller : gameData.getControllers()) {
            controller.update(world);
        }
        draw();
    }

    private void draw() {
        /* Clear screen*/
        Gdx.gl.glClear(GL30.GL_COLOR_BUFFER_BIT);

        /* Draw grid */
        int gap = Gdx.graphics.getHeight() / world.getGridLength();
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.setColor(Color.GREEN);
        for (int i = 1; i < world.getGridLength(); i++) {
            shapeRenderer.line(i * gap, 0, i * gap, world.getGridLength() * gap); // Vertical
            shapeRenderer.line(0, i * gap, world.getGridLength() * gap, i * gap); // Horizontal

        }
        shapeRenderer.end();

        TowerController.getInstance().getStage().draw();
        /* Render all entities to screen*/
        batch.begin();
        for (Drawable entity : world.getEntities(Drawable.class)) {
            draw(entity);
        }
        batch.end();

        ui.draw();
    }

    /**
     * Draw a single entity to the screen
     *
     * @param entity Entity to be drawn
     */
    private void draw(Drawable entity) {
        Texture texture = entity.getTexture();
        Position position = entity.getPosition();
        Vector2 vector = position.getVector();
        batch.draw(
                texture,                               // Texture
                vector.x - texture.getWidth() / 2,     // Position x
                vector.y - texture.getHeight() / 2,    // Position y
                texture.getWidth() / 2,                // Offset by x to ensure centring
                texture.getHeight() / 2,               // Offset by y to ensure centring
                texture.getWidth(),                    // Texture Width
                texture.getHeight(),                   // Texture Height
                1, 1,                                         // Texture scaling
                position.getRotation() * MathUtils.radDeg + 90, // Rotation
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

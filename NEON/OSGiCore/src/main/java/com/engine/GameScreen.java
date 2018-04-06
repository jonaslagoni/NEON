package com.engine;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.library.GameData;
import com.library.Sprite;
import com.library.World;
import com.library.interfaces.Drawable;

import static com.badlogic.gdx.math.MathUtils.radDeg;
import com.library.interfaces.Controller;
import com.library.interfaces.Plugin;
import static com.library.vectors.VectorUtils.angle;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class GameScreen implements Screen {

    private final Neon game;
    private final World world = new World();
    private final OrthographicCamera camera = new OrthographicCamera();
    private final Viewport viewport = new ExtendViewport(World.WIDTH, World.HEIGHT, camera);
    private Texture bg;
    private GameData gameData;
    private boolean speedUp;
    private final List<Controller> entityProcessorList = new CopyOnWriteArrayList<>();
    private final List<Plugin> gamePluginList = new CopyOnWriteArrayList<>();

    GameScreen(final Neon game) {
        this.game = game;
        gameData = new GameData(game.skin, viewport);
        Gdx.input.setInputProcessor(gameData.getMultiplexer());

        bg = new Texture(Gdx.files.internal("images/background2048.png"));
        
        gameData.addInputProcessor(new InputProcessor() {
            @Override
            public boolean keyDown(int keycode) {
                switch (keycode) {
                    case Input.Keys.SPACE:
                        speedUp = !speedUp;
                        return true;
                }
                return false;
            }

            @Override
            public boolean keyUp(int keycode) {
                return false;
            }

            @Override
            public boolean keyTyped(char character) {
                return false;
            }

            @Override
            public boolean touchDown(int screenX, int screenY, int pointer, int button) {
                return false;
            }

            @Override
            public boolean touchUp(int screenX, int screenY, int pointer, int button) {
                return false;
            }

            @Override
            public boolean touchDragged(int screenX, int screenY, int pointer) {
                return false;
            }

            @Override
            public boolean mouseMoved(int screenX, int screenY) {
                return false;
            }

            @Override
            public boolean scrolled(int amount) {
                return false;
            }
        });

    }

    private void drawEntity(Drawable drawable) {
        Sprite sprite = drawable.getSprite();
        Texture texture = sprite.getTexture();
        game.batch.draw(
                texture, //The texture to use
                sprite.getPosition().getX() - texture.getWidth() / 2, //Position x to draw
                sprite.getPosition().getY() - texture.getHeight() / 2, //Position y to draw
                texture.getWidth() / 2,
                texture.getHeight() / 2,
                texture.getWidth(),
                texture.getHeight(),
                sprite.getWidth() / texture.getWidth(),
                sprite.getHeight() / texture.getHeight(),
                angle(sprite.getVelocity()) * radDeg,
                0, 0,
                texture.getWidth(),
                texture.getHeight(),
                false, false
        );
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

        entityProcessorList.forEach(controller -> controller.update(speedUp ? delta * 2 : delta));

        /* Clear screen*/
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        viewport.apply();

        camera.update();
        game.batch.setProjectionMatrix(camera.combined);

        game.batch.begin();

        /* Draw background */
        game.batch.draw(bg, 0, 0, World.WIDTH, World.HEIGHT);

        /* Draw all entities to screen*/
        world.getEntities(Drawable.class).forEach(this::drawEntity);
        game.batch.end();


    }
    
    public void addEntityProcessingService(Controller eps) {
        this.entityProcessorList.add(eps);
    }

    public void removeEntityProcessingService(Controller eps) {
        this.entityProcessorList.remove(eps);
    }

    public void addGamePluginService(Plugin plugin) {
        this.gamePluginList.add(plugin);
        plugin.start();

    }

    public void removeGamePluginService(Plugin plugin) {
        this.gamePluginList.remove(plugin);
        plugin.stop();
    }


    @Override
    public void resize(int width, int height) {

        viewport.update(width, height);
        camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0);

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}

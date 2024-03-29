package com.engine;

import com.badlogic.gdx.*;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.library.Sprite;
import com.library.interfaces.*;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

import static com.badlogic.gdx.math.MathUtils.radDeg;
import static com.library.vectors.VectorUtils.angle;

public class GameScreen implements ApplicationListener, IObserver {

    private final static OrthographicCamera CAMERA = new OrthographicCamera();
    final static Viewport VIEWPORT = new ExtendViewport(IWorldService.WIDTH, IWorldService.HEIGHT, CAMERA);
    private final List<Controller> entityProcessorList = new CopyOnWriteArrayList<>();
    private final List<Plugin> gamePluginList = new CopyOnWriteArrayList<>();
    private Map<String, Texture> textureMap = new HashMap<>();
    private IAssetManager assetManager;
    private IWorldService world;
    private IGameData gameData;
    private SpriteBatch batch;
    private HUD hud;
    private boolean speedUp;


    @SuppressWarnings("ResultOfObjectAllocationIgnored")
    public void init() {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
//        config.setFromDisplayMode(LwjglApplicationConfiguration.getDesktopDisplayMode());
        config.height = 768;
        config.width = 1024;
        config.resizable = false;
        new LwjglApplication(this, config);
    }

    /**
     * Draws an entity, if that entity has a texture attached to it.
     * <p>
     * if the entity does not have a texture, nothing will be drawn.
     *
     * @param drawable Drawable entity
     */
    private void drawEntity(Drawable drawable) {
        Sprite sprite = drawable.getSprite();
        Texture texture = textureMap.get(sprite.getTexture());
        if (texture == null) {
            return;
        }
        batch.draw(
                texture, // The texture to use
                sprite.getPosition().getX() - texture.getWidth() / 2, // Position x to draw
                sprite.getPosition().getY() - texture.getHeight() / 2, // Position y to draw
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
    public void render() {

        float delta = Gdx.graphics.getDeltaTime();
        entityProcessorList.forEach(controller -> controller.update(speedUp ? delta * 2 : delta));
        hud.update(delta);

        /* Clear screen*/
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        VIEWPORT.apply();

        CAMERA.update();
        batch.setProjectionMatrix(CAMERA.combined);

        batch.begin();

        /* Draw background */
        batch.draw(textureMap.get("bg"), 0, 0, IWorldService.WIDTH, IWorldService.HEIGHT);

        /* Draw all entities to screen*/
        if (world != null)
            world.getEntities(Drawable.class).forEach(this::drawEntity);

        batch.end();

        hud.getStage().getViewport().apply();
        hud.getStage().draw();
    }

    @Override
    public void resize(int width, int height) {
        VIEWPORT.update(width, height, true);
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

    @Override
    public void create() {

        try (InputStream stream = getClass().getClassLoader().getResourceAsStream("background2048.png")) {
            assetManager.loadAsset("bg", stream);
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        batch = new SpriteBatch();
        hud = new HUD(batch, new BitmapFont(), world, gameData);
        hud.start();

        InputMultiplexer multiplexer = new InputMultiplexer();
        Gdx.input.setInputProcessor(multiplexer);
        multiplexer.addProcessor(hud);
        multiplexer.addProcessor(hud.getStage());
        multiplexer.addProcessor(new InputProcessor() {
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
        assetManager.addObserver(this);
        update();
    }

    public void setWorld(IWorldService world) {
        this.world = world;
    }

    public void removeWorld(IWorldService world) {
        this.world = null;
    }

    public void setGameData(IGameData gameData) {
        this.gameData = gameData;
    }

    public void removeGameData(IGameData gameData) {
        this.gameData = null;
    }

    public void setAssetManager(IAssetManager assetManager) {
        this.assetManager = assetManager;
    }

    public void removeAssetManager(IAssetManager assetManager) {
        this.assetManager = null;
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
    public void update() {
        Map<String, Texture> newTextureMap = new HashMap<>();
        assetManager.getTextures().forEach((name, bytes) -> {
            if (textureMap.containsKey(name)) {
                Texture texture = this.textureMap.get(name);
                newTextureMap.put(name, texture);
            } else {
                Gdx.app.postRunnable(() -> {
                    Pixmap pixmap = new Pixmap(bytes, 0, bytes.length);
                    Texture texture = new Texture(pixmap);
                    newTextureMap.put(name, texture);
                });
            }
        });
        textureMap = newTextureMap;
    }
}

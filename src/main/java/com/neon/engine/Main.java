package com.neon.engine;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.badlogic.gdx.tools.texturepacker.TexturePacker;
import com.badlogic.gdx.tools.texturepacker.TexturePacker.Settings;

public class Main {
    public static void main(String[] args) {
        Settings settings = new Settings();
        settings.maxWidth = 2048;
        settings.maxHeight = 2048;
        TexturePacker.process(settings, "./images", "./assets", "assets");

        Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
        // config.setFullscreenMode(Lwjgl3ApplicationConfiguration.getDisplayMode());
        config.setWindowedMode(1024, 768);
        //config.setResizable(false);
        new Lwjgl3Application(new Game(), config);
    }
}
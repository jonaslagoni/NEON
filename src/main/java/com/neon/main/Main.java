package com.neon.main;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.badlogic.gdx.tools.texturepacker.TexturePacker;
import com.badlogic.gdx.tools.texturepacker.TexturePacker.Settings;

public class Main {
    public static void main(String[] args) {
        Settings settings = new Settings();
        settings.maxWidth = 512;
        settings.maxHeight = 512;
        TexturePacker.process(settings, "./images", "./assets", "assets");

        Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
        //config.setFullscreenMode(Lwjgl3ApplicationConfiguration.getDisplayMode());
        new Lwjgl3Application(new Game(), config);
    }
}
package com.neosoft;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;

public class Launcher {
    public static void main (String[] args) {
            new LwjglApplication(ThreeDecks.getInstance(), "Hello World!", 480, 320, false);
    }
}

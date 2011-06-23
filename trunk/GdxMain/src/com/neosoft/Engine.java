package com.neosoft;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;

public abstract class Engine implements ApplicationListener {

	private Screen screen;
	
	private int screenWidth;
	private int screenHeight;
	
	final public void changeScreen(Screen screen) {
		if (null == screen || this.screen == screen) {
			return;
		}
		
		if (null != this.screen) {
			this.screen.pause();
			this.screen.dispose();	
		}
				
		this.screen = screen;
		this.screen.initialize();
		this.screen.resize(this.screenWidth, this.screenHeight);
	}
	
	public abstract Screen getInitialScreen();
	
	public abstract void initialize();

	@Override
	final public void create() {
		this.initialize();
		
		this.screen = this.getInitialScreen();
		this.screen.initialize();
	}

	@Override
	final public void resume() {
		this.screen.resume();
	}

	@Override
	final public void render() {
		float deltaTime = Gdx.graphics.getDeltaTime();
		
		this.screen.update(deltaTime);
		this.screen.render(deltaTime);
	}

	@Override
	final public void resize(int width, int height) {
		this.screenWidth = width;
		this.screenHeight = height;
		
		this.screen.resize(width, height);
	}

	@Override
	final public void pause() {
		this.screen.pause();
	}

	@Override
	final public void dispose() {
		this.screen.dispose();
	}

}

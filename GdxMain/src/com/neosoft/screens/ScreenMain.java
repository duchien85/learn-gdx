package com.neosoft.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.SpriteCache;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.MoveTo;
import com.badlogic.gdx.scenes.scene2d.actors.Image;
import com.neosoft.Screen;
import com.neosoft.utils.CachedTileBuilder;

public class ScreenMain extends Screen {
	
	private final SpriteCache bgCache = new SpriteCache();
	private final SpriteBatch batch = new SpriteBatch();
	private final BitmapFont font = new BitmapFont();
	
	private int bgCacheID;
	
	private final Stage stage = new Stage(0, 0, false);
	
	private Image[] cards;
	
	private final TextureAtlas atlas = new TextureAtlas(Gdx.files.internal("pack"), Gdx.files.internal(""));;

	@Override
	public void initialize() {
				
		AtlasRegion cardRegion = this.atlas.findRegion("card");
		
		final int num = 60;
		
		this.cards = new Image[num];
		
		for (int i = 0; i < num; i++) {
			this.cards[i] = new Image("card_" + i, cardRegion);
			
			this.cards[i].originX = this.cards[i].x = 10 + 15 * i;
			this.cards[i].originY = this.cards[i].y = 0;
			
			this.stage.addActor(this.cards[i]);
		}
		
		Gdx.input.setInputProcessor(this);
	}
	
	@Override
	public void update(float deltaTime) {
		this.stage.act(deltaTime);
	}

	@Override
	public void render(float deltaTime) {
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
				
		this.bgCache.begin();
		this.bgCache.draw(this.bgCacheID);
		this.bgCache.end();
		
		batch.begin();
			font.draw(batch, "FPS : " + Gdx.graphics.getFramesPerSecond(), 20, 200);		
		batch.end();
		
		this.stage.draw();
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}	
	
	@Override
	public void resize(int width, int height) {
		super.resize(width, height);
		
		TextureRegion bgTexRegion = this.atlas.findRegion("background");
		this.bgCacheID = CachedTileBuilder.build(this.bgCache, bgTexRegion, width, height);
		
		this.stage.setViewport(width, height, false);
	}

	@Override
	public boolean touchDown(int x, int y, int pointer, int button) {
		
		boolean touched = this.stage.touchDown(x, y, pointer, button);
		
		if (touched) {
			Actor actor = this.stage.getLastTouchedChild();
			
			if (null != actor) {
				System.out.println("" + actor.name);	
				
				if (actor.y == actor.originY) {
					actor.action(MoveTo.$(actor.originX, actor.originY + 12, 0.1f));
				} else {
					actor.action(MoveTo.$(actor.originX, actor.originY, 0.1f));
				}
			}
		}
		
		return true;
	}

}

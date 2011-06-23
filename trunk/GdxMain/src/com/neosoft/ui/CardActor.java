package com.neosoft.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.actors.Image;

public class CardActor extends Image {
	
	private static final TextureAtlas atlas = new TextureAtlas(Gdx.files.internal("pack"), Gdx.files.internal("packed/"));
	private static final TextureRegion cardBgRegion = atlas.findRegion("card-bg");
	
	private static final int ICON_WIDTH = 11;
	private static final int ICON_HEIGHT = 24;
	
	private static final int ICON_MARGIN_LEFT = 4;
	private static final int ICON_MARGIN_TOP = 4;
	
	public CardActor(String name) {
		super(name, cardBgRegion);
	}

	@Override
	protected void draw(SpriteBatch batch, float parentAlpha) {
		super.draw(batch, parentAlpha);
		
		// TextureRegion iconRegion = atlas.findRegion("card-spade-1");
		// batch.draw(iconRegion, this.x + ICON_MARGIN_LEFT, this.y + cardBgRegion.getRegionHeight() - ICON_MARGIN_TOP - ICON_HEIGHT, ICON_WIDTH, ICON_HEIGHT);
	}	

}

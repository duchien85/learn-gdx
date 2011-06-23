package com.neosoft;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.neosoft.model.CardRank;
import com.neosoft.model.CardSuit;

public class CardFactory {
	
	private static final TextureAtlas atlas = new TextureAtlas(Gdx.files.internal("pack"), Gdx.files.internal("packed/"));
	
	private static final CharSequence[] spadeIcons = {
		"card-spade-3",
		"card-spade-4",
		"card-spade-5",
		"card-spade-6",
		"card-spade-7",
		"card-spade-8",
		"card-spade-9",
		"card-spade-10",
		"card-spade-j",
		"card-spade-q",
		"card-spade-k",
		"card-spade-1",
		"card-spade-2",
	};
	
	public static Actor makeCard(CardSuit suit, CardRank rank) {
		if (CardRank.JOKER == rank) {
			return null;
		}
		
		if (CardSuit.SPADES != suit) {
			return null;
		}
		
		AtlasRegion cardRegion = atlas.findRegion("card-bg");
		AtlasRegion iconRegion = atlas.findRegion(spadeIcons[rank.ordinal()].toString());
		
		// Pixmap pixmap = new Pixmap (cardRegion.getRegionWidth(), cardRegion.getRegionHeight(), Pixmap.Format.RGBA8888);
		
		return null;
	}
}

package com.neosoft.utils;

import com.badlogic.gdx.graphics.g2d.SpriteCache;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public final class CachedTileBuilder {
	
	private CachedTileBuilder() {
		
	}
	
	/*
	 * @return cache ID
	 */
	public static int build(SpriteCache bgCache, TextureRegion texRegion, int width, int height) {
		int x = 0;
		int y = 0;		

		bgCache.clear();
		bgCache.beginCache();

		while (y < height) {

			while (x < width) {
				bgCache.add(texRegion, x, y);

				x += texRegion.getRegionWidth();
			}

			x = 0;
			y += texRegion.getRegionHeight();
		}

		return bgCache.endCache();
	}
}

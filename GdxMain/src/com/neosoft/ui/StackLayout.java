package com.neosoft.ui;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;

public class StackLayout extends Group {
	
	private int x;
	private int y;
	// private int cardWidth;
	// private int cardHeight;
	private int cardGap;

	public StackLayout(String name, int x, int y, /*int cardWidth, int cardHeight,*/ int cardGap) {
		super(name);
		
		this.setLayoutParam(x, y, cardGap);
	}
	
	protected void updateLayout () {
		int numActors = getActors().size();
		
		if (0 >= numActors) {
			return;
		}
		
		final int cardWidth = (int) getActors().get(0).width;
		
		float leftMost = this.x - (cardWidth + (numActors - 1) * this.cardGap) / 2;
		
		for (int i = 0; i < numActors; i++) {
			Actor actor = getActors().get(i);
			actor.originX = actor.x = leftMost + this.cardGap * i;
			actor.originY = actor.y = this.y;			
		}
	}
	
	public void setLayoutParam(int x, int y, int cardGap) {
		this.x = x;
		this.y = y;
		this.cardGap = cardGap;
		
		this.updateLayout();
	}

	@Override
	public void addActor(Actor actor) {
		super.addActor(actor);
		this.updateLayout();
	}

	@Override
	public void removeActor(Actor actor) {
		super.removeActor(actor);
		this.updateLayout();
	}	
}

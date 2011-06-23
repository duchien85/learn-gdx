package com.neosoft;

import com.neosoft.screens.ScreenMain;

public class ThreeDecks extends Engine {
	
	private static final ThreeDecks INSTANCE = new ThreeDecks();
	
	private ThreeDecks() {
		
	}
	
	public static ThreeDecks getInstance() {
		return INSTANCE;
	}
	
	@Override
	public Screen getInitialScreen() {
		return new ScreenMain();
	}

	@Override
	public void initialize() {
		// TODO Auto-generated method stub
		
	}
	
}

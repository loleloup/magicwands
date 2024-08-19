package com.magicwands.playermana;

public interface PlayerMana {
		
	default int getMaxMana() {
		return 0;
	}
	
	default int getMana() {
		return 0;
	}
	

	default void setMana(int newValue) {
		return;
	}
}

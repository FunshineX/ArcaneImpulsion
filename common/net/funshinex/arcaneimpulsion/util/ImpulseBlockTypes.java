package net.funshinex.arcaneimpulsion.util;

public enum ImpulseBlockTypes {
	NONE(0),
	COBBLE(1), 
	IRON(4), 
	GOLD(10), 
	DIAMOND(32), 
	EMERALD(64),
	ARCANE(256);
	
	private int value;
	private ImpulseBlockTypes(int value) {
		this.value = value;
	}
	
	public int GetValue() {
		return value;
	}
}

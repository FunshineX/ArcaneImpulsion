package net.funshinex.arcaneimpulsion.client.keybind;

import java.util.EnumSet;

import net.minecraft.client.settings.KeyBinding;
import cpw.mods.fml.client.registry.KeyBindingRegistry.KeyHandler;
import cpw.mods.fml.common.TickType;

public class KeyBindWrenchMode extends KeyHandler {

	private EnumSet<TickType> tickTypes = EnumSet.of(TickType.CLIENT);
	public static boolean keyPressed = false;
	public static boolean keyHasBeenPressed = false;
	
	public KeyBindWrenchMode(KeyBinding[] keyBindings, boolean[] repeatings) {
		super(keyBindings, repeatings);
	}

	@Override
	public String getLabel() {
		return "Toggle Arcane Wrench Mode";
	}

	@Override
	public void keyDown(EnumSet<TickType> types, KeyBinding kb,	boolean tickEnd, boolean isRepeat) {
		keyHasBeenPressed = true;
		
	}

	@Override
	public void keyUp(EnumSet<TickType> types, KeyBinding kb, boolean tickEnd) {
		if(keyHasBeenPressed) {
			keyHasBeenPressed = false;
			keyPressed = true;
		}
		
	}

	@Override
	public EnumSet<TickType> ticks() {
		return tickTypes;
	}

}

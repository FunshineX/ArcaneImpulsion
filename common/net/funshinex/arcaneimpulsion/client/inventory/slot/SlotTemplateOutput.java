package net.funshinex.arcaneimpulsion.client.inventory.slot;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class SlotTemplateOutput extends Slot {

	public SlotTemplateOutput(IInventory inventory, int id, int x, int y) {
		super(inventory, id, x, y);
	}
	
	@Override
	public boolean isItemValid(ItemStack stack) {
		return false;
	}

}

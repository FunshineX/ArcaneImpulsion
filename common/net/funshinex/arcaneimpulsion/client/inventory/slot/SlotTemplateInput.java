package net.funshinex.arcaneimpulsion.client.inventory.slot;

import net.funshinex.arcaneimpulsion.item.Items;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class SlotTemplateInput extends Slot {

	public SlotTemplateInput(IInventory inventory, int id, int x, int y) {
		super(inventory, id, x, y);
	}
	
	@Override
	public boolean isItemValid(ItemStack stack) {
		return stack.itemID == Items.arcaneTemplate.itemID;
	}

}

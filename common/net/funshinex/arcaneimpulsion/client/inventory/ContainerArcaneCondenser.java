package net.funshinex.arcaneimpulsion.client.inventory;

import net.funshinex.arcaneimpulsion.client.inventory.slot.SlotOutput;
import net.funshinex.arcaneimpulsion.client.inventory.slot.SlotTemplateInput;
import net.funshinex.arcaneimpulsion.tileentity.TileEntityArcaneCondenser;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ContainerArcaneCondenser extends Container{

	TileEntityArcaneCondenser arcaneCondenser;
	
	public ContainerArcaneCondenser(InventoryPlayer invPlayer, TileEntityArcaneCondenser arcaneCondenser) {
		this.arcaneCondenser = arcaneCondenser;
		
		for (int x=0; x<9; x++) {
			addSlotToContainer(new Slot(invPlayer, x, 8+18*x, 142));
		}
		
		for (int y=0; y<3; y++) {
			for (int x=0; x<9; x++) {
				addSlotToContainer(new Slot(invPlayer, 9+x+9*y, 8+18*x, 84+18*y));
			}
		}
		
		addSlotToContainer(new SlotTemplateInput(arcaneCondenser, 0, 48, 48));
		addSlotToContainer(new SlotOutput(arcaneCondenser, 1, 106, 48));
	}
		
	@Override
	public boolean canInteractWith(EntityPlayer entityplayer) {
		return arcaneCondenser.isUseableByPlayer(entityplayer);
	}
	
	@Override
	public ItemStack transferStackInSlot(EntityPlayer player, int i) {
		Slot slot = getSlot(i);
		
		if (slot != null && slot.getHasStack()) {
			ItemStack stack = slot.getStack();
			ItemStack result = stack.copy();
			
			if (i>=36) {
				if(!mergeItemStack(stack, 0, 36, false)) {
					return null;
				}
			}
			else
				return null;
			
			if (stack.stackSize == 0) {
				slot.putStack(null);
			}
			else {
				slot.onSlotChanged();
			}
			
			slot.onPickupFromSlot(player, stack);
			
			return result;
		}
		
		return null;
	}
	
	@Override
	public void addCraftingToCrafters(ICrafting player) {
		super.addCraftingToCrafters(player);
		
		player.sendProgressBarUpdate(this, 0, arcaneCondenser.getNearbyStorage());
		
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void updateProgressBar(int id, int data) {
		arcaneCondenser.setNearbyStorage(data);
	}
	
	@Override
	public void detectAndSendChanges() {
		super.detectAndSendChanges();
		
		for (Object player : crafters) {
			((ICrafting)player).sendProgressBarUpdate(this, 0, arcaneCondenser.getNearbyStorage());
		}
	}

}

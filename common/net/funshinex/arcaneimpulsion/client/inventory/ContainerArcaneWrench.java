package net.funshinex.arcaneimpulsion.client.inventory;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ContainerArcaneWrench extends Container{

	InventoryArcaneWrench invArcaneWrench;
	
	public ContainerArcaneWrench(InventoryPlayer invPlayer, InventoryArcaneWrench invWrench) {
		
		this.invArcaneWrench = invWrench;
		
		for (int x=0; x<9; x++) {
			addSlotToContainer(new Slot(invPlayer, x, 8+18*x, 142));
		}
		
		for (int y=0; y<3; y++) {
			for (int x=0; x<9; x++) {
				addSlotToContainer(new Slot(invPlayer, 9+x+9*y, 8+18*x, 84+18*y));
			}
		}	
		
		addSlotToContainer(new Slot(invWrench, 0, 80, 35));
		
	}
		
	@Override
	public boolean canInteractWith(EntityPlayer entityplayer) {
		return true;
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
		
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void updateProgressBar(int id, int data) {
		
	}
	
	@Override
	public void detectAndSendChanges() {
		super.detectAndSendChanges();
		
		for (Object player : crafters) {
			
		}
		
	}
	
	public void saveToNBT(ItemStack stack) {
		if (!stack.hasTagCompound()) {
			stack.setTagCompound(new NBTTagCompound());
		}
		
		invArcaneWrench.writeToNBT(stack.getTagCompound());
	}
	

}

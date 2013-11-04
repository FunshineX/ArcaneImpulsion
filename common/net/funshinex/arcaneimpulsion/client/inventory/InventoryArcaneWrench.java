package net.funshinex.arcaneimpulsion.client.inventory;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class InventoryArcaneWrench implements IInventory {

	private ItemStack inventory;
	
	public InventoryArcaneWrench(ItemStack stack) {
		if (!stack.hasTagCompound()) {
			stack.stackTagCompound = new NBTTagCompound();
		}
		
		readFromNBT(stack.getTagCompound());
	}
	
	@Override
	public int getSizeInventory() {
		return 1;
	}

	@Override
	public ItemStack getStackInSlot(int slot) {
		return inventory;
	}

	@Override
	public ItemStack decrStackSize(int slot, int amount) {
		ItemStack returnStack = inventory;
		inventory = null;
		onInventoryChanged();

		return returnStack;
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int slot) {
		ItemStack returnStack = inventory;
		inventory = null;
		return returnStack;
	}

	@Override
	public void setInventorySlotContents(int slot, ItemStack stack) {
		inventory = stack;
	}

	@Override
	public String getInvName() {
		return "ArcaneWrench";
	}

	@Override
	public boolean isInvNameLocalized() {
		return false;
	}

	@Override
	public int getInventoryStackLimit() {
		return 1;
	}

	@Override
	public void onInventoryChanged() {
		
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer entityplayer) {
		return true;
	}

	@Override
	public void openChest() {
	
	}

	@Override
	public void closeChest() {
		
	}

	@Override
	public boolean isItemValidForSlot(int i, ItemStack itemstack) {
		return true;
	}
	
	public void readFromNBT(NBTTagCompound compound) {
		
		NBTTagCompound item= ((NBTTagCompound)compound.getTag("Item"));
		if (item== null) {
			return;
		}
		
		inventory = ItemStack.loadItemStackFromNBT(item);
		
	}
	
	public void writeToNBT(NBTTagCompound compound) {
		NBTTagCompound item = new NBTTagCompound();
		if (inventory != null) {
			inventory.writeToNBT(item);
		}
		
		compound.setTag("Item", item);
	}
}

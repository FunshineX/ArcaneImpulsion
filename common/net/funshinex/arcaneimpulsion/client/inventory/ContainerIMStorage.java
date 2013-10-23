package net.funshinex.arcaneimpulsion.client.inventory;

import net.funshinex.arcaneimpulsion.tileentity.TileEntityIMStorage;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ContainerIMStorage extends Container{

	TileEntityIMStorage imStorage;
	
	public ContainerIMStorage(InventoryPlayer invPlayer, TileEntityIMStorage imStorage) {
		this.imStorage = imStorage;
	}
		
	@Override
	public boolean canInteractWith(EntityPlayer entityplayer) {
		return true;
	}
	
	@Override
	public void addCraftingToCrafters(ICrafting player) {
		super.addCraftingToCrafters(player);
		
		player.sendProgressBarUpdate(this, 0, imStorage.getInternalStorage());
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void updateProgressBar(int id, int data) {
		imStorage.setInternalStorage(data);
	}
	
	@Override
	public void detectAndSendChanges() {
		super.detectAndSendChanges();
		
		for (Object player : crafters) {
			((ICrafting)player).sendProgressBarUpdate(this, 0, imStorage.getInternalStorage());
		}		
	}

}

package net.funshinex.arcaneimpulsion.client.interfaces;

import net.funshinex.arcaneimpulsion.tileentity.TileEntityArcaneCollector;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ContainerArcaneCollector extends Container{

	TileEntityArcaneCollector arcaneCollector;
	
	public ContainerArcaneCollector(InventoryPlayer invPlayer, TileEntityArcaneCollector arcaneCollector) {
		this.arcaneCollector = arcaneCollector;
	}
		
	@Override
	public boolean canInteractWith(EntityPlayer entityplayer) {
		return true;
	}
	
	@Override
	public void addCraftingToCrafters(ICrafting player) {
		super.addCraftingToCrafters(player);
		
		player.sendProgressBarUpdate(this, 0, arcaneCollector.getInternalStorage());
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void updateProgressBar(int id, int data) {
		arcaneCollector.setInternalStorage(data);
	}
	
	@Override
	public void detectAndSendChanges() {
		super.detectAndSendChanges();
		
		for (Object player : crafters) {
			((ICrafting)player).sendProgressBarUpdate(this, 0, arcaneCollector.getInternalStorage());
		}
		
	}

}

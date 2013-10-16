package net.funshinex.arcaneimpulsion.client.interfaces;

import net.funshinex.arcaneimpulsion.tileentity.TileEntityArcaneCondenser;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ContainerArcaneCondenser extends Container{

	TileEntityArcaneCondenser arcaneCondenser;
	
	public ContainerArcaneCondenser(InventoryPlayer invPlayer, TileEntityArcaneCondenser arcaneCondenser) {
		this.arcaneCondenser = arcaneCondenser;
	}
		
	@Override
	public boolean canInteractWith(EntityPlayer entityplayer) {
		return true;
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

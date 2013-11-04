package net.funshinex.arcaneimpulsion.client.inventory;

import net.funshinex.arcaneimpulsion.ArcaneImpulsion;
import net.funshinex.arcaneimpulsion.tileentity.TileEntityArcaneCollector;
import net.funshinex.arcaneimpulsion.tileentity.TileEntityArcaneCondenser;
import net.funshinex.arcaneimpulsion.tileentity.TileEntityArcaneExtractor;
import net.funshinex.arcaneimpulsion.tileentity.TileEntityIMStorage;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.InventoryBasic;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.IGuiHandler;
import cpw.mods.fml.common.network.NetworkRegistry;

public class GuiHandler implements IGuiHandler {

	public GuiHandler() {
		NetworkRegistry.instance().registerGuiHandler(ArcaneImpulsion.class, this);
	}
	
	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world,int x, int y, int z) {
		TileEntity te = null;
		switch(ID) {
		case 0:
			te = world.getBlockTileEntity(x, y, z);
			if (te!=null && te instanceof TileEntityArcaneCollector) {
				return new ContainerArcaneCollector(player.inventory, (TileEntityArcaneCollector)te);
			}
			break;
		case 1:
			te = world.getBlockTileEntity(x, y, z);
			if (te!=null && te instanceof TileEntityArcaneExtractor) {
				return new ContainerArcaneExtractor(player.inventory, (TileEntityArcaneExtractor)te);
			}
			break;
		case 2:
			te = world.getBlockTileEntity(x, y, z);
			if (te!=null && te instanceof TileEntityArcaneCondenser) {
				return new ContainerArcaneCondenser(player.inventory, (TileEntityArcaneCondenser)te);
			}
			break;
		case 3:
			te = world.getBlockTileEntity(x, y, z);
			if (te!=null && te instanceof TileEntityIMStorage) {
				return new ContainerIMStorage(player.inventory, (TileEntityIMStorage)te);
			}
		case 4:
			ItemStack stack = player.getCurrentEquippedItem();
			return new ContainerArcaneWrench(player.inventory, new InventoryArcaneWrench(stack));
		}
		
		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world,	int x, int y, int z) {
		TileEntity te = null;
		
		switch(ID) {
		case 0:
			te = world.getBlockTileEntity(x, y, z);
			if (te!=null && te instanceof TileEntityArcaneCollector) {
				return new GuiArcaneCollector(player.inventory, (TileEntityArcaneCollector)te);
			}
			break;
		case 1:
			te = world.getBlockTileEntity(x, y, z);
			if (te!=null && te instanceof TileEntityArcaneExtractor) {
				return new GuiArcaneExtractor(player.inventory, (TileEntityArcaneExtractor)te);
			}
			break;
		case 2:
			te = world.getBlockTileEntity(x, y, z);
			if (te!=null && te instanceof TileEntityArcaneCondenser) {
				return new GuiArcaneCondenser(player.inventory, (TileEntityArcaneCondenser)te);
			}
			break;
		case 3:
			te = world.getBlockTileEntity(x, y, z);
			if (te!=null && te instanceof TileEntityIMStorage) {
				return new GuiIMStorage(player.inventory, (TileEntityIMStorage)te);
			}
			break;
		case 4:
			ItemStack stack = player.getCurrentEquippedItem();
			return new GuiArcaneWrench(player.inventory, new InventoryArcaneWrench(stack));
		}
		
		return null;
	}

}

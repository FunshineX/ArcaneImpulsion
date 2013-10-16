package net.funshinex.arcaneimpulsion.block;

import net.funshinex.arcaneimpulsion.ArcaneImpulsion;
import net.funshinex.arcaneimpulsion.tileentity.TileEntityIMStorage;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.FMLNetworkHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockIMStorage extends BlockContainer {

	protected BlockIMStorage(int id) {
		super(id, Material.iron);
		
		setCreativeTab(CreativeTabs.tabRedstone);
		setHardness(50.0F);
		setResistance(10.0F);
		setStepSound(Block.soundMetalFootstep);

		setUnlocalizedName(BlockInfo.IM_STORAGE_UNLOCALIZED_NAME);
	}
	
	@SideOnly(Side.CLIENT)
	private Icon topIcon;
	
	@SideOnly(Side.CLIENT)
	private Icon sideIcon;
	
	@SideOnly(Side.CLIENT)
	private Icon bottomIcon;
	
	@SideOnly(Side.CLIENT)
	@Override
	public void registerIcons(IconRegister register) {
		topIcon = register.registerIcon(BlockInfo.TEXTURE_LOCATION + ":" + BlockInfo.IM_STORAGE_TOP);
		sideIcon = register.registerIcon(BlockInfo.TEXTURE_LOCATION + ":" + BlockInfo.IM_STORAGE_SIDE);
		bottomIcon = register.registerIcon(BlockInfo.TEXTURE_LOCATION + ":" + BlockInfo.IM_STORAGE_BOTTOM);
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public Icon getIcon(int side, int meta) {
		
		if (side == 1) {
			return topIcon;
		} else {
			return sideIcon;
		}
	}

	@Override
	public TileEntity createNewTileEntity(World world) {
		return new TileEntityIMStorage();
	}
	
	@Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
        if(!world.isRemote) {
        	FMLNetworkHandler.openGui(player, ArcaneImpulsion.instance, 3, world, x, y, z);
        	
        }
        return true;
    }

}

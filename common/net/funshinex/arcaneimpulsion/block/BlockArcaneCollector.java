package net.funshinex.arcaneimpulsion.block;

import net.funshinex.arcaneimpulsion.ArcaneImpulsion;
import net.funshinex.arcaneimpulsion.tileentity.TileEntityArcaneCollector;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.FMLNetworkHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockArcaneCollector extends BlockContainer {

	protected BlockArcaneCollector(int id) {
		super(id, Material.iron);

        setCreativeTab(ArcaneImpulsion.tabArcaneImpulsion);
		setHardness(50.0F);
		setResistance(10.0F);
		setStepSound(Block.soundMetalFootstep);

		setUnlocalizedName(BlockInfo.ARCANE_COLLECTOR_UNLOCALIZED_NAME);
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
		topIcon = register.registerIcon(BlockInfo.TEXTURE_LOCATION + ":" + BlockInfo.ARCANE_COLLECTOR_TOP);
		sideIcon = register.registerIcon(BlockInfo.TEXTURE_LOCATION + ":" + BlockInfo.ARCANE_COLLECTOR_SIDE);
		bottomIcon = register.registerIcon(BlockInfo.TEXTURE_LOCATION + ":" + BlockInfo.ARCANE_COLLECTOR_BOTTOM);
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
		return new TileEntityArcaneCollector();
	}
	
	@Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
        if(!world.isRemote) {
        	FMLNetworkHandler.openGui(player, ArcaneImpulsion.instance, 0, world, x, y, z);
        	
        }
        return true;
    }

}

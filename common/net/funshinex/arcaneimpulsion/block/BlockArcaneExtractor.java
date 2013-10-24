package net.funshinex.arcaneimpulsion.block;

import net.funshinex.arcaneimpulsion.ArcaneImpulsion;
import net.funshinex.arcaneimpulsion.tileentity.TileEntityArcaneExtractor;
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

public class BlockArcaneExtractor  extends BlockContainer {

	protected BlockArcaneExtractor(int id) {
		super(id, Material.iron);

        setCreativeTab(ArcaneImpulsion.tabArcaneImpulsion);
		setHardness(50.0F);
		setResistance(10.0F);
		setStepSound(Block.soundMetalFootstep);

		setUnlocalizedName(BlockInfo.ARCANE_EXTRACTOR_UNLOCALIZED_NAME);
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
		topIcon = register.registerIcon(BlockInfo.TEXTURE_LOCATION + ":" + BlockInfo.ARCANE_EXTRACTOR_TOP);
		sideIcon = register.registerIcon(BlockInfo.TEXTURE_LOCATION + ":" + BlockInfo.ARCANE_EXTRACTOR_SIDE);
		bottomIcon = register.registerIcon(BlockInfo.TEXTURE_LOCATION + ":" + BlockInfo.ARCANE_EXTRACTOR_BOTTOM);
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
		return new TileEntityArcaneExtractor();
	}
	
	@Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
        if(!world.isRemote) {
        	FMLNetworkHandler.openGui(player, ArcaneImpulsion.instance, 1, world, x, y, z);
        	
        }
        return true;
    }

}
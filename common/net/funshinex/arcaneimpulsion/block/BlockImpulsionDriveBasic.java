package net.funshinex.arcaneimpulsion.block;

import net.funshinex.arcaneimpulsion.ArcaneImpulsion;
import net.funshinex.arcaneimpulsion.tileentity.TileEntityImpulsionDriveBasic;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockImpulsionDriveBasic extends BlockContainer {

	public BlockImpulsionDriveBasic(int id) {
		super(id, Material.iron);

        setCreativeTab(ArcaneImpulsion.tabArcaneImpulsion);
		setHardness(50.0F);
		setResistance(10.0F);
		setStepSound(Block.soundMetalFootstep);

		setUnlocalizedName(BlockInfo.IMPULSION_DRIVE_BASIC_UNLOCALIZED_NAME);
	}
	
	@SideOnly(Side.CLIENT)
	private Icon topIcon;
	
	@SideOnly(Side.CLIENT)
	private Icon activeTopIcon;
	
	@SideOnly(Side.CLIENT)
	private Icon sideIcon;
	
	@SideOnly(Side.CLIENT)
	private Icon bottomIcon;
	
	@SideOnly(Side.CLIENT)
	@Override
	public void registerIcons(IconRegister register) {
		topIcon = register.registerIcon(BlockInfo.TEXTURE_LOCATION + ":" + BlockInfo.IMPULSION_DRIVE_BASIC_TOP);
		activeTopIcon = register.registerIcon(BlockInfo.TEXTURE_LOCATION + ":" + BlockInfo.IMPULSION_DRIVE_BASIC_TOP_ACTIVE);
		sideIcon = register.registerIcon(BlockInfo.TEXTURE_LOCATION + ":" + BlockInfo.IMPULSION_DRIVE_BASIC_SIDE);
		bottomIcon = register.registerIcon(BlockInfo.TEXTURE_LOCATION + ":" + BlockInfo.IMPULSION_DRIVE_BASIC_BOTTOM);
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public Icon getIcon(int side, int meta) {
		
		if (side == 0) {
            return bottomIcon;
        } else if (side == 1) {
            return isActivated(meta) ? activeTopIcon : topIcon;
        } else {
            return sideIcon;
        }
	}
	
	@Override
	public void onBlockClicked(World world, int x, int y, int z, EntityPlayer player) {
		if (!world.isRemote) {
			
			TileEntityImpulsionDriveBasic teImpulsionDriveBasic = (TileEntityImpulsionDriveBasic)world.getBlockTileEntity(x, y, z);
			teImpulsionDriveBasic.doImpulse();
		}
	}
	
	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int par6, float par7, float par8, float par9)
    {
		if (!world.isRemote) {
			System.out.println("activated");
		}
		return true;
    }

	@Override
	public TileEntity createNewTileEntity(World world) {
		return new TileEntityImpulsionDriveBasic();
	}
	
	private boolean isActivated(int meta) {
        return meta == 1;
    }

}

package net.funshinex.arcaneimpulsion.block;

import net.funshinex.arcaneimpulsion.tileentity.TileEntityImpulsionDriveAdv;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockImpulsionDriveAdv extends BlockImpulsionDriveBasic {

	public BlockImpulsionDriveAdv(int id) {
		super(id);
		
		setCreativeTab(CreativeTabs.tabRedstone);
		setHardness(50.0F);
		setResistance(10.0F);
		setStepSound(Block.soundMetalFootstep);
		
		setUnlocalizedName(BlockInfo.IMPULSION_DRIVE_ADVANCED_UNLOCALIZED_NAME);
	}

	@Override
	public TileEntity createNewTileEntity(World world) {
		return new TileEntityImpulsionDriveAdv();
	}

}

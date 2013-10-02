package net.funshinex.arcaneimpulsion.block;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import net.funshinex.arcaneimpulsion.tileentity.TileEntityArcaneCollector;
import net.funshinex.arcaneimpulsion.tileentity.TileEntityImpulsionDriveAdv;
import net.funshinex.arcaneimpulsion.tileentity.TileEntityImpulsionDriveBasic;
import net.minecraft.block.Block;

public class Blocks {
	
	public static Block impulsionDriveBasic;
	public static Block impulsionDriveAdvanced;
	public static Block arcaneCollector;
	
	public static void init() {
		impulsionDriveBasic = new BlockImpulsionDriveBasic(BlockInfo.IMPULSION_DRIVE_BASIC_ID);
		GameRegistry.registerBlock(impulsionDriveBasic, BlockInfo.IMPULSION_DRIVE_BASIC_KEY);

		impulsionDriveAdvanced = new BlockImpulsionDriveAdv(BlockInfo.IMPULSION_DRIVE_ADVANCED_ID);
		GameRegistry.registerBlock(impulsionDriveAdvanced, BlockInfo.IMPULSION_DRIVE_ADVANCED_KEY);
		
		arcaneCollector = new BlockArcaneCollector(BlockInfo.ARCANE_COLLECTOR_ID);
		GameRegistry.registerBlock(arcaneCollector, BlockInfo.ARCANE_COLLECTOR_KEY);
	}
	
	public static void addNames() {
		LanguageRegistry.addName(impulsionDriveBasic, BlockInfo.IMPULSION_DRIVE_BASIC_NAME);
		LanguageRegistry.addName(impulsionDriveAdvanced, BlockInfo.IMPULSION_DRIVE_ADVANCED_NAME);
		LanguageRegistry.addName(arcaneCollector, BlockInfo.ARCANE_COLLECTOR_NAME);
	}
	
	public static void registerTileEntities() {
		GameRegistry.registerTileEntity(TileEntityImpulsionDriveBasic.class, BlockInfo.IMPULSION_DRIVE_BASIC_KEY);
		GameRegistry.registerTileEntity(TileEntityImpulsionDriveAdv.class, BlockInfo.IMPULSION_DRIVE_ADVANCED_KEY);
		GameRegistry.registerTileEntity(TileEntityArcaneCollector.class, BlockInfo.ARCANE_COLLECTOR_KEY);
	}
}

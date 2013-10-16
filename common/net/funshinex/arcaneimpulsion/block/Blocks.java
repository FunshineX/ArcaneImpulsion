package net.funshinex.arcaneimpulsion.block;

import net.funshinex.arcaneimpulsion.tileentity.TileEntityArcaneCollector;
import net.funshinex.arcaneimpulsion.tileentity.TileEntityArcaneCondenser;
import net.funshinex.arcaneimpulsion.tileentity.TileEntityArcaneExtractor;
import net.funshinex.arcaneimpulsion.tileentity.TileEntityIMStorage;
import net.funshinex.arcaneimpulsion.tileentity.TileEntityImpulsionDriveAdv;
import net.funshinex.arcaneimpulsion.tileentity.TileEntityImpulsionDriveBasic;
import net.minecraft.block.Block;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

public class Blocks {
	
	public static Block impulsionDriveBasic;
	public static Block impulsionDriveAdvanced;
	public static Block arcaneCollector;
	public static Block arcaneExtractor;
	public static Block arcaneCondenser;
	public static Block imStorage;
	
	public static void init() {
		impulsionDriveBasic = new BlockImpulsionDriveBasic(BlockInfo.IMPULSION_DRIVE_BASIC_ID);
		GameRegistry.registerBlock(impulsionDriveBasic, BlockInfo.IMPULSION_DRIVE_BASIC_KEY);

		impulsionDriveAdvanced = new BlockImpulsionDriveAdv(BlockInfo.IMPULSION_DRIVE_ADVANCED_ID);
		GameRegistry.registerBlock(impulsionDriveAdvanced, BlockInfo.IMPULSION_DRIVE_ADVANCED_KEY);
		
		arcaneCollector = new BlockArcaneCollector(BlockInfo.ARCANE_COLLECTOR_ID);
		GameRegistry.registerBlock(arcaneCollector, BlockInfo.ARCANE_COLLECTOR_KEY);

		arcaneExtractor = new BlockArcaneExtractor(BlockInfo.ARCANE_EXTRACTOR_ID);
		GameRegistry.registerBlock(arcaneExtractor, BlockInfo.ARCANE_EXTRACTOR_KEY);		

		arcaneCondenser = new BlockArcaneCondenser(BlockInfo.ARCANE_CONDENSER_ID);
		GameRegistry.registerBlock(arcaneCondenser, BlockInfo.ARCANE_CONDENSER_KEY);		

		imStorage = new BlockIMStorage(BlockInfo.IM_STORAGE_ID);
		GameRegistry.registerBlock(imStorage, BlockInfo.IM_STORAGE_KEY);
	}
	
	public static void addNames() {
		LanguageRegistry.addName(impulsionDriveBasic, BlockInfo.IMPULSION_DRIVE_BASIC_NAME);
		LanguageRegistry.addName(impulsionDriveAdvanced, BlockInfo.IMPULSION_DRIVE_ADVANCED_NAME);
		LanguageRegistry.addName(arcaneCollector, BlockInfo.ARCANE_COLLECTOR_NAME);
		LanguageRegistry.addName(arcaneExtractor, BlockInfo.ARCANE_EXTRACTOR_NAME);
		LanguageRegistry.addName(arcaneCondenser, BlockInfo.ARCANE_CONDENSER_NAME);
		LanguageRegistry.addName(imStorage, BlockInfo.IM_STORAGE_NAME);
	}
	
	public static void registerTileEntities() {
		GameRegistry.registerTileEntity(TileEntityImpulsionDriveBasic.class, BlockInfo.IMPULSION_DRIVE_BASIC_KEY);
		GameRegistry.registerTileEntity(TileEntityImpulsionDriveAdv.class, BlockInfo.IMPULSION_DRIVE_ADVANCED_KEY);
		GameRegistry.registerTileEntity(TileEntityArcaneCollector.class, BlockInfo.ARCANE_COLLECTOR_KEY);
		GameRegistry.registerTileEntity(TileEntityArcaneExtractor.class, BlockInfo.ARCANE_EXTRACTOR_KEY);
		GameRegistry.registerTileEntity(TileEntityArcaneCondenser.class, BlockInfo.ARCANE_CONDENSER_KEY);
		GameRegistry.registerTileEntity(TileEntityIMStorage.class, BlockInfo.IM_STORAGE_KEY);
	}
}

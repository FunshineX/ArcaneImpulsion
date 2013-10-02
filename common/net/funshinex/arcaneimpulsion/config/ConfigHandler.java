package net.funshinex.arcaneimpulsion.config;

import java.io.File;

import net.funshinex.arcaneimpulsion.block.BlockInfo;
import net.minecraftforge.common.Configuration;

public class ConfigHandler {
	
	public static void init(File file) {
		Configuration config = new Configuration(file);
		
		config.load();
		
		BlockInfo.IMPULSION_DRIVE_BASIC_ID = config.getBlock(BlockInfo.IMPULSION_DRIVE_BASIC_KEY,  BlockInfo.IMPULSION_DRIVE_BASIC_DEFAULT).getInt();
		BlockInfo.IMPULSION_DRIVE_ADVANCED_ID = config.getBlock(BlockInfo.IMPULSION_DRIVE_ADVANCED_KEY,  BlockInfo.IMPULSION_DRIVE_ADVANCED_DEFAULT).getInt();
		BlockInfo.ARCANE_COLLECTOR_ID = config.getBlock(BlockInfo.ARCANE_COLLECTOR_KEY,  BlockInfo.ARCANE_COLLECTOR_DEFAULT).getInt();
		
		config.save();
	}
}

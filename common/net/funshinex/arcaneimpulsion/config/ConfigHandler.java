package net.funshinex.arcaneimpulsion.config;

import java.io.File;

import net.funshinex.arcaneimpulsion.block.BlockInfo;
import net.funshinex.arcaneimpulsion.item.ItemInfo;
import net.minecraftforge.common.Configuration;

public class ConfigHandler {
	
	public static void init(File file) {
		Configuration config = new Configuration(file);
		
		config.load();
		
		BlockInfo.IMPULSION_DRIVE_BASIC_ID = config.getBlock(BlockInfo.IMPULSION_DRIVE_BASIC_KEY,  BlockInfo.IMPULSION_DRIVE_BASIC_DEFAULT).getInt();
		BlockInfo.IMPULSION_DRIVE_ADVANCED_ID = config.getBlock(BlockInfo.IMPULSION_DRIVE_ADVANCED_KEY,  BlockInfo.IMPULSION_DRIVE_ADVANCED_DEFAULT).getInt();
		BlockInfo.ARCANE_COLLECTOR_ID = config.getBlock(BlockInfo.ARCANE_COLLECTOR_KEY,  BlockInfo.ARCANE_COLLECTOR_DEFAULT).getInt();
		BlockInfo.ARCANE_EXTRACTOR_ID = config.getBlock(BlockInfo.ARCANE_EXTRACTOR_KEY,  BlockInfo.ARCANE_EXTRACTOR_DEFAULT).getInt();
		BlockInfo.ARCANE_CONDENSER_ID = config.getBlock(BlockInfo.ARCANE_CONDENSER_KEY,  BlockInfo.ARCANE_CONDENSER_DEFAULT).getInt();
		BlockInfo.IM_STORAGE_ID = config.getBlock(BlockInfo.IM_STORAGE_KEY,  BlockInfo.IM_STORAGE_DEFAULT).getInt();
		
		ItemInfo.ARCANE_TEMPLATE_ID = config.getItem(ItemInfo.ARCANE_TEMPLATE_KEY, ItemInfo.ARCANE_TEMPLATE_DEFAULT).getInt() - 256;
		
		config.save();
	}
}

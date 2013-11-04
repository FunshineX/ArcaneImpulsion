package net.funshinex.arcaneimpulsion.config;

import java.io.File;
import java.util.HashMap;

import net.minecraftforge.common.ConfigCategory;
import net.minecraftforge.common.Configuration;
import net.minecraftforge.common.Property;

public class IMValues {
	
	public static HashMap<Integer,Integer> ims = new HashMap<Integer,Integer>();
	
	public static void init(File file) {
		File newFile = new File(file.getAbsolutePath().replace(".cfg","IMs.cfg"));

		Configuration config = new Configuration(newFile);
		
		config.load();
		
		ConfigCategory imValuesCategoryVanilla = config.getCategory("VanillaIMValues");
		
		for (Property block : imValuesCategoryVanilla.values()) {
			
			String[] imvData = block.getName().split("-");
	
			ims.put(Integer.parseInt(imvData[0]),block.getInt());			
		}
		
		ConfigCategory imValuesCategoryMods = config.getCategory("ModIMValues");
		
		for (ConfigCategory mod : imValuesCategoryMods.getChildren()) {
			for (Property block : mod.values()) {
				
				String[] imvData = block.getName().split("-");
				
				ims.put(Integer.parseInt(imvData[0]),block.getInt());			
			}
		}
				
		config.save();
	}
}

